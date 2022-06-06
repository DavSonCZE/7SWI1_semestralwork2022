package cz.davson.reviewgameapp.services;

import cz.davson.reviewgameapp.repositories.GameGenreRepository;
import cz.davson.reviewgameapp.repositories.GameRepository;
import cz.davson.reviewgameapp.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameServiceTest {

    private GameRepository gameRepository;
    private GameGenreRepository gameGenreRepository;
    private ReviewRepository reviewRepository;
    private GameService gameService;


    @BeforeEach
    void init(){
        gameRepository = mock(GameRepository.class);
        gameGenreRepository = mock(GameGenreRepository.class);
        reviewRepository = mock(ReviewRepository.class);

        gameService = new GameService(gameRepository,gameGenreRepository,reviewRepository);
    }

    @Test
    void gameExistedWithName(){
        String gameName = "Minecraft";
        when(gameRepository.existsByName(gameName)).thenReturn(true);
        Boolean answ= gameService.gameExistsByName(gameName);
        assertTrue(answ, "Game already exists in database!");
    }

    @Test
    void gameDoesntExistedWithName(){
        String gameName = "Rokoko";
        when(gameRepository.existsByName(gameName)).thenReturn(false);
        Boolean answ= gameService.gameExistsByName(gameName);
        assertFalse(answ, "Game already exists in database!");
    }
}