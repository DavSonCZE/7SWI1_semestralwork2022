package cz.davson.reviewgameapp.entities;

import lombok.Builder;
import lombok.Data;

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
    @GeneratedValue
    private Long id;

    private String gameName;
    private String publisher;
    private String developer;
    private Date releaseDate;
    private String versionOfGame;
    private String gameGenre;
    private String platformForGame;
    private double priceForGame;
    @ManyToOne
    private ImageSource gameIcon;

//    public Game() {
//    }
}







