package com.adda.picture_sale_backend.services;

import com.adda.picture_sale_backend.entities.User;
import com.adda.picture_sale_backend.jwt.JWTUtility;
import com.adda.picture_sale_backend.repositories.UserRepository;
import com.adda.picture_sale_backend.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public String loginWithUsernameAndPassword(String username, String password) {
//        User user = userRepository.findByUsername(username).orElse(null);
//        assert user != null;
//        if (PasswordEncoder.getInstance().matches(password, user.getPassword())) {
//            return JWTUtility.getInstance().generateToken(username);
//        }
//        return null;
//    }

    public String loginWithEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        assert user != null;
        if (PasswordEncoder.getInstance().matches(password, user.getPassword())) {
            return JWTUtility.getInstance().generateTokenWithEmail(email);
        }
        return null;
    }
}
