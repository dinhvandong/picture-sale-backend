package com.adda.picture_sale_backend.controllers;

import com.adda.picture_sale_backend.database.SequenceGeneratorService;
import com.adda.picture_sale_backend.dto.ResponseObject;
import com.adda.picture_sale_backend.dto.UserDTO;
import com.adda.picture_sale_backend.entities.ConfirmCode;
import com.adda.picture_sale_backend.entities.User;
import com.adda.picture_sale_backend.jwt.JwtTokenStore;
import com.adda.picture_sale_backend.repositories.UserRepository;
import com.adda.picture_sale_backend.security.PasswordEncoder;
import com.adda.picture_sale_backend.services.AuthService;
import com.adda.picture_sale_backend.services.ConfirmCodeService;
import com.adda.picture_sale_backend.services.EmailService;
import com.adda.picture_sale_backend.services.UserService;

import com.adda.picture_sale_backend.utils.DateUtils;
import com.adda.picture_sale_backend.utils.Ultis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//http://163.44.206.118

@CrossOrigin(origins = {
        "http://163.44.206.118:83",
        "http://163.44.206.118:80",
        "http://163.44.206.118",
        "http://163.44.206.118:81",
        "http://localhost:3001",
        "http://localhost:3000",
        "http://150.95.113.18",
        "http://ambassadordaycruise.com/",
        "http://admin.ambassadordaycruise.com/"

})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @Autowired
    ConfirmCodeService confirmCodeService;

    @Autowired
    EmailService emailService;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
        if (userService.existsByEmailOrPhone(userDTO.getEmail(), userDTO.getPhone())) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "null", "Email already exists"));
        }
        User requestUser = new User();
        requestUser.setEmail(userDTO.getEmail());
        requestUser.setPassword(userDTO.getPassword());
        requestUser.setPhone(userDTO.getPhone());
        requestUser.setCountry(userDTO.getCountry());
        requestUser.setLastName(userDTO.getLastName());
        requestUser.setFirstName(userDTO.getFirstName());
        requestUser.setStatus(1);
        requestUser.setRole("client");
        requestUser.setCountry(userDTO.getCountry());
        requestUser.setGender(userDTO.getGender());
        requestUser.setStatus(User.STATUS_PENDING);

        User user = userService.createUser(requestUser);

        ConfirmCode confirmCode = new ConfirmCode();
        Long idCode = sequenceGeneratorService.generateSequence(ConfirmCode.SEQUENCE_NAME);
        confirmCode.setId(idCode);
        confirmCode.setUserID(user.getId());
        confirmCode.setEmail(userDTO.getEmail());
        confirmCode.setStatus(ConfirmCode.STATUS_CONFIRM_PENDING);
        confirmCode.setType(ConfirmCode.REGISTER_NEW);
        confirmCode.setCreatedTime(DateUtils.getCurrentDate());
        confirmCode.setSecureCode(Ultis.generateRandomCode(6));
        confirmCode.setPathRandom(Ultis.generateRandomString(10));
        confirmCodeService.create(confirmCode);

        emailService.sendEmailRegisterConfirmCode(userDTO.getEmail(), userDTO.getFirstName() + " "+ userDTO.getLastName(), confirmCode.getPathRandom(), confirmCode.getSecureCode());

        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200, user, "success"));
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<?> signupAdmin(@RequestBody UserDTO userDTO) {
        if (userService.findByEmail(userDTO.getEmail())!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, "null", "Email already exists"));
        }
        User requestUser = new User();
        //requestUser.setId(1L);
        requestUser.setEmail(userDTO.getEmail());
        requestUser.setPassword(PasswordEncoder.getInstance().encodePassword(userDTO.getPassword()));
        requestUser.setPhone(userDTO.getPhone());
        requestUser.setCountry(userDTO.getCountry());
        requestUser.setLastName(userDTO.getLastName());
        requestUser.setFirstName(userDTO.getFirstName());
        requestUser.setStatus(User.STATUS_CONFIRM);
        requestUser.setGender(userDTO.getGender());
        requestUser.setRole("admin");
        User user = userService.createUser(requestUser);
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200, user, "success"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody UserDTO userDTO) {
        User user = userService.findByEmail((userDTO.getEmail()));
        if (user==null || user.getStatus() != User.STATUS_CONFIRM || !PasswordEncoder.getInstance().matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(201, user, "User not found"));
        }
        String token = authService.loginWithEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        JwtTokenStore.getInstance().storeToken(userDTO.getEmail(), token);
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200,user,token));
    }

    @PostMapping("/signinAdmin")
    public ResponseEntity<?> signinAdmin(@RequestBody UserDTO userDTO) {
        User user = userService.findByEmail((userDTO.getEmail()));
        if (!user.getRole().equals("admin") || !PasswordEncoder.getInstance().matches(userDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(201, user, "Token invalid"));
        }
        String token = authService.loginWithEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        JwtTokenStore.getInstance().storeToken(userDTO.getEmail(), token);
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200,user,token));
    }


    @PostMapping("/requestChangePassword")
    public ResponseEntity<?> requestCodeChangePassword(@RequestParam String email,
                                                       @RequestParam String code,
                                                       @RequestParam String password)
    {
        ConfirmCode confirmCode = confirmCodeService
                .findBySecureCodeAndEmailAndType(code, email, ConfirmCode.FORGOT_PASSWORD);

        if(confirmCode == null){
            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseObject(200,false,"not found"));
        }

        confirmCode.setStatus(ConfirmCode.STATUS_CONFIRM_OK);

        confirmCodeService.update(confirmCode);

        User user = userService.findByEmail(email);
        if(user!= null){


            user.setPassword(PasswordEncoder.getInstance().encodePassword(password));
          User response =    userService.updateChangePassword(user);
            emailService.sendEmailChangePasswordSuccess(email, user.getFirstName() + " "+
                    user.getLastName());

            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseObject(200,response,"success"));

        }

        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(202,false,"update fail"));

    }

    @PostMapping("/requestCodeForgotPassword")
    public ResponseEntity<?> requestCodeForgotPassword(@RequestParam String email)
    {
        User found = userService.findByEmail(email);
        if(found!= null){

            ConfirmCode confirmCode = new ConfirmCode();
            Long idCode = sequenceGeneratorService.generateSequence(ConfirmCode.SEQUENCE_NAME);
            confirmCode.setId(idCode);
            confirmCode.setEmail(email);
            confirmCode.setStatus(ConfirmCode.STATUS_CONFIRM_PENDING);
            confirmCode.setType(ConfirmCode.FORGOT_PASSWORD);
            confirmCode.setCreatedTime(DateUtils.getCurrentDate());
            confirmCode.setSecureCode(Ultis.generateRandomCode(6));
            confirmCode.setPathRandom(Ultis.generateRandomString(10));
            confirmCodeService.create(confirmCode);
            ConfirmCode confirmCode1 = confirmCodeService.create(confirmCode);
            // Send email
            emailService.sendEmailForgotPassword(email, found.getFirstName() + " "+
                    found.getLastName(), confirmCode.getSecureCode());

            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseObject(200,confirmCode1,"success"));

        }

        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(201,false,"Email is not found."));

    }


    @PostMapping("/requestConfirmCode")
    public ResponseEntity<?> requestConfirmCode(@RequestParam String path, @RequestParam String code){

        ConfirmCode confirmCode = null;
        confirmCode = confirmCodeService.findBySecureCodeAndPath(code, path);

        if(confirmCode== null){
            return ResponseEntity.status(HttpStatus.OK).body
                    (new ResponseObject(201,false,"fail"));
        }

        // send Email


        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200,true,"Success"));


    }


    @GetMapping("/findAllRequestCode")
    public ResponseEntity<?> findAllRequestCode(){
        return ResponseEntity.status(HttpStatus.OK).body
                (new ResponseObject(200,confirmCodeService.findAll(),"Success"));

    }


}
