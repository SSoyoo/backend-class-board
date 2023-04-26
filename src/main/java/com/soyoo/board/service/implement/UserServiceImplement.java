package com.soyoo.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soyoo.board.dto.request.user.PostUserRequestDto;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.entity.UserEntity;
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

        ResponseDto resposeBody = null;

        String email = dto.getUserEmail();   
        String nickname = dto.getUserNickname();
        String phoneNumber = dto.getUserPhoneNumber();
        
        //todo : 데이터베이스 오류 반환
       
        try {

            //todo : 이메일중복 반환
            boolean hasEmail = userRepository.existsByEmail(email);
        
            if(hasEmail) {
                resposeBody = new ResponseDto("EU", "existent User Email");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposeBody);
            } 

            //todo : 닉네임중복 반환 
            boolean hasNickname = userRepository.existsByNickname(nickname);
            if(hasNickname){
                resposeBody = new ResponseDto("EN", "existent User Nickname");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposeBody);

            }

            //todo : 휴대폰중복 반환

            boolean hasPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if(hasPhoneNumber) {
                resposeBody = new ResponseDto("EP", "Exist User PhoneNumber");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposeBody);
            }

            //유저레코드 삽입//
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
            resposeBody = new ResponseDto("su","sucess");
            
        } catch (Exception exception) {
            exception.printStackTrace();
            resposeBody = new ResponseDto("DE", "Database Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposeBody);
        }

        //todo : 성공반환
        return  ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
