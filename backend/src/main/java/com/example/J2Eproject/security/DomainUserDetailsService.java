package com.example.J2Eproject.security;

import com.example.J2Eproject.user.UserRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.J2Eproject.user.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationServiceException("username" + username + " not found"));

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .disabled(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .build();
    }
}
