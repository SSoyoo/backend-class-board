package com.soyoo.board.service;


import org.springframework.http.ResponseEntity;

import com.soyoo.board.dto.request.auth.SignInRequestDto;
import com.soyoo.board.dto.request.auth.SignUpRequestDto;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.dto.response.auth.SignInResponseDto;


public interface AuthService {

    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
