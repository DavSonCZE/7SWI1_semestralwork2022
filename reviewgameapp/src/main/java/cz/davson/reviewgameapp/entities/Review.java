package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "review")
@NoArgsConstructor
public class Review {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Review( User user, Game game, int score, String reviewComment) {
        this.user = user;
        this.game = game;
        this.score = score;
        this.reviewComment = reviewComment;
    }
}
