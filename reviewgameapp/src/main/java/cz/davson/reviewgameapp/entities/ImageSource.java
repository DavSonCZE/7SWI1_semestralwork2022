package cz.davson.reviewgameapp.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Data
public class ImageSource {
    @Id
    @Getter
    @GeneratedValue
    private long id;

    @Getter
    @Setter
    @NotNull
    @Column(length = 128)
    private String path;
}
