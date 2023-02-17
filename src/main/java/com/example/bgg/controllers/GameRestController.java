package com.example.bgg.controllers;

import com.example.bgg.services.GameService;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/games")
@CrossOrigin()
public class GameRestController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<String> getGames(@RequestParam(defaultValue = "25") int limit,
                                           @RequestParam(defaultValue = "0") int offset){

        JsonObject result = gameService.getGames(limit, offset);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
    }

    @GetMapping ("/rank")
    public ResponseEntity<String> getGamesByRank(@RequestParam(defaultValue = "25") int limit,
                                                 @RequestParam(defaultValue = "0") int offset) {

        JsonObject result = gameService.getGamesByRank(limit,offset);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

    @GetMapping ("/game/{game_id}")
    public ResponseEntity<String> getGameById(@PathVariable String game_id){

        JsonObject result = gameService.getGameById(game_id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

    @GetMapping ("/{game_name}")
    public ResponseEntity<String> getGameByName(@PathVariable String game_name){

        JsonObject result = gameService.getGameByName(game_name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }
}
