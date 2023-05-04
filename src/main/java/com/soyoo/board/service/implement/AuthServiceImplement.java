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

import net.bytebuddy.asm.Advice.Return;

@Service
public class AuthServiceImplement implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    @Autowired
    public AuthServiceImplement(

            UserRepository userRepository,
            JwtProvider jwtProvider

    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickname();
        String PhoneNumner = dto.getUserPhoneNumber();
        String password = dto.getUserPassword();
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

            String encodedPassword = passwordEncoder.encode(password);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
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

            // 로그인 실패 (1) 이메일 없음
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null)
                return CustomResponse.signInFailed();
            // 로그인 실패 (2) 비밀번호 틀린 경우
            String encodedPassword = userEntity.getPassword();
            boolean equaledPassword = passwordEncoder.matches(password, encodedPassword);
            if (!equaledPassword) return CustomResponse.signInFailed();

            String jwt = jwtProvider.create(email);
            body = new SignInResponseDto(jwt);


        }

        catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

}
