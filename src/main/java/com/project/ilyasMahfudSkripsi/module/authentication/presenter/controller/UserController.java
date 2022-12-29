package com.project.ilyasMahfudSkripsi.module.authentication.presenter.controller;

import com.project.ilyasMahfudSkripsi.module.authentication.model.UserDetailsDto;
import com.project.ilyasMahfudSkripsi.module.authentication.model.UserProfileDto;
import com.project.ilyasMahfudSkripsi.module.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.map.api}")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profiles")
    public ResponseEntity<Object> getUserProfile() {
        UserDetailsDto userDetailsDto = userService.getUserDetails();
        return new ResponseEntity<>(userDetailsDto, HttpStatus.OK);
    }

    @GetMapping("/userHighlight")
    public ResponseEntity<Object> getUserHistory(){
        UserProfileDto userProfileDto = userService.getUserProfile();
        return new ResponseEntity<>(userProfileDto, HttpStatus.OK);
    }
}
