package com.hit.controller;

import java.io.IOException;
import java.util.List;

import com.hit.dm.VideoGame;
import com.hit.services.GameService;

public class GameController {

	public GameController() {
		super();
	}

	public static  void AddNewGame (VideoGame i_game) throws IOException {
		GameService.addNewGame(i_game);
	}
	
	public static void DeleteGame (VideoGame i_game) throws IOException {
		GameService.deleteGame(i_game);
	}
	
	public static void RentNewGame (String i_gameTitle)  throws IOException {
		GameService.rentNewGame(i_gameTitle);
	}
	
	public static void ReturnGame (String i_gameTitle)  throws IOException {
		GameService.returnVideoGame(i_gameTitle);
	}
	public static  List<VideoGame> getAllGames()  throws IOException {
		return GameService.getAllGames();
	}
	public static  List<VideoGame> getAllRentedGames()  throws IOException {
		return GameService.getAllRentedGames();
	}
}
