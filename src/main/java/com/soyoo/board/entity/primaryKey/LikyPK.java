package com.soyoo.board.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class LikyPK implements Serializable {

    @Column(name = "board_number")
    private int board_number;
    @Column(name = "user_email")
    private String user_email;
    
}
