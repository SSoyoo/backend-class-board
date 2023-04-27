package com.soyoo.board.dto.response.board;

import java.util.List;

import com.soyoo.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class GetBoardListResponseDto extends ResponseDto {

    private List<BoardSummary> boardList;
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

}