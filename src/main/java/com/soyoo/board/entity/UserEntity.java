package com.soyoo.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String consentPersonalInformation;
    private String profileImageUrl;

    
}
