package views;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class ControlBox extends ToolBar {


    private Map<String, Button> btns;
    private  MainView view;


    public ControlBox(MainView view) {


        this.view = view;


        btns = new HashMap<>();
        btns.put("play",new Button("Play!"));
        btns.put("stop",new Button("Stop!"));
//        btns.put("switch",new Button("1D"));
        btns.put("reset",new Button("Reset!"));
        btns.put("back",new Button("Back!"));
        btns.put("step",new Button("Step!"));

        btns.get("step").setOnAction(ev -> {
            view.getSimulation().step();
            view.draw();
        });
        btns.get("back").setOnAction(ev -> {
            view.getSimulation().stepBack();
            view.draw();
        });
        btns.get("reset").setOnAction(ev -> {
            view.getSimulation().clear();
            view.draw();
        });
        btns.get("play").setOnAction(ev -> {
            view.getSimulation().play();
        });
        btns.get("stop").setOnAction(ev -> {
            view.getSimulation().stop();
        });




        this.getItems().addAll(
                btns.values()
        );

    }

}
