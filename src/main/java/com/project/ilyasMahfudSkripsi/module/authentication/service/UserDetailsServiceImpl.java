package com.project.ilyasMahfudSkripsi.module.authentication.service;

import com.project.ilyasMahfudSkripsi.exception.AuthenticateFailedException;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetailsImpl;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticateFailedException("Incorrect username or password"));
        return new UserDetailsImpl(user);
    }
}
