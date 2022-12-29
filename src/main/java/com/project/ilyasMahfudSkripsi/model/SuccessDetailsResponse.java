package com.project.ilyasMahfudSkripsi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessDetailsResponse {
    private Boolean success;
    private String message;
    private Object data;
}
