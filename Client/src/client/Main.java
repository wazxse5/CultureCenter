package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ThreadClient threadClient = new ThreadClient();
        ViewManager viewManager = new ViewManager(primaryStage, threadClient);
        threadClient.setViewManager(viewManager);

        primaryStage.setOnCloseRequest(event -> threadClient.disconnect());
        primaryStage.setTitle("Client");
        viewManager.setInitScene();
        primaryStage.show();

        threadClient.connect("localhost", 8989);
    }
}
