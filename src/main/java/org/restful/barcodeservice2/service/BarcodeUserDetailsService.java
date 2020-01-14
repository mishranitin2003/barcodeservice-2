package org.restful.barcodeservice2.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BarcodeUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Accept any user name for now but with password as 'password'
        User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(new BCryptPasswordEncoder().encode("password"));
        builder.roles("ADMIN", "USER");
        return builder.build();
    }
}
