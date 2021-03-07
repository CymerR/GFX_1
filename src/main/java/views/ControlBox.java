package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ControlBox extends ToolBar {


    private Map<String, Button> buttons;

    private TextField inputTxt;


    public ControlBox() {

        buttons = new HashMap<>();
        buttons.put("play",new Button("Play!"));
        buttons.put("switch",new Button("1D"));
        buttons.put("reset",new Button("Reset!"));
        buttons.put("back",new Button("Back!"));
        buttons.put("step",new Button("Step!"));
        inputTxt = new TextField();
        inputTxt.setPrefColumnCount(3);





        this.getItems().addAll(
                buttons.values()
        );
        this.getItems().addAll( inputTxt);

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


    public void addPlayAction(EventHandler<ActionEvent> handler){
        buttons.get("play").setOnAction(ev -> {
            var text = inputTxt.getText();
            if (!Pattern.compile("\\d+").matcher(text).matches()) {
                new Alert(Alert.AlertType.ERROR, "Must be number!").showAndWait();
                text = "0";
            }
            int n = Integer.parseInt(text);
            var stp = buttons.get("step");
            if (stp != null && stp.getOnAction() != null) {
                try {
                    for (int i = 0;i < n;i++, handler.handle(ev), Thread.sleep(0)) {
                        stp.getOnAction().handle(ev);
                        System.out.println("Ticked!");

                    }
                } catch (InterruptedException ex) {}
            }
        });
    }
}
