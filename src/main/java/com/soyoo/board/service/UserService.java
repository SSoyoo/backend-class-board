package com.soyoo.board.service;

import org.springframework.http.ResponseEntity;

import com.soyoo.board.dto.request.user.PostUserRequestDto;
import com.soyoo.board.dto.response.ResponseDto;

public interface UserService {

    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto);
    
}
