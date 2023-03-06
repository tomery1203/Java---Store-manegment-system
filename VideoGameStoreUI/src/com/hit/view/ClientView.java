package com.hit.view;

import com.hit.controller.ClientController;
import com.hit.dm.GamePlatformEnum;
import com.hit.dm.VideoGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientView extends BorderPane {

	    // Constants
	    private static final String APP_TITLE = "Video Game Store - Client";

	    // Variables
	    private Stage m_primaryStage;
	    private Image m_icon;
	    private TableView<VideoGame> m_gameTable;
	    private ClientController m_clientController = null;
	    
	    public ClientView(Stage i_primaryStage) {
	        this.m_primaryStage = i_primaryStage;
	        this.m_clientController = new ClientController();
	        this.m_icon = new Image(getClass().getResourceAsStream("ICON.png"));
	        this.m_gameTable = new TableView<>();

	        setUpUI();
	    }

	    @SuppressWarnings("unchecked")
	    private void setUpUI() {
	    	
	    	 // Load the background image
	        Image backgroundImage = new Image(getClass().getResourceAsStream("/com/hit/view/background.png"));
	        ImageView backgroundView = new ImageView(backgroundImage);
	        backgroundView.setFitWidth(900);
	        backgroundView.setFitHeight(600);
	        backgroundView.setOpacity(0.35);
	        
	        
	        // Set up the top bar with buttons
	        HBox topBar = new HBox(10);
	        topBar.setPadding(new Insets(10));
	        topBar.setAlignment(Pos.CENTER_RIGHT);

	        Button availableGamesButton = createAvailableGamesButton();
	        Button rentedGamesButton = createRentedGamesButton();
	        Button rentGameButton = createRentGameButton();
	        Button returnRentedGameButton = createReturnRentedGameButton();

	        topBar.getChildren().addAll(
	                availableGamesButton,
	                rentedGamesButton,
	                rentGameButton,
	                returnRentedGameButton
	        );
	        setTop(topBar);

	        // Set up the center table for displaying the list of games
	        m_gameTable.setPlaceholder(new Label("No games to display."));

	        TableColumn<VideoGame, String> titleCol = new TableColumn<>("Title");
	        titleCol.setCellValueFactory(new PropertyValueFactory<>("name"));

	        TableColumn<VideoGame, GamePlatformEnum> platformCol = new TableColumn<>("Platform");
	        platformCol.setCellValueFactory(new PropertyValueFactory<>("Platform"));

	        TableColumn<VideoGame, Short> yearCol = new TableColumn<>("Year of Release");
	        yearCol.setCellValueFactory(new PropertyValueFactory<>("YearOfRelease"));

	        TableColumn<VideoGame, Long> idCol = new TableColumn<>("ID");
	        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

	        m_gameTable.getColumns().addAll(titleCol, platformCol, yearCol, idCol);

	        VBox centerBox = new VBox(10);
	        centerBox.setPadding(new Insets(10));
	        centerBox.setStyle("-fx-background-color: #f0f0f0;"); // Set the background color
	        centerBox.getChildren().add(m_gameTable);
	        setCenter(centerBox);

	        StackPane root = new StackPane(backgroundView, this);
	        
	        Scene scene = new Scene(root,900, 600);
	        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	        m_primaryStage.setScene(scene);
	        
	        
	        // Set the stage properties
	        m_primaryStage.setTitle(APP_TITLE);
	        m_primaryStage.getIcons().add(m_icon);
	        m_primaryStage.setWidth(900);
	        m_primaryStage.setHeight(600);
	    }
	    public Button createAvailableGamesButton() {
	        Button button = new Button("Available Games");
	        button.setOnAction(event -> {
	            m_clientController.handleAvailableGamesButton(event, m_gameTable);
	        });
	        return button;
	    }

	    private Button createRentedGamesButton() {
	        Button button = new Button("Rented Games");
	        button.setOnAction(event -> {
	            m_clientController.handleRentedGamesButton(event, m_gameTable);
	        });
	        return button;
	    }

	    private Button createRentGameButton() {
	        Button button = new Button("Rent Game");
	        button.setOnAction(event -> {
	            // Get the selected game from the table view
	            VideoGame selectedGame = m_gameTable.getSelectionModel().getSelectedItem();

	            // Call the handleRentGameButton method with the selected game
	            m_clientController.handleRentGameButton(event, selectedGame, m_gameTable);
	            m_clientController.handleAvailableGamesButton(event, m_gameTable);
	        });
	        return button;
	    }
	    private Button createReturnRentedGameButton() {
	        Button button = new Button("Return Rented Game");
	        button.setOnAction(event -> {
	            // Get the selected game from the table view
	            VideoGame selectedGame = m_gameTable.getSelectionModel().getSelectedItem();
	            
	            // Call the handleReturnRentedGameButton method with the selected game
	            m_clientController.handleReturnRentedGameButton(event, selectedGame, m_gameTable);
	            m_clientController.handleRentedGamesButton(event, m_gameTable);
	        });
	        return button;
	    }
}
