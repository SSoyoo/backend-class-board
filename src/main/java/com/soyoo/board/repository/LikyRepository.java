package com.soyoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soyoo.board.entity.LikyEntity;
import com.soyoo.board.entity.primaryKey.LikyPK;
@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPK > {
    
}
