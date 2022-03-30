package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Builder
@Data
public class Review {
    @Id
    @Getter
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private User user;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private Game game;

    @Getter
    @Setter
    @NotNull
    private int score;

    @Getter
    @Setter
    @Type(type="text")
    private String reviewComment;
}
