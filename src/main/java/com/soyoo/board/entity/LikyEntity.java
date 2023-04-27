package com.soyoo.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.soyoo.board.entity.primaryKey.LikyPK;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Liky")
@Table(name = "Liky")
@IdClass(LikyPK.class)
public class LikyEntity {
    
    @Id
    private int boardNumber;
    @Id
    private String userEmail;
    
}
