package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Table(name = "game")
@NoArgsConstructor
public class Game {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @NonNull
    private String name;
    @Getter
    @Setter
    @NotNull
    private String developer;
    @Getter
    @Setter
    @NotNull
    private int releaseYear;
    @Getter
    @Setter
    @NotNull
    private String platform;
    @Getter
    @Setter
    @NotNull
    private int price;

    @Getter
    @Setter
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Collection<GameGenre> genres;


    public Game(@NonNull String name, String developer, int release, String platform, int price, @NonNull Collection<GameGenre> genres) {
        this.name = name;
        this.developer = developer;
        this.releaseYear = release;
        this.platform = platform;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", release=" + releaseYear +
                ", platform='" + platform + '\'' +
                ", price=" + price +
                ", genres=" + genres +
                '}';
    }
}








