package com.soyoo.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.entity.resultSet.BoardListResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetBoardListResponseDto extends ResponseDto {

    private List<BoardSummary> boardList;
    public GetBoardListResponseDto(List<BoardListResultSet> resultSet){
        super("SU", "Success");
        List<BoardSummary> boardList = new ArrayList<>();
        for(BoardListResultSet result : resultSet){
            BoardSummary boardSummary = new BoardSummary(result);
            boardList.add(boardSummary);
    
        }
        this.boardList = boardList;
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class BoardSummary{

    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImageUrl;
    private String boardLikeDatetime;
    private int viewCount;
    private String boardWriterEmail;
    private String boardWriterNickname;
    private String boardWriterImageUrl;
    private int commentCount;
    private int likeCount;

    public BoardSummary(BoardListResultSet resultSet){
        this.boardNumber = resultSet.getboardNumber();
        this.boardTitle = resultSet.getBoardTitle();
        this.boardContent = resultSet.getBoardContent();
        this.boardImageUrl = resultSet.getBoardImageUrl();
        this.boardLikeDatetime = resultSet.getBoardWriteDateTime();
        this.viewCount = resultSet.getViewCount();
        this.boardWriterEmail = resultSet.getBoardWriterEmail();
        this.boardWriterNickname = resultSet.getBoardWrterNickname();
        this.boardWriterImageUrl = resultSet.getBoardWriterImageUrl();
        this.commentCount = resultSet.getCommentCount();
        this.likeCount = resultSet.getLikeCount();
    }

}