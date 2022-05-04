package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor

public class Review {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_review_id")
    private Long id;
    @Getter
    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Getter
    @Setter
    @NotNull
    @OneToOne
    @JoinColumn(name = "fk_game_id")
    private Game game;

    @Getter
    @Setter
    @NotNull
    @Column(name = "score")
    private int score;

    @Getter
    @Setter
    @Column(name = "review_comment")
    @Type(type="text")
    private String reviewComment;

    public Review(User user, Game game, int score, String reviewComment) {
        this.user = user;
        this.game = game;
        this.score = score;
        this.reviewComment = reviewComment;
    }
}


