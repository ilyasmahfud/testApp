package com.project.ilyasMahfudSkripsi.module.authentication.presenter.controller;

import com.project.ilyasMahfudSkripsi.model.SuccessDetailsResponse;
import com.project.ilyasMahfudSkripsi.model.SuccessResponse;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.User;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetails;
import com.project.ilyasMahfudSkripsi.module.authentication.entity.UserDetailsImpl;
import com.project.ilyasMahfudSkripsi.module.authentication.jwt.JwtTokenUtility;
import com.project.ilyasMahfudSkripsi.module.authentication.model.LoginDto;
import com.project.ilyasMahfudSkripsi.module.authentication.presenter.model.LoginRequest;
import com.project.ilyasMahfudSkripsi.module.authentication.presenter.model.RegisterRequest;
import com.project.ilyasMahfudSkripsi.module.authentication.service.AuthenticationService;
import com.project.ilyasMahfudSkripsi.utility.ModelMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${url.map.api}")
public class AuthenticationController {
    @Autowired
    private ModelMapperUtility modelMapperUtility;
    @Autowired
    private JwtTokenUtility jwtTokenUtility;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterRequest requestBody) {
        User newUser = modelMapperUtility.modelMapperUtility()
                .map(requestBody, User.class);
        UserDetails newUserDetails = modelMapperUtility.modelMapperUtility()
                .map(requestBody, UserDetails.class);
        authenticationService.register(newUser, newUserDetails);
        return new ResponseEntity<>(
                new SuccessResponse(true, "User successfully registered"),
                HttpStatus.CREATED);
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> login(@RequestBody LoginRequest requestBody) {
        LoginDto loginDto = authenticationService.login(requestBody.getUsername(), requestBody.getPassword());
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = jwtTokenUtility.generateToken(userDetails);
        loginDto.setToken(token);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(new SuccessDetailsResponse(true, "Login successful", loginDto));
    }

}
