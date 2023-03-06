package com.hit.view;
	
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

public class MainView extends Application {
    // Constants
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 200;
    private static final String APP_TITLE = "Video Game Store";
    
    // Variables
    private Stage m_primaryStage;
    private Image m_icon;

    @Override
    public void start(Stage primaryStage) {
        // Initialize variables
        this.m_primaryStage = primaryStage;
        this.m_icon = new Image(getClass().getResourceAsStream("ICON.png"));
        // Set up the UI
        setUpUI();
        // Show the primary stage
        primaryStage.show();
    }

    private void setUpUI() {
        // Create the title label
        Label titleLabel = new Label("Welcome!");
        titleLabel.getStyleClass().add("label-title");

        // Create the client and employee buttons
        Button clientButton = createButton("Client");
        clientButton.setOnAction(event -> {
            openClientView();
        });

        Button employeeButton = createButton("Employee");
        employeeButton.setOnAction(event -> {
            openEmployeeView();
        });

        // Create an HBox to hold the buttons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(clientButton, employeeButton);

        // Load the background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/com/hit/view/background.png"));
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(WINDOW_WIDTH);
        backgroundView.setFitHeight(WINDOW_HEIGHT);
        backgroundView.setOpacity(0.35);

        // Create a VBox to hold the title label and the button box
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(titleLabel, buttonBox);

        // Create a StackPane and add the background image and the VBox as children
        StackPane root = new StackPane(backgroundView, vbox);

        // Create a new Scene and set it on the primary stage
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        m_primaryStage.setScene(scene);
        m_primaryStage.setTitle(APP_TITLE);
        m_primaryStage.getIcons().add(m_icon);

        // Center the stage on the screen
        m_primaryStage.centerOnScreen(); 
    }


    private Button createButton(String i_text) {
        Button button = new Button(i_text);
        button.setPrefWidth(100);
        button.getStyleClass().add("main-button");
        return button;
    }

    private void openEmployeeView() {
        // Create a new instance of EmployeeView
    	
    	 if (!showPasswordDialog()) {
    	        return;
    	    }
        EmployeeView employeeView = new EmployeeView(m_primaryStage);

        // Set the scene on the primary stage
        Scene scene = new Scene(employeeView, 900, 600);
        m_primaryStage.setScene(scene);
        m_primaryStage.setTitle("Video Game Store - Employee");
        m_primaryStage.getIcons().add(m_icon);

        // Show the primary stage
        m_primaryStage.show();
    }
    private void openClientView() {
        // Create a new instance of ClientView
        ClientView clientView = new ClientView(m_primaryStage);

        // Set the scene on the primary stage
        Scene scene = new Scene(clientView, 900, 600);
        m_primaryStage.setScene(scene);
        m_primaryStage.setTitle("Video Game Store - Client");
        m_primaryStage.getIcons().add(m_icon);

        // Show the primary stage
        m_primaryStage.show();
    }
    
    private boolean showPasswordDialog() {
        // Create the password field and label
        PasswordField passwordField = new PasswordField();
        Label label = new Label("Please enter the password:");
        
        // Create the OK button and set its action
        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", okButtonType, cancelButtonType);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(m_primaryStage);
        alert.setTitle("Enter Password");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(new VBox(10, label, passwordField));
        
        // Set the default button
        Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
        okButton.setDefaultButton(true);
        
        // Show the dialog and wait for a result
        Optional<ButtonType> result = alert.showAndWait();
        
        // Check if the OK button was clicked and the entered password is correct
        if (result.isPresent() && result.get() == okButtonType && passwordField.getText().equals("Aa123456")) {
            return true;
        }
        
        // Check if the Cancel button was clicked and close the dialog
        if (result.isPresent() && result.get() == cancelButtonType) {
            ((Stage) okButton.getScene().getWindow()).close();
            return false;
        }
        
        // Otherwise, show an error message and return false
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Incorrect password");
        errorAlert.initModality(Modality.APPLICATION_MODAL);
        errorAlert.initOwner(m_primaryStage);
        errorAlert.showAndWait();
        return false;
    }
}