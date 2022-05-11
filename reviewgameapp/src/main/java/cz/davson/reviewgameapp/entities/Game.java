package cz.davson.reviewgameapp.entities;

import lombok.*;


import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
public class Game {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_game_id")
    private Long id;
    @Getter
    @Setter
    @NonNull
    private String gameName;

    @Getter
    @Setter
    @NonNull
    private int releaseYear;
    @Getter
    @Setter
    @NonNull
    private String developer;
    @Getter
    @Setter
    @NonNull
    private String platform;
    @Getter
    @Setter
    @NonNull
    @Column(name = "price")
    private int price;

    @Getter
    @Setter
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Game_has_Genre",
            joinColumns = @JoinColumn(name = "pk_fk_game_id", referencedColumnName = "pk_game_id"),
            inverseJoinColumns =  @JoinColumn(name = "pk_fk_gamegenre_id", referencedColumnName = "pk_game_genre_id")
    )
    private Collection<GameGenre> genres;
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", releaseYear=" + releaseYear +
                ", developer='" + developer + '\'' +
                ", platform='" + platform + '\'' +
                ", price=" + price +
                ", genres=" + genres +
                '}';
    }
}







