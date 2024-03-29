package com.soyoo.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.soyoo.board.entity.LikyEntity;
import com.soyoo.board.entity.primaryKey.LikyPK;
@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPK > {
    List<LikyEntity> findByBoardNumber(int boardNumber);

    @Transactional
    void deleteByBoardNumber(int boardNumber);

}
