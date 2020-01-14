package org.restful.barcodeservice2;

import org.restful.barcodeservice2.service.BarcodeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BarcodeServiceSecurityConfig extends WebSecurityConfigurerAdapter {

    // Secure the end points with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/").denyAll()
                .antMatchers(HttpMethod.PUT, "/").denyAll()
                .antMatchers(HttpMethod.PATCH, "/").denyAll()
                .antMatchers(HttpMethod.DELETE, "/").denyAll()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new BarcodeUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

}
