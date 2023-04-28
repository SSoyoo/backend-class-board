package com.soyoo.board.dto.request.board;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostBoardRequestDto {

    @Email
    @NotBlank
    private String boardWriterEmail;

    @NotBlank
    private String boardTitle;

    @NotBlank
    private String boardContent;
    private String boardImageUrl;
    
}
