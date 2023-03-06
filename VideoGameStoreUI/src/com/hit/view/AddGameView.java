package com.hit.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;
import com.hit.controller.EmployeeController;
import com.hit.dm.GamePlatformEnum;
import com.hit.dm.VideoGame;
import com.hit.server.Request;
import com.hit.server.Response;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddGameView extends Stage {
    private static final String WINDOW_TITLE = "Add New Game";

    private Image icon;
    private TableView<VideoGame> m_gameTable;

    public AddGameView(EmployeeController i_employeeController, TableView<VideoGame> i_gameTable) {
        this.icon = new Image(getClass().getResourceAsStream("ICON.png"));
        getIcons().add(icon);
        this.m_gameTable = i_gameTable;
        setTitle(WINDOW_TITLE);

        // Create UI controls for entering video game data
        Label nameLabel = new Label("Title:");
        TextField nameField = new TextField();

        Label yearLabel = new Label("Year of Release:");
        TextField yearField = new TextField();
        yearField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        idField.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null));

        Label platformLabel = new Label("Platform:");
        ComboBox<GamePlatformEnum> platformComboBox = new ComboBox<>();
        platformComboBox.getItems().addAll(GamePlatformEnum.values());

        Button createButton = new Button("Create");

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(cancelEvent -> {
            close();
        });

        // Set up the layout of the add game window
        VBox addGameLayout = new VBox(10);
        addGameLayout.setPadding(new Insets(10));
        addGameLayout.getChildren().addAll(
                nameLabel, nameField,
                yearLabel, yearField,
                idLabel, idField,
                platformLabel, platformComboBox,
                new HBox(10, createButton, cancelButton)
        );

        Scene addGameScene = new Scene(addGameLayout, 400, 300);
        setScene(addGameScene);

        createButton.setOnAction(event -> {
            String name = nameField.getText();
            short year = Short.parseShort(yearField.getText());
            long id = Long.parseLong(idField.getText());
            GamePlatformEnum platform = platformComboBox.getValue();

            VideoGame videoGame = new VideoGame(name, year, id, platform);
            i_employeeController.  handleAddGameButton(event, videoGame);
            close();
           i_employeeController.handleAvailableGamesButton(event, i_gameTable);
        });
    }
}
    