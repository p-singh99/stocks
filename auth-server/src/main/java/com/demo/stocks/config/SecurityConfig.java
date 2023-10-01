package com.demo.stocks.config;

import com.demo.stocks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;

/*
*
*   SPRING SECURITY AUTHENTICATION ARCHITECTURE
*
*   #########################                  ####################
*   # Authentication Filter #   ------------>  # Security Context #
*   #########################                  ####################
*             |    A
*             V    |
*   ##########################
*   # Authentication Manager #
*   ##########################
*             |    A                            ########################
*             V    |                            # User Details Service #
*   ###########################   ----------->  ########################
*   # Authentication Provider #
*   ###########################   ----------->  ####################
*                                               # Password Encoder #
*                                               ####################
*
*   1) Request is intercepted by Auth filter.
*   2) Auth responsibility is delegated to Authentication Manager.
*   3) Auth Provider implements the authentication logic.
*   4) Auth Provider uses User Details Service to find User and validate password.
*   5) The details of authenticated entity are stored in the Security Context.
*
* */

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Autowired
    private UserRepository repository;

    private static final String[] AUTH_DISABLED = {"/auth/**", "/error"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((request) -> request
                        .requestMatchers(AUTH_DISABLED).permitAll()
                        .anyRequest().authenticated()
                ).formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username);
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}