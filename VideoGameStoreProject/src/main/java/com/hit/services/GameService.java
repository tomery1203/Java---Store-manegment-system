package com.hit.services;

import java.io.IOException;
import java.util.List;

import com.hit.dao.VideoGameFileDao;
import com.hit.dm.VideoGame;
//import com.hit.dm.GamePlatformEnum;


public class GameService {
	
	public GameService( ) throws IOException {
		super();
	}
	
	public static  void addNewGame(VideoGame i_videoGame) throws IOException {
		VideoGameFileDao dao    = new VideoGameFileDao ();
		try {
			dao.saveNewGame(i_videoGame);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	public static void deleteGame(VideoGame i_videoGame) throws IOException {
		VideoGameFileDao dao    = new VideoGameFileDao ();
		try {
			dao.deleteNewGame(i_videoGame);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	public static  void rentNewGame(String i_gameTitle) throws IOException {
		VideoGameFileDao dao    = new VideoGameFileDao ();
		try {
			dao.rent(i_gameTitle);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	public  static void returnVideoGame(String i_gameTitle) throws IOException {
		VideoGameFileDao dao    = new VideoGameFileDao ();
		try {
			dao.reeturn(i_gameTitle);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	public static List<VideoGame> getAllGames() throws IOException {
	    VideoGameFileDao dao = new VideoGameFileDao();
	    return dao.read(dao.getAvailblePath());
	}

	public static List<VideoGame> getAllRentedGames() throws IOException {
		VideoGameFileDao dao = new VideoGameFileDao();
	    return dao.read(dao.getRentedAvailblePath());
	}
	}
