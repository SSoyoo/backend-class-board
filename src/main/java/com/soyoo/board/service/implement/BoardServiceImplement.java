package com.soyoo.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soyoo.board.dto.request.board.PatchBoardRequestDto;
import com.soyoo.board.dto.request.board.PostBoardRequest;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.service.BoardService;
import com.soyoo.board.dto.response.board.*;
import com.soyoo.board.entity.BoardEntity;
import com.soyoo.board.repository.BoardRepository;
import com.soyoo.board.repository.UserRepository;

@Service
public class BoardServiceImplement implements BoardService {

    @Autowired
    private UserRepository userRepository;
    private BoardRepository boardRepository;

    public BoardServiceImplement(
            UserRepository userRepository,
            BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public ResponseEntity<ResponseDto> postBoard(PostBoardRequest dto) {

        ResponseDto body = null;
        String boardWriteEmail = dto.getBoardWriterEmail();

        try {
            // TODO : 존재하지 않는 유저 오류 반환
            boolean existedUserEmail = userRepository.existsByEmail(boardWriteEmail);
            if (!existedUserEmail) {
                ResponseDto errorBody = new ResponseDto("NU", "NonExistent User Email");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);

            }

            BoardEntity boardEntity = new BoardEntity(dto);
            boardRepository.save(boardEntity);

            body = new ResponseDto("SU", "SUCESS");
            

        } catch (Exception exception) {
            // TODO : 데이터베이스 오류반환
            exception.printStackTrace();
            ResponseDto errorBody = new ResponseDto("DE", "DataBase Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }

        // TODO :성공반환
        return ResponseEntity.status(HttpStatus.OK).body(body);

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
