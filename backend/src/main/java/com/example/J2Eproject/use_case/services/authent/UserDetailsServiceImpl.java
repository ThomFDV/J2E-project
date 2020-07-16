package com.example.J2Eproject.use_case.services.authent;

import com.example.J2Eproject.domain.models.User;
import com.example.J2Eproject.infrastructure.dao.user.MongoUserRepository;
import com.example.J2Eproject.infrastructure.dao.user.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    MongoUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        return UserDetailsImpl.build(user);
    }
}
