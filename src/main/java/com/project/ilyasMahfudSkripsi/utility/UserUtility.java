package com.project.ilyasMahfudSkripsi.utility;

import com.project.ilyasMahfudSkripsi.exception.ResourceNotFoundException;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.Role;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.RoleEnum;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.RoleRepository;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserUtility {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public User getSignedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getPrincipal().toString();
        return userRepository.findByUsername(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAllUsers() {
        Role userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Role %s not found", RoleEnum.USER)));

        return userRepository.findByRole(userRole);
    }
}
