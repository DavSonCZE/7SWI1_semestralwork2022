package cz.davson.reviewgameapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@RequiredArgsConstructor
@Table(name = "game_genre")
public class GameGenre {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_game_genre_id")
    private long id;

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @JsonBackReference
    @ManyToMany
    private Collection<Game> games;

    public GameGenre(@NonNull String name, Collection<Game> games) {
        this.name = name;
        this.games = games;
    }
}


