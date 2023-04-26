package com.soyoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.soyoo.board.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity , String>{
    
    public boolean existsByEmail(String email);
    public boolean existsByNickname(String nickname);
    public boolean existsByPhoneNumber(String phoneNumber);
    
}
