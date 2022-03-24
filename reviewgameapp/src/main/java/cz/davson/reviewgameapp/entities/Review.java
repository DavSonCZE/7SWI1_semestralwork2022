package cz.davson.reviewgameapp.entities;


import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Builder
@Data
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Game game;
    private int score;
    @Type(type="text")
    private String reviewComment;
}
