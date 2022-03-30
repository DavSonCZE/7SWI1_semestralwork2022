package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
public class ImageSource {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "game_icon_path", length = 128)
    private String path;

    public ImageSource(String path) {
        this.path = path;
    }
}


