package cz.davson.reviewgameapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "review")
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Getter
    @Setter
    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_game_id")
    private Game game;

    @Getter
    @Setter
    @NotNull
    private int score;

    @Getter
    @Setter
    private String reviewComment;

    public Review(Long id, User user, Game game, int score, String reviewComment) {
        this.id = id;
        this.user = user;
        this.game = game;
        this.score = score;
        this.reviewComment = reviewComment;
    }
}


