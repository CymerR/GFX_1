package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Blend;
import logic.Simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ControlBox extends ToolBar {


    private Map<String, Button> buttons;


    public ControlBox() {

        buttons = new HashMap<>();
        buttons.put("play",new Button("Play!"));
        buttons.put("stop",new Button("Stop!"));
        buttons.put("reset",new Button("Reset!"));
        buttons.put("back",new Button("Back!"));
        buttons.put("step",new Button("Step!"));


        this.getItems().addAll(
                buttons.values()
        );

    }



    public void setStepAction(EventHandler<ActionEvent> handler){
        buttons.get("step").setOnAction(handler);
    }

    public void setResetAction(EventHandler<ActionEvent> handler){
        buttons.get("reset").setOnAction(handler);
    }


    public void setBackAction(EventHandler<ActionEvent> handler){
        buttons.get("back").setOnAction(handler);
    }


    public void setPlayAction(EventHandler<ActionEvent> handler){
        EventHandler<ActionEvent> res = ev -> {
            buttons.get("step").setVisible(false);
            handler.handle(ev);
        };
        buttons.get("play").setOnAction(res);
    }


    public void setStopAction(EventHandler<ActionEvent> handler){
        EventHandler<ActionEvent> res = ev -> {
            buttons.get("step").setVisible(true);
            handler.handle(ev);
        };
        buttons.get("stop").setOnAction(res);
    }

}
