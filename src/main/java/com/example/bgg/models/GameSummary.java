package com.example.bgg.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.time.LocalDateTime;

public class GameSummary {

    private String gameId;
    private String name;
    private int year;
    private int ranking;
    private int average;
    private int usersRated;
    private String url;
    private String thumbnail;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(int usersRated) {
        this.usersRated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
                .add("game_id", gameId)
                .add("name", name)
                .add("year", year)
                .add("ranking", ranking)
                .add("average", average)
                .add("users_rated", usersRated)
                .add("url", url)
                .add("thumbnail", thumbnail)
                .build();
    }
}
