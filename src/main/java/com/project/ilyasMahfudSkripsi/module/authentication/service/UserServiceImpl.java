package com.project.ilyasMahfudSkripsi.module.authentication.service;

import com.project.ilyasMahfudSkripsi.exception.ResourceNotFoundException;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetails;
import com.project.ilyasMahfudSkripsi.module.authentication.model.UserDetailsDto;
import com.project.ilyasMahfudSkripsi.module.authentication.model.UserProfileDto;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.UserDetailsRepository;
import com.project.ilyasMahfudSkripsi.module.authentication.repository.UserRepository;
import com.project.ilyasMahfudSkripsi.utility.ModelMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapperUtility modelMapperUtility;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailsDto getUserDetails() {
        User signedInUser = getSignedInUser();
        UserDetails userDetails = userDetailsRepository.findByUser(signedInUser)
                .orElseThrow(() -> new ResourceNotFoundException("User's details not found"));

        UserDetailsDto userDetailsDto = modelMapperUtility.modelMapperUtility()
                .map(userDetails, UserDetailsDto.class);
        userDetailsDto.setUsername(signedInUser.getUsername());

        return userDetailsDto;
    }

    @Override
    public UserProfileDto getUserProfile() {
        User signedInUser = getSignedInUser();
        UserDetails userDetails = userDetailsRepository.findByUser(signedInUser)
                .orElseThrow(() -> new ResourceNotFoundException("User's details not found"));

        UserProfileDto userProfileDto = modelMapperUtility.modelMapperUtility()
                .map(userDetails, UserProfileDto.class);
        userProfileDto.setFullName(userProfileDto.getFullName());
        userProfileDto.setProfilePicture(userProfileDto.getProfilePicture());

        return userProfileDto;
    }


    private User getSignedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

}
