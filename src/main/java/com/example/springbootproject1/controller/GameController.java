package com.example.springbootproject1.controller;

import com.example.springbootproject1.entity.Game;
import com.example.springbootproject1.service.GameService;
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

    // Retrieve a specific game by ID
    @GetMapping("/{gameId}")
    public Game getGameById(@PathVariable int gameId) {
        return gameService.findById(gameId);
    }

    // Retrieve all games
    @GetMapping("/all")
    @Cacheable(value="gameInfo")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    // Add a new game
    @PostMapping("/add")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }

    // Update an existing game
    @PutMapping("/update/{gameId}")
    public ResponseEntity<?> updateGame(@PathVariable int gameId, @RequestBody Game updatedGame) {
        return gameService.updateGame(gameId, updatedGame);
    }

    // Delete a game by id
    @DeleteMapping("/delete/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable int gameId) {
        return gameService.deleteGame(gameId);
    }
}
