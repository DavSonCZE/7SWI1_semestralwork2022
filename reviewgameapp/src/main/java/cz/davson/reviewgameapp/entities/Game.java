package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/*@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor*/

@Entity
@Table(name = "game")
@Data
@NoArgsConstructor
/*@Builder
@Data*/
public class Game {

    /*public enum GameVersion{
        Action, ActionAndAdventure, Adventure, Roleplaying, Simulator, Strategy, Sport, Puzzle, Idle
    }

    public enum GameGenre{
        Alpha, Beta, Demo, EarlyAccess, FullRealise
    }

    public enum Platform{
        PC, Android, MacOS, Linux, PS4, PS5, Switch
    }*/

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_game_id")
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @Column(name = "game_icon")
    private ImageSource gameIcon;

    @Getter
    @Setter
    @NonNull
    @Column(name = "game_name")
    private String gameName;

    @Getter
    @Setter
    @NonNull
    @JoinTable(
            name = "Game_has_Genre",
            joinColumns = @JoinColumn(name = "pk_fk_game_id", referencedColumnName = "pk_game_id"),
            inverseJoinColumns = @JoinColumn(name = "pk_fk_game_genre_id", referencedColumnName = "pk_game_genre_id")
    )
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

    public Game(ImageSource gameIcon, @NonNull String gameName, @NonNull Collection<GameGenre> gameGenres, @NonNull Date releaseDate, @NonNull String developer, @NonNull String publisher, @NonNull String platform, @NonNull double price) {
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







