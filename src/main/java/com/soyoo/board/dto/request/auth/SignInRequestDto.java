package com.soyoo.board.dto.request.auth;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDto {
    
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
}
