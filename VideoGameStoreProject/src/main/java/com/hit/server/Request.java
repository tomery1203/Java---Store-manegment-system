package com.hit.server;

import com.google.gson.Gson;
import com.hit.dm.VideoGame;

public class Request {
    private String action;
    private VideoGame game;

    public Request(String action, VideoGame game) {
        this.action = action;
        this.game = game;
    }

    public String getAction() {
        return action;
    }

    public VideoGame getGame() {
        return game;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Request fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Request.class);
    }
}