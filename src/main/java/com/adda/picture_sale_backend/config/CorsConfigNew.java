package com.adda.picture_sale_backend.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfigNew {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://150.95.110.230");
        corsConfig.addAllowedOrigin("http://localhost:81");
        corsConfig.addAllowedOrigin("http://localhost:80");
        corsConfig.addAllowedOrigin("http://localhost:3000");
        corsConfig.addAllowedOrigin("http://localhost:3001");
        corsConfig.addAllowedOrigin("http://163.44.206.118:80");
        corsConfig.addAllowedOrigin("http://163.44.206.118");
        corsConfig.addAllowedOrigin("http://163.44.206.118:81");
        corsConfig.addAllowedOrigin("http://163.44.206.118:83");
        corsConfig.addAllowedOrigin("http://163.44.206.118:3000");
        corsConfig.addAllowedOrigin("http://150.95.113.18:80");
        corsConfig.addAllowedOrigin("http://150.95.113.18");
        corsConfig.addAllowedOrigin("http://ambassadordaycruise.com/");
        corsConfig.addAllowedOrigin("http://admin.ambassadordaycruise.com/");
        corsConfig.addAllowedOrigin("https://ambassadordaycruise.com/");
        corsConfig.addAllowedOrigin("https://admin.ambassadordaycruise.com/");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(source);
    }
}
