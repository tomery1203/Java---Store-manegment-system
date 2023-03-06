package com.hit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;
import com.hit.controller.GameController;
import com.hit.dm.VideoGame;
public class HandleRequest implements Runnable {

	
	private Socket m_clientSocket;
	//private GameController gameController;
	
	public HandleRequest(Socket clientSocket) {
		super();
		this.m_clientSocket = clientSocket;
	}


    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(m_clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(m_clientSocket.getOutputStream(), true);

            String jsonRequest = input.readLine(); 
            Gson gson = new Gson();
            Request request = gson.fromJson(jsonRequest, Request.class); 

           
            switch (request.getAction()) {
                case "addGame":
                	GameController.AddNewGame(request.getGame());
                    output.println("Game added successfully.");
                    break;
                case "deleteGame":
                	GameController.DeleteGame(request.getGame());
                    output.println("Game deleted successfully.");
                    break;
                case "rentGame":
                	GameController.RentNewGame(request.getGame().getName());
                    output.println("Game rented successfully.");
                    break;
                case "returnGame":
                	GameController.ReturnGame(request.getGame().getName());
                    output.println("Game returned successfully.");
                    break;

                case "getAllAvailableGames":
                	System.out.println("i got to the handler");
                    List<VideoGame> availableGames = GameController.getAllGames();
                    Response response = new Response(true, "Available games retrieved successfully.", availableGames);
                    String jsonResponse = gson.toJson(response);
                    output.println(jsonResponse);
                    break;
                case "getAllRentedAvailableGames":
                    List<VideoGame> rentedAvailableGames = GameController.getAllRentedGames();
                    Response rentedResponse = new Response(true, "Rented available games retrieved successfully.", rentedAvailableGames);
                    String rentedJsonResponse = gson.toJson(rentedResponse);
                    output.println(rentedJsonResponse);
                    break;

            }
            m_clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
