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

public class MainView extends VBox {

    private Canvas canvas;
    private Simulation simulation;
    private Affine affine;
    private double tileSize;

    private double width, height;

    public MainView(double w, double h, double x)  {
        canvas = new Canvas(w,h);
        simulation = new Simulation(this, (int) x,(int) x);

        this.width = w;
        this.height = h;

        this.tileSize = w / x;
        affine = new Affine();
        affine.appendScale(w / x, h / x);

        canvas.setOnMouseDragged(this::handleMouse);


        ControlBox ui = new ControlBox(this);


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
            g.setFill(Color.LIGHTGRAY);
            g.setTransform(affine);
            g.fillRect(0,0,simulation.getWidth(),simulation.getHeight());



        for (int x=0;x < simulation.getWidth(); x++) {
            for (int y =0; y<simulation.getHeight();y++) {
                switch (simulation.getState(x,y)){
                    case Simulation.ALIVE:
                        g.setFill(Color.BISQUE);
                        break;
                    case Simulation.DEAD:
                        g.setFill(Color.DARKGRAY);
                        break;
                }
                g.fillRect(x,y,1,1);
            }
        }
        g.setStroke(Color.SLATEBLUE);
        g.setLineWidth(0.05);
        for (int x = 0; x < simulation.getWidth(); x++) {
            g.strokeLine(x,0,x,simulation.getHeight());
        }
        for (int y = 0; y < simulation.getHeight(); y++) {
            g.strokeLine(0,y,simulation.getHeight(), y);
        }
    }


    public Simulation getSimulation() {
        return simulation;
    };
}
