package com.project.ilyasMahfudSkripsi.module.authentication.service;

import com.project.ilyasMahfudSkripsi.module.authentication.model.UserDetailsDto;
import com.project.ilyasMahfudSkripsi.module.authentication.model.UserProfileDto;

public interface UserService {
    UserDetailsDto getUserDetails();
    UserProfileDto getUserProfile();
}
