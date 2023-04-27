package com.soyoo.board.service;

import org.springframework.http.ResponseEntity;

import com.soyoo.board.dto.request.board.PatchBoardRequestDto;
import com.soyoo.board.dto.request.board.PostBoardRequest;
import com.soyoo.board.dto.response.board.*;
import com.soyoo.board.dto.response.ResponseDto;


public interface BoardService {

    public ResponseEntity<ResponseDto> postBoard(PostBoardRequest dto);
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList();
    public ResponseEntity<? super GetBoardListResponseDto> getBoardTop3();
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto);
    public ResponseEntity<ResponseDto> deleteBoard(String userEmail, Integer boardNumber);




}
