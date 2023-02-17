package com.example.bgg.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.data.mongodb.core.mapping.Field;

public class Game {

    @Field(name = "gid")
    private String gameId;
    private String name;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
                .add("game_id", gameId)
                .add("name", name)
                .build();
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
