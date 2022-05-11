package cz.davson.reviewgameapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "game_genre")
@NoArgsConstructor
@RequiredArgsConstructor
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
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
    private Collection<Game> movies;

    @Override
    public String toString() {
        return "GameGenre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}


