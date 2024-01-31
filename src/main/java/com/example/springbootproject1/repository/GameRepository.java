package com.example.springbootproject1.repository;
import com.example.springbootproject1.entity.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {

    //used for incrementing gameId on create
    private int gameCounter=1;

    //initializes game list
    private final List<Game> list = new ArrayList<Game>();

    //returns game list
    public List<Game> getAllGames() {
        return list;
    }

    //using JavaStreams to filter through game list and return matching game
    //if no matching game, returns a null object
    public Game findById(int id) {
        return list.stream()
                .filter(g -> g.getId() == id)
                .findAny()
                .orElse(null);
    }

    //sets Id to incrementing counter, then adds to List<Game> list
    //returns game object to display
    public Game save(Game game) {
        game.setId(gameCounter++);
        list.add(game);
        return game;
    }

    //on update adds game with existing id to list
    public Game update(Game game) { list.add(game); return game; }

    //deletes game where id matches supplied through endpoint as arg
    public void delete(int id){
        list.removeIf(game -> game.getId() == id);
    }
}
