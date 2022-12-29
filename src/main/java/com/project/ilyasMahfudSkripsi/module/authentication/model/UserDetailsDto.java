package com.project.ilyasMahfudSkripsi.module.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetailsDto {
    private String fullName;
    private String username;
    private String password;
}
