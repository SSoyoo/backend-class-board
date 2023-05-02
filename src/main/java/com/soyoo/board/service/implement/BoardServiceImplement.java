package com.soyoo.board.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soyoo.board.common.util.CustomResponse;
import com.soyoo.board.dto.request.board.PatchBoardRequestDto;
import com.soyoo.board.dto.request.board.PostBoardRequestDto;
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
    public ResponseEntity<ResponseDto> postBoard(PostBoardRequestDto dto) {

        ResponseDto body = null;

        String boardWriteEmail = dto.getBoardWriterEmail();

        try {
            // 존재하지 않는 유저 오류 반환
            boolean existedUserEmail = userRepository.existsByEmail(boardWriteEmail);
            if (!existedUserEmail) {
                ResponseDto errorBody = new ResponseDto("NU", "NonExistent User Email");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);

            }

            BoardEntity boardEntity = new BoardEntity(dto);
            boardRepository.save(boardEntity);

            body = new ResponseDto("SU", "SUCESS");

        } catch (Exception exception) {
            // 데이터베이스 오류반환
            exception.printStackTrace();
            ResponseDto errorBody = new ResponseDto("DE", "DataBase Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }

        // 성공반환
        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

        GetBoardResponseDto body = null;

        try {

            // boardNumber null처리
            if (boardNumber == null) {

                return CustomResponse.validationFaild();
            }

            // 1. 존재하지 않는 게시물
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) {

                return CustomResponse.notExistBoardNumber();
            }

            int viewCount = boardEntity.getViewCount();
            boardEntity.setViewCount(++viewCount);
            boardRepository.save(boardEntity);

            String writerEmail = boardEntity.getWriterEmail();
            UserEntity userEntity = userRepository.findByEmail(writerEmail);
            List<CommentEntity> commentEntities = commentRepository.findByBoardNumber(boardNumber);
            List<LikyEntity> likyEntities = likyRepository.findByBoardNumber(boardNumber);

            body = new GetBoardResponseDto();

        } catch (Exception e) {
            e.printStackTrace();

            return CustomResponse.databaseError();
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

        int boardNumber = dto.getBoardNumber();
        String userEmail = dto.getUserEmail();
        String boardTitle = dto.getBoardTitle();
        String boardContent = dto.getBoardContent();
        String boardImageUrl = dto.getBoardImageUrl();

        try {
            // 1.존재하지 않는 게시물 번호 요청시의 반환

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null)
                return CustomResponse.notExistBoardNumber();

            // 2. 존재하지 않는 유저 이메일의 경우 반환
            boolean existedUserEmail = userRepository.existsByEmail(userEmail);
            if (!existedUserEmail)
                return CustomResponse.notExistUserNumber();

            // 3. 권한이 없는 유저의 경우 반환

            boolean equalWriter = boardEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter)
                return CustomResponse.noPermissions();

            // 바뀐내용으로 set, 이것도 보드 엔터티에서 작업해주는 게 깔끔함. 
            //보드 엔터티와 디티오를 넘겨주면 그걸 변경해주는 static 메소드 작성

            boardEntity.setTitle(boardTitle);
            boardEntity.setContent(boardContent);
            boardEntity.setBoardImageUrl(boardImageUrl);
            // 변경된 게시물 저장
            boardRepository.save(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBoard(String userEmail, Integer boardNumber) {

       ResponseDto body = null;
       try {
        if(boardNumber == null) return CustomResponse.validationFaild();
        //1. 존재하지 않는 게시물 번호 반환
        BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
        if(boardEntity == null) return CustomResponse.notExistBoardNumber();
        //2. 존재하지 않는 유저 이메일 반환
        boolean existedUserEmail = userRepository.existsByEmail(userEmail);
        if(!existedUserEmail) return CustomResponse.notExistUserEmail();
        //3. 권한 없음 반환 - > 작성자인지 아닌지
        boolean equalWriter = boardEntity.getWriterEmail().equals(userEmail);
        if(!equalWriter) return CustomResponse.noPermissions();

        commentRepository.deleteByBoardNumber(boardNumber);
        likyRepository.deleteByBoardNumber(boardNumber);

        boardRepository.delete(boardEntity);
        

       } catch (Exception e) {
        e.printStackTrace();
        return CustomResponse.databaseError();
       }

       return CustomResponse.success();
    }

}
