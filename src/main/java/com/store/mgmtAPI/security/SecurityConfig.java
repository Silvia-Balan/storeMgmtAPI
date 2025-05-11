package com.store.mgmtAPI.security;

import com.store.mgmtAPI.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // enable @Secured
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AccessDeniedHandler accessDeniedHandler) throws Exception{
        http
                .authorizeHttpRequests(auth -> // configure access based on roles
                        auth
                                // Allow access to H2 console only as ADMIN
                                .requestMatchers("/h2-console/**").permitAll()
                                .anyRequest().authenticated()
                )
                // disable CSRF for H2 console
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")  // allow H2
                        .disable()                                          // allow Postman POST/PUT
                )
                // Allow frame-based pages: H2 console
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable())
                )
                .exceptionHandling(ex -> ex
                                .accessDeniedHandler(accessDeniedHandler)
                        )
                // Enable HTTP Basic Auth
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
