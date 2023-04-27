package com.soyoo.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soyoo.board.dto.request.board.PatchBoardRequestDto;
import com.soyoo.board.dto.request.board.PostBoardRequest;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.dto.response.board.GetBoardListResponseDto;
import com.soyoo.board.dto.response.board.GetBoardResponseDto;
import com.soyoo.board.service.BoardService;

@RestController
@RequestMapping("/api/v1/board")

public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 작성
    @PostMapping("")
    public ResponseEntity<ResponseDto> postBoard(
            @Valid @RequestBody PostBoardRequest requestBody

    ) {
        ResponseEntity<ResponseDto> response = boardService.postBoard(requestBody);
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
        @RequestBody PatchBoardRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = boardService.patchBoard(requestBody);
        return response;
    }

    //특정게시물 삭제
    @DeleteMapping("/{userEmail}/{boardNumber}")
    public ResponseEntity<ResponseDto> deleteBoard(
        @PathVariable("userEmail") String UserEmail,
        @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity<ResponseDto> response = boardService.deleteBoard(UserEmail, boardNumber);
        return response;
    }


}
