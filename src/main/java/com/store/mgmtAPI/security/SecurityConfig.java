package com.store.mgmtAPI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> // configure access based on roles
                        auth
                                // Allow access to H2 console only as ADMIN
                                .requestMatchers("/h2-console/**").permitAll()

                                .requestMatchers(HttpMethod.GET, "/products").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.GET, "/products/**").hasRole("CLIENT")

                                .requestMatchers(HttpMethod.GET, "/products").hasRole("VENDOR")
                                .requestMatchers(HttpMethod.GET, "/products/**").hasRole("VENDOR")
                                .requestMatchers(HttpMethod.POST, "/products").hasRole("VENDOR")
                                .requestMatchers(HttpMethod.PUT, "/products/product/**").hasRole("VENDOR")

                                .requestMatchers(HttpMethod.DELETE, "/products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")

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
                // Enable HTTP Basic Auth
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
