package cz.davson.reviewgameapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Builder
@Data
public class GameGenre {

    @Id
    @Getter
    @GeneratedValue
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
}
