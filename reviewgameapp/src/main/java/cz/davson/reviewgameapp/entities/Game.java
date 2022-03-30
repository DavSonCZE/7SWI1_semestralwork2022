package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/*@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor*/

@Entity
@Builder
@Data
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
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @NotNull
    private String gameName;

    @Getter
    @Setter
    @NotNull
    private String publisher;

    @Getter
    @Setter
    @NotNull
    private String developer;

    @Getter
    @Setter
    @NotNull
    private Date releaseDate;

    @Getter
    @Setter
    @NotNull
    private String versionOfGame;

    @Getter
    @Setter
    @NotNull
    private String gameGenre;

    @Getter
    @Setter
    @NotNull
    private String platformForGame;

    @Getter
    @Setter
    @NotNull
    private double priceForGame;

    @Getter
    @Setter
    @ManyToOne
    private ImageSource gameIcon;

//    public Game() {
//    }
}







