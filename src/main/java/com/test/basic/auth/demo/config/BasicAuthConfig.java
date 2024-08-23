package com.test.basic.auth.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthConfig {

    @Value("${app.security.username}")
    String username;

    @Value("${app.security.password}")
    String password;

    @Value("${app.security.rol}")
    String rol;

    @Value("${app.security.username2}")
    String username2;

    @Value("${app.security.password2}")
    String password2;

    @Value("${app.security.rol2}")
    String rol2;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest.requestMatchers("/api/v1/public").permitAll()
                                .requestMatchers("/api/v1/private").hasRole(rol)
                                .requestMatchers("/api/v1/manager").hasRole(rol2)
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();
        UserDetails userDetails = User.builder().username(username).password(encoder.encode(password)).roles(rol).build();
        UserDetails userDetails2 = User.builder().username(username2).password(encoder.encode(password2)).roles(rol2).build();
        return new InMemoryUserDetailsManager(userDetails, userDetails2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
