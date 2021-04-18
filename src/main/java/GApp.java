import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;

public class GApp extends Application {

    static final double SIZE = 40, NumOfTiles = 20;
    static final double height = SIZE * NumOfTiles, width = height;

    @Override
    public void start(Stage primaryStage) {
        var view = new MainView(width,height, NumOfTiles);
        Scene scene = new Scene(view,width,height);
        primaryStage.setTitle("Hello Life");
        primaryStage.setScene(scene);
        view.draw();
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}