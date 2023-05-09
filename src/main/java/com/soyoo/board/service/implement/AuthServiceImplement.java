package com.soyoo.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soyoo.board.common.util.CustomResponse;
import com.soyoo.board.dto.request.auth.SignInRequestDto;
import com.soyoo.board.dto.request.auth.SignUpRequestDto;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.dto.response.auth.SignInResponseDto;
import com.soyoo.board.entity.UserEntity;
import com.soyoo.board.povider.JwtProvider;
import com.soyoo.board.repository.UserRepository;
import com.soyoo.board.service.AuthService;

@Service
public class AuthServiceImplement implements AuthService {
    
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImplement(
        UserRepository userRepository,
        JwtProvider jwtProvider
    ) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickname();
        String phoneNumber = dto.getUserPhoneNumber();
        String password = dto.getUserPassword();

        try {
            //* 존재하는 유저 이메일 반환 
            boolean existedUserEmail = userRepository.existsByEmail(email);
            if (existedUserEmail) return CustomResponse.existUserEmail();

            //* 존재하는 유저 닉네임 반환 
            boolean existedUserNickname = userRepository.existsByNickname(nickname);
            if (existedUserNickname) return CustomResponse.existUserNickname();

            //* 존재하는 휴대폰 번호 반환 
            boolean existedUserPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if (existedUserPhoneNumber) return CustomResponse.existUserPhoneNumber();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        SignInResponseDto body = null;

        String email = dto.getUserEmail();
        String password = dto.getUserPassword();

        try {
            //* 로그인 실패 반환 (이메일 X) 
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return CustomResponse.signInFailed();

            //* 로그인 실패 반환 (패스워드 X) 
            String encordedPassword = userEntity.getPassword();
            boolean equaledPassword = passwordEncoder.matches(password, encordedPassword);
            if (!equaledPassword) return CustomResponse.signInFailed();

            String jwt = jwtProvider.create(email);
            body = new SignInResponseDto(jwt);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

}