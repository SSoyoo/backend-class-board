package com.soyoo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soyoo.board.entity.CommentEntity;
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
    
    List<CommentEntity> findByBoardNumber(int boardNumber); // pk도 유니크도 아니라서 리스트반환
}
