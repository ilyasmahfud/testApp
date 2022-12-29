package com.project.ilyasMahfudSkripsi.module.authentication.presenter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    @Email
    private String username;
    @NotBlank
    @Size(min = 8)
    private String password;
}