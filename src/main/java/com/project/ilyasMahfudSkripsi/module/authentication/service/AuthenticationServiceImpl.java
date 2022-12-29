package com.project.ilyasMahfudSkripsi.module.authentication.service;

import com.project.ilyasMahfudSkripsi.exception.AuthenticateFailedException;
import com.project.ilyasMahfudSkripsi.exception.ForbiddenException;
import com.project.ilyasMahfudSkripsi.exception.RegisterFailedException;
import com.project.ilyasMahfudSkripsi.exception.ResourceNotFoundException;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.Role;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.RoleEnum;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetails;
import com.project.ilyasMahfudSkripsi.module.authentication.model.LoginDto;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.RoleRepository;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.UserDetailsRepository;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.UserRepository;
import com.project.ilyasMahfudSkripsi.utility.FakerUtility;
import com.project.ilyasMahfudSkripsi.utility.ModelMapperUtility;
import com.project.ilyasMahfudSkripsi.utility.RestTemplateUtility;
import com.project.ilyasMahfudSkripsi.utility.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
//    @Value("${resource.server.url}")
    private String BASE_URL;

    @Autowired
    private FakerUtility fakerUtility;
    @Autowired
    private ModelMapperUtility modelMapperUtility;
    @Autowired
    private RestTemplateUtility restTemplateUtility;
    @Autowired
    private UserUtility userUtility;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    @Transactional
    public void register(User userRequestBody, UserDetails userDetailsRequestBody) {
        if (userRepository.findByUsername(userRequestBody.getUsername()).isPresent()) {
            throw new RegisterFailedException("Username already registered");
        }

        Role userRole = roleRepository.findByRoleName(RoleEnum.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        User newUser = userRepository.save(
                new User(
                        userRequestBody.getUsername(),
                        passwordEncoder.encode(userRequestBody.getPassword()),
                        userRole));

        UserDetails newUserDetails = modelMapperUtility.modelMapperUtility()
                .map(userDetailsRequestBody, UserDetails.class);
        newUserDetails.setUser(newUser);
        userDetailsRepository.save(newUserDetails);
    }

    @Override
    public LoginDto login(String email, String password) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(email, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            if (user.getRole().getRoleName().name().equals(RoleEnum.USER.name())) {
                UserDetails userDetails = userDetailsRepository.findByUser(user)
                        .orElseThrow(() -> new ResourceNotFoundException("User details not found"));

                LoginDto loginDto = new LoginDto();
                loginDto.setUsername(user.getUsername());
                loginDto.setRole(user.getRole().getRoleName().name());
                return loginDto;
            } else {
                throw new ForbiddenException("Forbidden");
            }
        } catch (BadCredentialsException exception) {
            throw new AuthenticateFailedException("Incorrect email or password");
        }
    }

}
