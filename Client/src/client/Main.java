package client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.net.ConnectException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ThreadClient threadClient = new ThreadClient();
        ViewManager viewManager = new ViewManager(primaryStage, threadClient);
        threadClient.setViewManager(viewManager);

        primaryStage.setOnCloseRequest(event -> threadClient.disconnect());
        primaryStage.setTitle("Client");
        viewManager.setInitScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        try {
            threadClient.connect("localhost", 8989);
        } catch (ConnectException e) {
            viewManager.setTitle(viewManager.getTitle() + " - offline");
        }

    }
}
