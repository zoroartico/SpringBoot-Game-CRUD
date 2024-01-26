package com.example.springbootproject1.repository;
import com.example.springbootproject1.entity.Game;

import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    //used for incrementing gameId
    private int gameCounter=1;

    //instantiate game list
    private final List<Game> list = new ArrayList<>();

    //gets all games
    public List<Game> getAllGames() {
        return list;
    }

    //gets game
    public Game findById(int id) {
        for(Game g: list)
            if(g.getId() == id)
                return g;
        return null;
    }


    //saves games through post
    public Game save(Game game) {
        game.setId(gameCounter++);
        return game;
    }
}
