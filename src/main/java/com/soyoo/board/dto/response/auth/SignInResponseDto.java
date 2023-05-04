package com.soyoo.board.dto.response.auth;

import com.soyoo.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto extends ResponseDto {
    
    private String token;
    private int experationDate;

}
