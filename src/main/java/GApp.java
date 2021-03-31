import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;

public class GApp extends Application {

    static final double width = 900, height = 600, NumOfTiles = 25;

    @Override
    public void start(Stage primaryStage) {
        var view = new MainView(width,height, NumOfTiles);
        Scene scene = new Scene(view,width,height);
        primaryStage.setTitle("Hello Life");
        primaryStage.setScene(scene);
        view.draw();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}