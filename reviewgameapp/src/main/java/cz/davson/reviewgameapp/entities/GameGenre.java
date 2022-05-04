package cz.davson.reviewgameapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(name = "game_genre")
@NoArgsConstructor
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

    public GameGenre(@NonNull String name) {
        this.name = name;
    }


}


