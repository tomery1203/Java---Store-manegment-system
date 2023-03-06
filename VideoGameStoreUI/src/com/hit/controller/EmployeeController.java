package com.hit.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.VideoGame;
import com.hit.model.Employee;
import com.hit.server.Request;
import com.hit.server.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import com.google.gson.*;
public class EmployeeController {

    public EmployeeController() {}

    public void handleAvailableGamesButton(ActionEvent i_event, TableView<VideoGame> i_gameTable) {
        try {
            // Create a new Employee object
            Employee employee = new Employee();

            // Set up the request to get all available games
            Request request = new Request("getAllAvailableGames", null);

            // Send the request to the server
            Socket socket = employee.getM_socket();
            socket.setSoTimeout(5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Gson gson = new Gson();
            String json = gson.toJson(request);
            out.println(json);

            // Receive the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String jsonResponse = in.readLine();
            Response response = gson.fromJson(jsonResponse, Response.class);

            if (response.isSuccess()) {
                // Get the list of available games from the response
                List<VideoGame> availableGames = response.getData();
                // Update the observable list containing the game data
                ObservableList<VideoGame> gameData = FXCollections.observableArrayList(availableGames);
                // Set the updated game data as the new items for the TableView
                i_gameTable.setItems(gameData);
            } else {
                System.out.println("Failed to get available games: " + response.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRentedGamesButton(ActionEvent i_event, TableView<VideoGame> i_gameTable) {
    	   try {
               // Create a new Employee object
               Employee employee = new Employee();

               // Set up the request to get all available games
               Request request = new Request("getAllRentedAvailableGames", null);

               // Send the request to the server
               Socket socket = employee.getM_socket();
               socket.setSoTimeout(5000);
               PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
               Gson gson = new Gson();
               String json = gson.toJson(request);
               out.println(json);

               // Receive the response from the server
               BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               String jsonResponse = in.readLine();
               Response response = gson.fromJson(jsonResponse, Response.class);

               if (response.isSuccess()) {
                   // Get the list of available games from the response
                   List<VideoGame> availableGames = response.getData();
                   // Update the observable list containing the game data
                   ObservableList<VideoGame> gameData = FXCollections.observableArrayList(availableGames);
                   // Set the updated game data as the new items for the TableView
                   i_gameTable.setItems(gameData);
               } else {
                   System.out.println("Failed to get available games: " + response.getMessage());
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }


    public void handleAddGameButton(ActionEvent i_event,VideoGame i_gameToAdd) {
    	 try {
             // Create a new Employee object
             Employee employee = new Employee();

             // Set up the request to get all available games
             Request request = new Request("addGame", i_gameToAdd);

             // Send the request to the server
             Socket socket = employee.getM_socket();
             socket.setSoTimeout(5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Gson gson = new Gson();
             String json = gson.toJson(request);
             out.println(json);
    	   } catch (IOException e) {
               e.printStackTrace();
           }
    }

    public void handleDeleteGameButton(ActionEvent i_event,VideoGame i_gameToAdd, TableView<VideoGame> i_gameTable) {
   	 try {
    	   // Create a new Employee object
        Employee employee = new Employee();

        // Set up the request to get all available games
        Request request = new Request("deleteGame", i_gameToAdd);
        // Send the request to the server
        Socket socket = employee.getM_socket();
        socket.setSoTimeout(5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        Gson gson = new Gson();
        String json = gson.toJson(request);
        out.println(json);
        
	   } catch (IOException e) {
          e.printStackTrace();
      }
   	this.handleAvailableGamesButton(i_event, i_gameTable);
}
    

    public void handleRentGameButton( ActionEvent i_event,VideoGame i_gameToRent, TableView<VideoGame> i_gameTable) {
    	 try {
      	   // Create a new Employee object
          Employee employee = new Employee();

          // Set up the request to get all available games
          Request request = new Request("rentGame", i_gameToRent);
          // Send the request to the server
          Socket socket = employee.getM_socket();
          socket.setSoTimeout(5000);
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          
          Gson gson = new Gson();
          String json = gson.toJson(request);
          out.println(json);
          
  	   } catch (IOException e) {
            e.printStackTrace();
        }
     	this.handleAvailableGamesButton(i_event, i_gameTable);
  }
      
   public void handleReturnRentedGameButton(ActionEvent i_event,VideoGame i_gameToRent, TableView<VideoGame> i_gameTable) {
	   try {
      	   // Create a new Employee object
          Employee employee = new Employee();

          // Set up the request to get all available games
          Request request = new Request("returnGame", i_gameToRent);
          // Send the request to the server
          Socket socket = employee.getM_socket();
          socket.setSoTimeout(5000);
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
          
          Gson gson = new Gson();
          String json = gson.toJson(request);
          out.println(json);
          
  	   } catch (IOException e) {
            e.printStackTrace();
        }
     	this.handleRentedGamesButton(i_event, i_gameTable);
  }
    
}