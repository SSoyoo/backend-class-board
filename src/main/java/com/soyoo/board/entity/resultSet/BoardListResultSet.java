package com.soyoo.board.entity.resultSet;

public interface BoardListResultSet {

    public int getboardNumber();
    public String getBoardTitle();
    public String getBoardContent();
    public String getBoardImageUrl();
    public String getBoardWriteDateTime();
    public int getViewCount();
    public String getBoardWriterEmail();
    public String getBoardWrterNickname();
    public String getBoardWriterImageUrl();
    public int getCommentCount();
    public int getLikeCount();

    
}
