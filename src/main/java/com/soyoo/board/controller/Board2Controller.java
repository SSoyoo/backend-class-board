package com.soyoo.board.controller;

import javax.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soyoo.board.dto.request.board.PatchBoardRequestDto;
import com.soyoo.board.dto.request.board2.PatchBoardRequestDto2;
import com.soyoo.board.dto.request.board2.PostBoardRequestDto2;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.dto.response.board.GetBoardListResponseDto;
import com.soyoo.board.dto.response.board.GetBoardResponseDto;
import com.soyoo.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/board")
@RequiredArgsConstructor

public class Board2Controller {

    private final BoardService boardService;


    // 게시글 작성
    @PostMapping("")
    public ResponseEntity<ResponseDto> postBoard(
            @Valid @RequestBody PostBoardRequestDto2 requestBody,
            @AuthenticationPrincipal String userEmail

    ) {
        ResponseEntity<ResponseDto> response = boardService.postBoard(userEmail, requestBody);
        return response;

    }


    // 특정게시물 조회
    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    //게시물목록 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(

    ){
        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardList();
        return response;
    }

    //top3 게시물조회
    @GetMapping("/top3")
    public ResponseEntity<? super GetBoardListResponseDto> getboardTop3(){

        ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoardTop3();
        return response;
    }

    //특정게시물 수정
    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchBoard(
        @AuthenticationPrincipal String userEmail,
        @RequestBody PatchBoardRequestDto2 requestBody
    ){
        ResponseEntity<ResponseDto> response = boardService.patchBoard(userEmail, requestBody);
        return response;
    }

    

    //특정게시물 삭제
    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<ResponseDto> deleteBoard(
        @AuthenticationPrincipal String UserEmail,
        @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity<ResponseDto> response = boardService.deleteBoard(UserEmail, boardNumber);
        return response;
    }


}
