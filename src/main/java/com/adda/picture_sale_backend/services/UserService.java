package com.adda.picture_sale_backend.services;

import com.adda.picture_sale_backend.database.SequenceGeneratorService;
import com.adda.picture_sale_backend.entities.User;
import com.adda.picture_sale_backend.jwt.JWTUtility;
import com.adda.picture_sale_backend.jwt.JwtInterceptor;
import com.adda.picture_sale_backend.repositories.UserRepository;
import com.adda.picture_sale_backend.security.PasswordEncoder;
import com.adda.picture_sale_backend.utils.DateUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public UserService(UserRepository userRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {

        List<User> returnList = userRepository.findAll();

        returnList.sort(Comparator.comparingInt(User::getStatus).reversed());

        return returnList;
    }

    public User createUser(User user) {
        long _id = sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
        user.setId(_id);
        user.setStatus(1);
        user.setCreatedDate(DateUtils.getCurrentDate());
        user.setPassword(PasswordEncoder.getInstance().encodePassword(user.getPassword()));

        return userRepository.insert(user);
    }

//    public User findByUsername(String username) {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        return optionalUser.orElse(null);
//        //  return userRepository.findByUsername(username).get();
//    }


    public  boolean existsByEmailOrPhone(String email, String phone){

        boolean check1 =  userRepository.existsByEmailOrPhone(email,phone);

        if(check1)
        {
            User user = findByEmail(email);
            if(user.getStatus()== User.STATUS_CONFIRM)
            {
                return true;
            }else if(user.getStatus() == User.STATUS_PENDING)
            {
                userRepository.delete(user);
                return false;
            }
        }
        return false;
    }

    public User findByEmail(String email) {
        //Optional<User> optionalUser = userRepository.findByEmail(email);

        //if(optionalUser.isEmpty()) return  null;
        //return  optionalUser.get();
        for(User user: findAll()){
            if(user.getEmail().equals(email) && user.getStatus()== User.STATUS_CONFIRM)
                return user;
        }

        for(User user: findAll()){
            if(user.getEmail().equals(email))
                return user;
        }
        return  null;
       // return optionalUser.orElse(null);
        //  return userRepository.findByUsername(username).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User changPassword(User updatedUser){
        Long id = updatedUser.getId();
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }

    }

    public User updateChangePassword( User updatedUser){
        Long id = updatedUser.getId();
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }

    }
    public User updateUser(User updatedUser) {
        Long id = updatedUser.getId();
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            //user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setCountry(updatedUser.getCountry());
            user.setAvatar(updatedUser.getAvatar());
            user.setStatus(updatedUser.getStatus());
            user.setLastName(updatedUser.getLastName());
            user.setFirstName(updatedUser.getFirstName());
            user.setRole(updatedUser.getRole());
            user.setGender(updatedUser.getGender());
            // Update other fields if needed
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    public User findUserByToken(String token) {
       // token = "Bearer " + token;
        boolean isAuthenticated = JwtInterceptor.getInstance().isValidToken(token);
        User user = null;
        if (isAuthenticated) {
            Claims claims = JWTUtility.getInstance().parseToken(token);
            String email = claims.getSubject();
            if (email != null) {
                user = findByEmail(email);
                return user;
            }
        }
        return null;
        // Add more methods as per your requirements
    }

}