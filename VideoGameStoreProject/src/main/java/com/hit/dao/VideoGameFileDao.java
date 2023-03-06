package com.hit.dao;

import java.io.BufferedReader;
import com.hit.algorithm.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.hit.algorithm.IAlgoStringSearching;
import com.hit.algorithm.KMP;
import com.hit.dm.VideoGame;
import com.google.gson.*;

public class VideoGameFileDao implements Dao<Long, VideoGame> {
	private String m_rentedPath = "Rented.json";
	private String m_availblePath = "AvailbleGames.json";
	private KMP m_KMP;

	
	public VideoGameFileDao() throws IOException {}

	public List<VideoGame> read(String i_path) {
	    List<VideoGame> videogames = new ArrayList<>();
	    try (FileReader fr = new FileReader(i_path)) {
	        BufferedReader br = new BufferedReader(fr);
	        String line;
	        while ((line = br.readLine()) != null) {
	            VideoGame temp = new Gson().fromJson(line, VideoGame.class);
	            videogames.add(temp);
	        }
	        br.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return videogames;
	}

	public void saveNewGame(VideoGame i_videoGame ) {
	    try (FileWriter fw = new FileWriter(getAvailblePath(), true)) {
	        Gson gson = new GsonBuilder().create();
	        gson.toJson(i_videoGame, fw);
	        fw.write("\n"); // add a new line character after the game object
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void rentNewGame(VideoGame i_videoGame ) {
	    try (FileWriter fw = new FileWriter(m_rentedPath, true)) {
	        Gson gson = new GsonBuilder().create();
	        gson.toJson(i_videoGame, fw);
	        fw.write("\n"); // add a new line character after the game object
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void deleteNewGame(VideoGame i_videoGame) {
	    try (FileReader fileReader = new FileReader(getAvailblePath())) {
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        StringBuilder stringBuilder = new StringBuilder();
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	            VideoGame currentGame = new Gson().fromJson(line, VideoGame.class);
	            if (!currentGame.getName().equals(i_videoGame.getName())) {
	                stringBuilder.append(line).append("\n");
	            }
	        }
	        bufferedReader.close();
	        try (FileWriter fileWriter = new FileWriter(getAvailblePath())) {
	            fileWriter.write(stringBuilder.toString());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void returnNewGame(VideoGame i_videoGame) {
	    try (FileReader fileReader = new FileReader(m_rentedPath)) {
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        StringBuilder stringBuilder = new StringBuilder();
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	            VideoGame currentGame = new Gson().fromJson(line, VideoGame.class);
	            if (!currentGame.getName().equals(i_videoGame.getName())) {
	                stringBuilder.append(line).append("\n");
	            }
	        }
	        bufferedReader.close();
	        try (FileWriter fileWriter = new FileWriter(m_rentedPath)) {
	            fileWriter.write(stringBuilder.toString());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void save(VideoGame i_videoGame,String i_path) {
	    List<VideoGame> videogames = this.read(i_path); // read in existing video games
	    videogames.add(i_videoGame); // add the new video game to the list
	    this.write(videogames,i_path); // write the updated list back to the file
	}

	public void write(List<VideoGame> i_videoGames, String i_path) {
	    try (JsonWriter writer = new JsonWriter(new FileWriter(i_path))) {
	        Gson gson = new GsonBuilder().create();
	        writer.setIndent("  ");
	        writer.beginArray();
	        for (VideoGame game : i_videoGames) {
	            gson.toJson(game, VideoGame.class, writer);
	        }
	        writer.endArray();
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    public void delete (VideoGame i_videoGame, String i_path) {
        List<VideoGame> videogames = this.read(i_path); // read in existing video games
        videogames.remove(i_videoGame); // remove the specified video game from the list
        this.write(videogames,i_path); // write the updated list back to the file
    }
	
	public void rent (String i_gameTitle) {
		VideoGame videogame = this.findVideogameByTitle(i_gameTitle,getAvailblePath());
        if (videogame == null) {
            return;
        }
       this.deleteNewGame(videogame);
       this.rentNewGame(videogame);
	}
	
	public void reeturn (String i_gameTitle) {
		VideoGame videogame = this.findVideogameByTitle(i_gameTitle,m_rentedPath);
        if (videogame == null) {
            return;
        }
       this.returnNewGame(videogame);
        this.saveNewGame(videogame);
	}
	
	private VideoGame findVideogameByTitle (String i_stringToSearch, String i_path) {
        List<VideoGame> videogames = this.read(i_path);
        this.m_KMP = new KMP ();
        for (VideoGame videogame : videogames) {
            if (m_KMP.search(videogame.getName(), i_stringToSearch )!= -1) {
                return videogame;
                }
            }
        return null;
	}

	public String getAvailblePath() {
		return m_availblePath;
	}

	public String getRentedAvailblePath() {
		return m_rentedPath;
	}
}

