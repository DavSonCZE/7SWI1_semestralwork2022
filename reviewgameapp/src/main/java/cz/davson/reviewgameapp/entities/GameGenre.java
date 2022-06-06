package cz.davson.reviewgameapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "gamegenre")
@NoArgsConstructor
public class GameGenre {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
    private Collection<Game> games;

    public GameGenre(@NonNull String name, Collection<Game> games) {
        this.name = name;
        this.games = games;
    }

    @Override
    public String toString() {
        return "GameGenre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", games=" + games +
                '}';
    }
}
