package com.example.bgg.repositories;

import com.example.bgg.models.Game;
import com.example.bgg.models.GameSummary;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Repository
public class GameRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> getGames(int limit, int offset) {

        Query query = new Query();
        query.limit(limit);
        query.skip(offset);

        List<Game> games = mongoTemplate.find(query, Game.class, "game");

        return games;
    }

    public int getTotalGames() {

        Query query = new Query();
        int count = (int) mongoTemplate.count(query, "game");
        System.out.println("NUMBER OF GAMES>>>" + count);

        return count;
    }

    public List<Game> getGamesByRank(int limit, int offset) {

        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC,"ranking"));
        query.limit(limit);
        query.skip(offset);

        List<Game> games = mongoTemplate.find(query, Game.class, "game");

        return games;
    }

    public GameSummary getGameById(String game_id) {

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(game_id));

        Document document = mongoTemplate.findOne(query, Document.class, "game");

        System.out.println("DOCUMENT>>>>>>>>>>>>>>>>>>>>>>>>>" + document.toString());

        GameSummary game = new GameSummary();

        game.setGameId(String.valueOf(document.getObjectId("_id")));
        game.setName(document.getString("name"));
        game.setYear(document.getInteger("year"));
        game.setRanking(document.getInteger("ranking"));
        game.setUsersRated(document.getInteger("users_rated"));
        game.setUrl(document.getString("url"));
        game.setThumbnail(document.getString("image"));

        return game;

    }

    public List<GameSummary> getGameByName(String game_name) {

        Query query = new Query();
        Pattern pattern = Pattern.compile(game_name, Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("name").regex(pattern));

        List<Document> documents = mongoTemplate.find(query, Document.class, "game");

        List<GameSummary> gameSummaries = new LinkedList<>();

        for (Document document: documents) {
            GameSummary game = new GameSummary();

            game.setGameId(String.valueOf(document.getObjectId("_id")));
            game.setName(document.getString("name"));
            game.setYear(document.getInteger("year"));
            game.setRanking(document.getInteger("ranking"));
            game.setUsersRated(document.getInteger("users_rated"));
            game.setUrl(document.getString("url"));
            game.setThumbnail(document.getString("image"));

            gameSummaries.add(game);
        }

        return gameSummaries;
    }
}
