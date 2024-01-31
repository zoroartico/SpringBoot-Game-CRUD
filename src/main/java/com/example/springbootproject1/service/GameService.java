package com.example.springbootproject1.service;

import com.example.springbootproject1.entity.Game;
import com.example.springbootproject1.repository.GameRepository;
import com.example.springbootproject1.util.Validate;
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
        //validating title is not null and rating is withing range
        if ( valid.String(game.getTitle()) && valid.Range(game.getRating(),1.0,5.0) )
            message = ResponseEntity.ok("Success.\n"+ gameRepository.save(game));
        else
            message = ResponseEntity.badRequest().body("Failed. Title cannot be blank and Rating must be value 1-5");
        return message;
    }

    //updates game through gameRepo
    public ResponseEntity<?> updateGame(int gameId, Game updatedGame) {
        //validating game exists before attempting updating values
        Game existingGame = gameRepository.findById(gameId);
        if(valid.Object(existingGame)){
            //validating title and rating notnull and rating is within range before update
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
        //validates game exists before attempting delete
        Game deletingGame = gameRepository.findById(id);
        if (valid.Object(deletingGame)){
            gameRepository.delete(id);
            message = ResponseEntity.ok("Deleted.\n" + deletingGame);
        } else
            message = ResponseEntity.badRequest().body("Failed. Game not found.");
        return message;
    }

    //finds game through gameRepo by matching id
    public ResponseEntity<?> findById(int id) {
        Game foundGame = gameRepository.findById(id);
        if(valid.Object(foundGame))
            message = ResponseEntity.ok(foundGame);
        else
            message = ResponseEntity.badRequest().body("Game not found\n");
        return message;
    }
    //retrieves all games as list through gameRepo;
    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }
}
