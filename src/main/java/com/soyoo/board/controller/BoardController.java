package com.soyoo.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soyoo.board.dto.request.board.PostBoardRequest;
import com.soyoo.board.dto.response.ResponseDto;
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

}
