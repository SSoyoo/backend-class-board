package com.soyoo.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.soyoo.board.dto.request.auth.SignUpRequestDto;
import com.soyoo.board.dto.request.user.PostUserRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {

    @Id
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String address;
    private boolean consentPersonalInformation;
    private String profileImageUrl;

        public UserEntity(PostUserRequestDto dto) {
            
            this.email = dto.getUserEmail();
            this.password = dto.getUserPassword();
            this.nickname=dto.getUserNickname();
            this.phoneNumber = dto.getUserPhoneNumber();
            this.address = dto.getUserAddress();
            this.consentPersonalInformation = true;
            this.profileImageUrl = dto.getUserProfileImageUrl();


        }

        public UserEntity(SignUpRequestDto dto) {
            
            this.email = dto.getUserEmail();
            this.password = dto.getUserPassword();
            this.nickname=dto.getUserNickname();
            this.phoneNumber = dto.getUserPhoneNumber();
            this.address = dto.getUserAddress();
            this.consentPersonalInformation = true;
            this.profileImageUrl = dto.getUserProfileImageUrl();


        }



}
