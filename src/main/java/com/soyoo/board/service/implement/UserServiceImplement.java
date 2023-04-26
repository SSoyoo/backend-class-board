package com.soyoo.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soyoo.board.dto.request.user.PostUserRequestDto;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.repository.UserRepository;
import com.soyoo.board.service.UserService;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;
    public UserServiceImplement(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto){
        
        ResponseEntity<ResponseDto> result = null;
        ResponseDto resposeBody;
        String email = dto.getUserEmail();   
        String nickname = dto.getUserNickname();
        String phoneNumber = dto.getUserPhoneNumber();
        
        //todo : 데이터베이스 오류
       
        try {
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
       

        //todo : 이메일중복 반환
        boolean hasEmail = userRepository.exiexistsByEmail(email);
       
        if(hasEmail) {
            resposeBody = new ResponseDto("EU", "existent User Email");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposeBody);
        } 

        //todo : 닉네임중복 반환 
        boolean hasNickname = userRepository.exiexistsByNickname(nickname);
        if(hasNickname){
            resposeBody = new ResponseDto("EN", "existent User Nickname");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposeBody);

        }

        //todo : 휴대폰중복 반환

        boolean hasPhoneNumber = userRepository.exiexistsByPhoneNumber(phoneNumber);
        if(hasPhoneNumber) {
            resposeBody = new ResponseDto("EP", "존재하는 번호");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposeBody);
        }
        
        //todo : 성공반환
        return result;
    


    }
    
}
