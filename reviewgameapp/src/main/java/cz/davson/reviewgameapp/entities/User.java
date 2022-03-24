package cz.davson.reviewgameapp.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String surName;
    private String email;
    private String userName;
    private String password;

//    public User() {
//    }
}