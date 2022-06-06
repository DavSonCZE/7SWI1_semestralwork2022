package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameGenreRepository extends JpaRepository<GameGenre, Long> {

    void deleteById(long genreId);
    Boolean existsByName(String name);
    GameGenre getGameGenreByName(String name);
}
