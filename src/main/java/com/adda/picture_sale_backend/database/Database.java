package com.adda.picture_sale_backend.database;


import com.adda.picture_sale_backend.entities.User;
import com.adda.picture_sale_backend.repositories.UserRepository;
import com.adda.picture_sale_backend.security.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository
                                   ) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                User user = new User();
               // priceTimeRepository.deleteAll();;
                user.setId(1L);
                user.setEmail("admin@gmail.com");
                user.setPhone("84965741051");
                user.setPassword(PasswordEncoder.getInstance().encodePassword("A123456a@"));
                user.setStatus(User.STATUS_CONFIRM);
                user.setGender("Mr");
                user.setCountry("Vietnam");
                user.setFirstName("Dong");
                user.setLastName("Dinh");
                user.setRole("admin");
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                String formattedDate = currentDate.format(formatter);
                long longDate = Long.parseLong(formattedDate);
                user.setCreatedDate(longDate);
                userRepository.deleteAll();
                userRepository.save(user);
                if(userRepository.findAll().isEmpty())
                    userRepository.insert(user);

            }
        };
    }
}
