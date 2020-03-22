package ui;

import javafx.application.Application;
import javafx.stage.Stage;

// Main file that starts the application
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        App.init(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
