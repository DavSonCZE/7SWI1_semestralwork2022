package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameGenreRepository extends JpaRepository<GameGenre, Long> {
}
