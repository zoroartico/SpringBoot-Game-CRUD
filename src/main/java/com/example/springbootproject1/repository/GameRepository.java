package com.example.springbootproject1.repository;
import com.example.springbootproject1.entity.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {

    //used for incrementing gameId
    private int gameCounter=1;

    //gets game list
    private final List<Game> list = new ArrayList<>();

    //gets all games
    public List<Game> getAllGames() {
        return list;
    }

    //gets game by id in endpoint
    public Game findById(int id) {
        return list.stream()
                .filter(g -> g.getId() == id)
                .findAny()
                .orElse(null);
    }

    //saves games through post
    public Game save(Game game) {
        game.setId(gameCounter++);
        return game;
    }

    //deletes game through post
    public void delete(int id){
        list.removeIf(game -> game.getId() == id);
    }
}
