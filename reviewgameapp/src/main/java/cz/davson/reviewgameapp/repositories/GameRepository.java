package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
