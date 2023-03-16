package com.example.moviereviewSpringBootApplicaion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/movies/{movieId}/reviews/{userId}").permitAll()
                .antMatchers(HttpMethod.POST,"/").permitAll()
                .antMatchers(HttpMethod.GET,"/movies/{movieId}/reviews").permitAll()
                .antMatchers(HttpMethod.POST,"/movies/{movieId}/reviews").permitAll()
                .antMatchers(HttpMethod.PUT,"/movies/{movieId}/reviews").permitAll()
                .antMatchers(HttpMethod.DELETE,"/movies/{movieId}/reviews").permitAll()
                .antMatchers(HttpMethod.GET,"/movies").permitAll()
                .antMatchers(HttpMethod.GET,"/movies/{movieId}").permitAll()
                .antMatchers(HttpMethod.POST,"/movies").permitAll()
                .antMatchers(HttpMethod.PUT,"/movies/{movieId}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/movies/{movieId}").permitAll()
                .anyRequest().authenticated();
    }
}
