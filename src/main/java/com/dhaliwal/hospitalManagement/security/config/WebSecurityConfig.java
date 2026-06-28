package com.dhaliwal.hospitalManagement.security.config;

import com.dhaliwal.hospitalManagement.security.jwt.JwtAuthFilter;
import com.dhaliwal.hospitalManagement.security.oauth2.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.dhaliwal.hospitalManagement.entity.type.RoleType.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        sessionConfig ->
                                sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        )
                .authorizeHttpRequests(auth -> auth

                        // public
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/public/**").permitAll()

                        // admin
                        .requestMatchers("/admin/**")
                        .hasRole(ADMIN.name())

                        // doctor
                        .requestMatchers("/doctor/**")
                        .hasAnyRole(
                                ADMIN.name(),
                                DOCTOR.name()
                        )

                        // patient
                        .requestMatchers("/patient/**")
                        .hasAnyRole(
                                ADMIN.name(),
                                PATIENT.name()
                        )

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
