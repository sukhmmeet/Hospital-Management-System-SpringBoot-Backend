package com.dhaliwal.hospitalManagement.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler  oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        sessionConfig ->
                                sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
//
//                        .requestMatchers("/admin/doctors/**")
//                        .hasAnyRole("ADMIN", "DOCTOR")
//
//                        .requestMatchers("/admin/**")
//                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> {
                    oauth2.failureHandler(
                            (request, response, exception) -> log.error("Authentication Failure", exception)
                    );
                            oauth2.successHandler(oAuth2SuccessHandler);
                        }
                );
//                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
