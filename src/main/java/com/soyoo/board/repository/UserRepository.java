package com.soyoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.soyoo.board.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity , String>{
    
    public boolean exiexistsByEmail(String email);
    public boolean exiexistsByNickname(String nickname);
    public boolean exiexistsByPhoneNumber(String phoneNumber);
    
}
