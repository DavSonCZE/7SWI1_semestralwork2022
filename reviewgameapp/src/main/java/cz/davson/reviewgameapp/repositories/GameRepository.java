package cz.davson.reviewgameapp.repositories;

import cz.davson.reviewgameapp.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {
    void deleteById(long gameId);
    Boolean existsByName(String name);
    Game getGameByName(String name);
}
