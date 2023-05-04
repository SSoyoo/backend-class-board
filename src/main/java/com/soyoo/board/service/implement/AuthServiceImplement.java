package com.soyoo.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.soyoo.board.common.util.CustomResponse;
import com.soyoo.board.dto.request.auth.SignInRequestDto;
import com.soyoo.board.dto.request.auth.SignUpRequestDto;
import com.soyoo.board.dto.response.ResponseDto;
import com.soyoo.board.dto.response.auth.SignInResponseDto;
import com.soyoo.board.repository.UserRepository;
import com.soyoo.board.service.AuthService;

import net.bytebuddy.asm.Advice.Return;

@Service
public class AuthServiceImplement implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickname();
        String PhoneNumner = dto.getUserPhoneNumber();
        try {

            // 존재하는 유저 이메일
            boolean existUserEmail = userRepository.existsByEmail(email);
            if (existUserEmail)
                return CustomResponse.existUserEmail();
            // 존재하는 유저 닉네임
            boolean existUserNickname = userRepository.existsByNickname(nickname);
            if (existUserNickname)
                return CustomResponse.existUserNickname();
            // 존재하는 유저 휴대폰번호
            boolean existUserPhoneNumber = userRepository.existsByPhoneNumber(PhoneNumner);
            if (existUserPhoneNumber)
                return CustomResponse.existUserPhoneNumber();

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

    }

}
