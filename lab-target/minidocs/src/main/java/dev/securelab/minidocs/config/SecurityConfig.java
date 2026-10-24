package dev.securelab.minidocs.config;

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
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder,
            @Value("${MINIDOCS_MARIO_PASSWORD}") String marioPassword,
            @Value("${MINIDOCS_LUCA_PASSWORD}") String lucaPassword
    ) {

        UserDetails mario = User.withUsername("mario")
                .password(passwordEncoder.encode(marioPassword))
                .roles("USER")
                .build();

        UserDetails luca = User.withUsername("luca")
                .password(passwordEncoder.encode(lucaPassword))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(mario, luca);
    }
}