package com.soyoo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soyoo.board.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    public BoardEntity findByBoardNumber(int boardNumber);

    @Query(value = "select " +
            "B.board_number as boardNumber," +
            "B.title as boardTitle," +
            "B.content as boardContent," +
            "B.board_image_url as boardImageUrl," +
            "B.write_datetime as boardWriteDateTime," +
            "B.view_count as viewCount," +
            "U.email as boardWriterEmail," +
            "U.nickname as boardWriterNickname," +
            "U.profile_image_url as boardWriterProfileImageUrl," +
            "count(distinct c.comment_number) as commentCount," +
            "count(distinct l.user_email) as likeCount " +
            "from Board b, comment c, liky l, user u " +
            "where b.board_number = c.board_number " +
            "and b.board_number = l.board_number " +
            "and b.writer_email = u.email " +
            "group by b.board_number; " +
            "order by b.write_Datetime DESC;",
            nativeQuery = true
    )

    public List<BoardEntity> getList();
}
