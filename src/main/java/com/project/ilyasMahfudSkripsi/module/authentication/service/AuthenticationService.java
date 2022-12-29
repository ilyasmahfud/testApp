package com.project.ilyasMahfudSkripsi.module.authentication.service;

import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetails;
import com.project.ilyasMahfudSkripsi.module.authentication.model.LoginDto;

public interface AuthenticationService {
    void register(User userRequestBody, UserDetails userDetailsRequestBody);
    LoginDto login(String username, String password);
}
