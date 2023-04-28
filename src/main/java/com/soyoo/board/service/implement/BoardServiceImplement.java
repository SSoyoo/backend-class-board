package com.soyoo.board.service.implement;

import java.util.List;

import javax.xml.bind.annotation.XmlElement.DEFAULT;
import javax.xml.stream.events.Comment;

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
import com.soyoo.board.entity.CommentEntity;
import com.soyoo.board.entity.LikyEntity;
import com.soyoo.board.entity.UserEntity;
import com.soyoo.board.repository.BoardRepository;
import com.soyoo.board.repository.CommentRepository;
import com.soyoo.board.repository.LikyRepository;
import com.soyoo.board.repository.UserRepository;

@Service
public class BoardServiceImplement implements BoardService {

    @Autowired
    private UserRepository userRepository;
    private BoardRepository boardRepository;
    private CommentRepository commentRepository;
    private LikyRepository likyRepository;

    public BoardServiceImplement(
            UserRepository userRepository,
            BoardRepository boardRepository,
            CommentRepository commentRepository,
            LikyRepository likyRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.likyRepository = likyRepository;
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

        GetBoardResponseDto body = null;
        ResponseDto errorBody = null;
        

        try {

            //boardNumber null처리
            if(boardNumber == null){
                errorBody = new ResponseDto("VF", "Request Parameter Validation Failed");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
            }

            //1. 존재하지 않는 게시물
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null){
                errorBody = new ResponseDto("NB", "Non-exist board number");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
            }
            String writerEmail = boardEntity.getWriterEmail();
            UserEntity userEntity = userRepository.findByEmail(writerEmail);
            List<CommentEntity> commentEntities = commentRepository.findByBoardNumber(boardNumber);
            List<LikyEntity> likyEntities = likyRepository.findByBoardNumber(boardNumber);
       
        } catch (Exception e) {
            e.printStackTrace();
            errorBody = new ResponseDto("DE", "Database Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
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
