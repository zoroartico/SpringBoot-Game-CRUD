package com.example.springbootproject1.controller;

import com.example.springbootproject1.entity.Game;
import com.example.springbootproject1.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Routing all endpoints through /game
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // Add a new game
    @PostMapping("/add")
    public Game createGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }

    // Retrieve all games
    @GetMapping("/all")
    @Cacheable(value="gameInfo")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    // Retrieve a specific game by ID
    @GetMapping("/{gameId}")
    public Game getGameById(@PathVariable int gameId) {
        return gameService.findById(gameId);
    }

    // Update an existing game
    @PutMapping("/update/{gameId}")
    public Game updateGame(@PathVariable int gameId, @RequestBody Game updatedGame) {
        Game existingGame = gameService.findById(gameId);
        if (existingGame != null) {
            existingGame.setTitle(updatedGame.getTitle());
            existingGame.setRating(updatedGame.getRating());
            return gameService.saveGame(existingGame);
        }
         else {
            throw new RuntimeException("Game not found with id: " + gameId);
        }
    }

    // Delete a game by id
    @DeleteMapping("/delete/{gameId}")
    public String deleteGame(@PathVariable int gameId) {
        Game game = gameService.findById(gameId);
        if (game != null) {
            gameService.deleteGame(gameId);
            return "Game " + game.getTitle() + " has been deleted.";
        } else {
            throw new RuntimeException("Game not found with id: " + gameId);
        }
    }
}
