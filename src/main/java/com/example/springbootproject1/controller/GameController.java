package com.example.springbootproject1.controller;

import com.example.springbootproject1.entity.Game;
import com.example.springbootproject1.service.GameService;
import com.example.springbootproject1.service.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Routing all endpoints through /game
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;
    private final Validate valid = new Validate();

    // Add a new game
    @PostMapping("/add")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        //checks if game title is empty(this also will trigger if object is empty)
        if ( !valid.String(game.getTitle()) )
            return ResponseEntity.badRequest().body("Invalid title.");
        //checks if game rating is valid and within range
        if ( !( valid.Number(game.getRating()) &&
                valid.Range(game.getRating(),0.0,5.0)) )
            return ResponseEntity.badRequest().body("Rating must be between 1-5");
        return ResponseEntity.ok(gameService.saveGame(game));
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
