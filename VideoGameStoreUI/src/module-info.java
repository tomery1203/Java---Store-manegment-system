module VideoGameStoreUI {
	requires javafx.controls;
	requires javafx.graphics;
	requires VideoGameDataModel;
	requires javafx.base;
	requires requestAndResponse;
	requires com.google.gson;
	requires java.desktop;
	requires javafx.swing;
	
	opens com.hit.view to javafx.graphics, javafx.fxml;
	exports com.hit.driver;
}
