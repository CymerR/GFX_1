import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GApp extends Application {

    static final double width = 500, height = 500, tileX = 25;

    @Override
    public void start(Stage primaryStage) {
        MainView view = new MainView(width,height, tileX);
        Scene scene = new Scene(view,width,height+25);
        primaryStage.setTitle("Hello Life");
        primaryStage.setScene(scene);
        view.draw();
        primaryStage.show();
    }
}