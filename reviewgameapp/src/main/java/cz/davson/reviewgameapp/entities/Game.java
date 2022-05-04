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
    @Column(name = "game_icon")
    private String gameIcon;

    @Getter
    @Setter
    @NonNull
    @Column(name = "game_name")
    private String gameName;

    @Getter
    @Setter
    @ManyToMany
    /*@JoinTable(
            name = "Game_has_Genre",
            joinColumns = @JoinColumn(name = "pk_fk_game_id", referencedColumnName = "pk_game_id"),
            inverseJoinColumns = @JoinColumn(name = "pk_fk_game_genre_id", referencedColumnName = "pk_game_genre_id")
    )*/
    private List<GameGenre> gameGenres;

    @Getter
    @Setter
    @NonNull
    @Column(name = "release_date")
    private Date releaseDate;

    @Getter
    @Setter
    @NonNull
    @Column(name = "developer")
    private String developer;

    @Getter
    @Setter
    @NonNull
    @Column(name = "publisher")
    private String publisher;

    @Getter
    @Setter
    @NonNull
    @Column(name = "platform")
    private String platform;

    @Getter
    @Setter
    @NonNull
    @Column(name = "price")
    private double price;

    public Game(String gameIconPath, @NonNull String gameName, List<GameGenre> gameGenres, @NonNull Date releaseDate, @NonNull String developer, @NonNull String publisher, @NonNull String platform, @NonNull double price) {
        this.gameIcon = gameIcon;
        this.gameName = gameName;
        this.gameGenres = gameGenres;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.publisher = publisher;
        this.platform = platform;
        this.price = price;
    }
}







