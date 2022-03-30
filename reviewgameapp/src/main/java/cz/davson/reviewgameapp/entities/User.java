package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Data
public class User {
    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @NotNull
    private String firstName;

    @Getter
    @Setter
    @NotNull
    private String surName;

    @Getter
    @Setter
    @NotNull
    private String email;

    @Getter
    @Setter
    @NotNull
    private String userName;

//    public User() {
//    }
}