package com.example.bgg.services;

import com.example.bgg.models.Game;
import com.example.bgg.models.GameSummary;
import com.example.bgg.repositories.GameRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public JsonObject getGames(int limit, int offset) {

       List<Game> games = gameRepository.getGames(limit, offset);

       int count = gameRepository.getTotalGames();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Game game: games){
            arrayBuilder.add(game.toJson());
        }
        JsonArray gamesArray = arrayBuilder.build();

        return Json.createObjectBuilder()
                .add("games", gamesArray)
                .add("offset", offset)
                .add("limit", limit)
                .add("total", count)
                .add("timestamp", String.valueOf(LocalDateTime.now()))
                .build();

//        {
//            games: [ <array of games> ],
//            offset: <offset or default value>,
//                limit: <limit or default value>,
//                total: <total number of games>,
//            timestamp: <result timestamp>
//        }

    }

    public JsonObject getGamesByRank(int limit, int offset) {

        List<Game> games = gameRepository.getGamesByRank(limit,offset);

        int count = gameRepository.getTotalGames();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Game game: games){
            arrayBuilder.add(game.toJson());
        }
        JsonArray gamesArray = arrayBuilder.build();

        return Json.createObjectBuilder()
                .add("games", gamesArray)
                .add("offset", offset)
                .add("limit", limit)
                .add("total", count)
                .add("timestamp", String.valueOf(LocalDateTime.now()))
                .build();
    }

    public JsonObject getGameById(String game_id) {

       GameSummary game = gameRepository.getGameById(game_id);

       return Json.createObjectBuilder()
               .add("game_id", game.getGameId())
               .add("name", game.getName())
               .add("year", game.getYear())
               .add("ranking", game.getRanking())
               .add("average", game.getAverage())
               .add("users_rated", game.getUsersRated())
               .add("url", game.getUrl())
               .add("thumbnail", game.getThumbnail())
               .add("timestamp", String.valueOf(LocalDateTime.now()))
               .build();
    }

    public JsonObject getGameByName(String game_name) {

        List<GameSummary> games = gameRepository.getGameByName(game_name);


        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (GameSummary game: games){
            arrayBuilder.add(game.toJson());
        }
        JsonArray gamesArray = arrayBuilder.build();

        return Json.createObjectBuilder()
                .add("result", gamesArray)
                .build();

    }
}
