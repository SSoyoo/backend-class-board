package com.soyoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soyoo.board.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity , String>{
    
    public boolean existsByEmail(String email);
    public boolean existsByNickname(String nickname);
    public boolean existsByPhoneNumber(String phoneNumber);
    public UserEntity findByEmail(String email);
}
