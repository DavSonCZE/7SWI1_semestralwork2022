package cz.davson.reviewgameapp.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Data
public class ImageSource {
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 128)
    private String path;
}
