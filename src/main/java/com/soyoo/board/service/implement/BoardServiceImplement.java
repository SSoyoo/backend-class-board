package com.soyoo.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soyoo.board.dto.request.board.PatchBoardRequestDto;
import com.soyoo.board.dto.request.board.PostBoardRequest;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.service.BoardService;
import com.soyoo.board.dto.response.board.*;

@Service
public class BoardServiceImplement implements BoardService {

    @Override
    public ResponseEntity<ResponseDto> postBoard(PostBoardRequest dto) {
   
        throw new UnsupportedOperationException("Unimplemented method 'postBoard'");
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
       
        throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {
     
        throw new UnsupportedOperationException("Unimplemented method 'getBoardList'");
    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardTop3() {
  
        throw new UnsupportedOperationException("Unimplemented method 'getBoardTop3'");
    }

    @Override
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto) {
      
        throw new UnsupportedOperationException("Unimplemented method 'patchBoard'");
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBoard(String userEmail, Integer boardNumber) {
      
        throw new UnsupportedOperationException("Unimplemented method 'deleteBoard'");
    }

  
}
