package com.adda.picture_sale_backend.controllers;


import com.adda.picture_sale_backend.dto.ResponseObject;
import com.adda.picture_sale_backend.entities.User;
import com.adda.picture_sale_backend.jwt.JWTUtility;
import com.adda.picture_sale_backend.jwt.JwtInterceptor;
import com.adda.picture_sale_backend.jwt.JwtTokenStore;
import com.adda.picture_sale_backend.security.PasswordEncoder;
import com.adda.picture_sale_backend.services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/api/user")
public class UserController {
    private  final UserService userService;
    private final JwtInterceptor jwtInterceptor;
    private final JwtTokenStore jwtTokenStore;
//    private final FirebaseService firebaseService;
    @Autowired
    public UserController(UserService userService, JwtInterceptor jwtInterceptor,
                          JwtTokenStore jwtTokenStore) {
        this.userService = userService;
        this.jwtInterceptor = jwtInterceptor;
        this.jwtTokenStore = jwtTokenStore;
//        this.otpService = otpService;
    }
    @GetMapping("/findByToken")
    public ResponseEntity<?> findByToken(HttpServletRequest request) {
        String token = JwtInterceptor.getInstance().extractTokenFromRequest(request);
        if (token != null) {
            Claims claims =  JWTUtility.getInstance().parseToken(token);
            String email = claims.getSubject();
            if (email != null) {
                User user = userService.findByEmail(email);
                if (user != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, user,"user exist"));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
    }
    @GetMapping("/findToken")
    public ResponseEntity<?> findByTk(@RequestParam String token) {
       // String token = JwtInterceptor.getInstance().extractTokenFromRequest(request);
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            Claims claims = JWTUtility.getInstance().parseToken(token);
            String email = claims.getSubject();
            if (email != null) {
                User user = userService.findByEmail(email);
                if (user != null) {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, user,"user exist"));
                }else {
                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"cannot created"));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String token, @RequestBody User user)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"token is not blank"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            User response =  userService.changPassword(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"change password success"));
        }else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"token is invalid"));
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestParam String token, @RequestBody User user)
    {
        if(token.isBlank())
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            String rawPassword = user.getPassword();
            user.setPassword(PasswordEncoder.getInstance().encodePassword(rawPassword));
            User response =  userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"user not exist"));
        }else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam String token, @RequestBody User user)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            User response =  userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"user not exist"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String token, @RequestParam Long id)
    {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            User foundUser = userService.findById(id);
            foundUser.setStatus(0);
            User response =  userService.updateUser(foundUser);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, response,"user not exist"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }


    @PostMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam String token){

        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.getAllUsers(),"success"));
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
    }
    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam String token,@RequestParam Long id){

        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }
        token = "Bearer " + token;

        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        if(isAuthenticated)
        {
            if(userService.findById(id) == null)
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
            }else
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.findById(id),"success"));
            }
        }else
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(201, null,"user not exist"));
        }

    }
    @GetMapping("/new-access-token")
    public ResponseEntity<?> newAccessToken(@RequestParam String token){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, userService.findAll(),"success"));
    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String username = jwtInterceptor.extractUsername(token);
        if (jwtTokenStore.isTokenPresent(username, token)) {
            jwtTokenStore.removeToken(username);
            return ResponseEntity.ok("Logged out successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }
}
