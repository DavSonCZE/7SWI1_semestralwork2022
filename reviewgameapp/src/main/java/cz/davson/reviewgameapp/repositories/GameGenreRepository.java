package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameGenreRepository extends JpaRepository<GameGenre, Long>, JpaSpecificationExecutor<GameGenre> {

    void deleteById(long genreId);
    Boolean existsByName(String name);
    GameGenre getGenreByName(String name);
}
