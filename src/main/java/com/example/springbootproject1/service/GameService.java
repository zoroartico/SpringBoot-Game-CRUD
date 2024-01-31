package com.example.springbootproject1.service;

import com.example.springbootproject1.entity.Game;
import com.example.springbootproject1.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    private final Validate valid = new Validate();
    private ResponseEntity<?> message;

    //saves game through gameRepo
    public ResponseEntity<?> saveGame(Game game) {
        if ( valid.String(game.getTitle()) && valid.Range(game.getRating(),1.0,5.0) )
            message = ResponseEntity.ok("Success.\n"+gameRepository.save(game));
        else
            message = ResponseEntity.badRequest().body("Failed. Title cannot be blank and Rating must be value 1-5");
        return message;
    }

    public ResponseEntity<?> updateGame(int gameId, Game updatedGame) {
        Game existingGame = findById(gameId);
        if(valid.Object(existingGame)){
            if ( valid.String(updatedGame.getTitle()) && valid.Range(updatedGame.getRating(),1.0,5.0) ) {
                existingGame.setTitle(updatedGame.getTitle());
                existingGame.setRating(updatedGame.getRating());
                message = ResponseEntity.ok("Success.\n" + gameRepository.update(existingGame));
            } else
                message = ResponseEntity.badRequest().body("Failed. Title cannot be blank and Rating must be value 1-5");
        } else
            message = ResponseEntity.badRequest().body("Failed. Game not found.");
        return message;
    }
    //deletes game through gameRepo
    public ResponseEntity<?> deleteGame(int id) {
        if (valid.Object(findById(id))){
            gameRepository.delete(id);
            message = ResponseEntity.ok("Deleted.");
        } else
            message = ResponseEntity.badRequest().body("Failed. Game not found.");
        return message;
    }

    //finds game through gameRepo
    public Game findById(int id) {
        return gameRepository.findById(id);
    }
    //retrieves all games through gameRepo;
    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }
}
