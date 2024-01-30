package com.example.springbootproject1.service;

import com.example.springbootproject1.entity.Game;
import com.example.springbootproject1.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    //saves game through gameRepo
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Game game) { return gameRepository.update(game); }
    //deletes game through gameRepo
    public void deleteGame(int id) { gameRepository.delete(id); }
    //finds game through gameRepo
    public Game findById(int id) {
        return gameRepository.findById(id);
    }
    //retrieves all games through gameRepo;
    public List<Game> getAllGames() {
        return gameRepository.getAllGames();
    }
}
