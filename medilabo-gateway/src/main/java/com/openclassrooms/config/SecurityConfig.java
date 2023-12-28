package com.openclassrooms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password("medilabo") // For testing purpose only
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll() // Allow unauthenticated access to the authentication endpoint
                .antMatchers("/**").hasRole("ADMIN") // Require ADMIN role for other endpoints
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}