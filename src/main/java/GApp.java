import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import views.MainView;

import java.util.Properties;

public class GApp extends Application {

    static final double SIZE = 40, NumOfTiles = 20;
    static final double height = SIZE * NumOfTiles, width = height;

    @Override
    public void start(Stage primaryStage) {

        Properties properties = System.getProperties();
        properties.forEach((prop, val) -> System.out.println(prop + " : " + val));

//        String prop = System.getProperty("COLOR_ALIVE");
//        System.out.println(prop);
//        var c = Color.valueOf(prop);

//        System.out.println(c);

        var view = new MainView(width,height, NumOfTiles);
        Scene scene = new Scene(view,width,height);
        primaryStage.setTitle("Hello Life");
        primaryStage.setScene(scene);
        view.draw();
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}