package views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import logic.Simulation;
import logic.State;

public class MainView extends VBox {

    private static final Color ALIVE = Color.CRIMSON;
    private static final Color DEAD = Color.AQUA;


    private final Canvas canvas;
    private final Simulation simulation;
    private final Affine affine;


    public MainView(double w, double h, double x)  {
        canvas = new Canvas(w,h);
        simulation = new Simulation(this, (int) x,(int) x);

        affine = new Affine();
        affine.appendScale(w / (x-1), h / (x-2));

        canvas.setOnMouseDragged(this::handleMouse);


        ControlBox ui = new ControlBox();

        ui.setAnimationAction(
                play -> simulation.play(),
                stop -> simulation.stop()
        );
        ui.setStepAction(ev -> {
            draw();
            simulation.step();
        });
        ui.setResetAction(ev -> {
            simulation.clear();
            draw();
        });
        ui.setBackAction(ev -> {
            simulation.stepBack();
            draw();
        });

        this.getChildren().addAll(ui, canvas);
    }


    void handleMouse(MouseEvent ev) {
        try {
            Point2D p =  this.affine.inverseTransform(ev.getX(), ev.getY());

            if(ev.getButton() == MouseButton.PRIMARY) {
                simulation.setAlive((int) p.getX(),(int) p.getY());
            }
            if(ev.getButton() == MouseButton.SECONDARY) {
                simulation.setDead((int) p.getX(), (int) p.getY());
            }

        } catch (NonInvertibleTransformException ignored) {}
        draw();
    }


        public void draw() {
            GraphicsContext g = canvas.getGraphicsContext2D();
//            g.setFill(Color.LIGHTGRAY);// bg
            g.setTransform(affine);
            g.fillRect(0,0,simulation.getWidth(),simulation.getHeight());



        for (int x=0;x < simulation.getWidth(); x++) {
            for (int y =0; y<simulation.getHeight();y++) {
                switch (simulation.getState(x, y)) {
                    case 1 -> g.setFill(ALIVE); // cell "alive"
                    case 0 -> g.setFill(DEAD); // cell "dead"
                }
                g.fillRect(x,y,1,1);
            }
        }
        g.setStroke(Color.SLATEBLUE); // line, probably wont be done
        g.setLineWidth(0.02);
        for (int x = 0; x < simulation.getWidth(); x++) {
            g.strokeLine(x,0,x,simulation.getHeight());
        }
        for (int y = 0; y < simulation.getHeight(); y++) {
            g.strokeLine(0,y,simulation.getHeight(), y);
        }
    }
}
