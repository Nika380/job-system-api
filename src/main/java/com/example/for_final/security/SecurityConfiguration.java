package com.example.for_final.security;


import com.example.for_final.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.webjars.NotFoundException;

import java.util.stream.Collectors;

@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    private final UserRepo userRepo;

    public SecurityConfiguration(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var user = userRepo.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException("Email Not Found"));
            return new SecUser(user);

        };

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/users/register").permitAll()
                .antMatchers("/jobs", "/jobs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        return http.build();
    }
}
