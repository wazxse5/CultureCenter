package server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ThreadServer threadServer = new ThreadServer();
        ViewManager viewManager = new ViewManager(primaryStage, threadServer);
        threadServer.setViewManager(viewManager);

        primaryStage.setOnCloseRequest(event -> threadServer.close());
        primaryStage.setTitle("Server");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(300);
        viewManager.setInitScene();
        primaryStage.show();
    }
}
