import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;

import java.util.List;

public class ControlBox extends ToolBar {
    private Button stepBtn;
    private Button resetBtn;
    private Slider slider;

    public ControlBox() {
        stepBtn = new Button("Step!");
        resetBtn = new Button("Reset!");
        slider = new Slider(10, 25, 10);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(1);
        slider.setShowTickMarks(true);
        this.getItems().addAll(
                stepBtn, resetBtn, slider
        );


    }

    void setScrollValue(double size) {
        this.slider.setValue(size);
    }

    public void setScrollAction(ChangeListener<Number> listener) {
        slider.valueProperty().addListener(listener);
    }

    public void setStepAction(EventHandler<ActionEvent> handler){
        stepBtn.setOnAction(handler);
    }

    public void setResetAction(EventHandler<ActionEvent> handler){
        resetBtn.setOnAction(handler);
    }

    List<Node> getNodes() {
        return List.of(stepBtn, resetBtn);
    }
}
