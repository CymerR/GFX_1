import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.MainView;
import views.MonoView;

public class GApp extends Application {

    static final double width = 500, height = 500, NumOfTiles = 40;

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