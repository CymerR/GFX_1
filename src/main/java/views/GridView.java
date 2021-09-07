package views;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Simulation;

public class GridView {

    private static Color ALIVE;
    private static Color DEAD;

    private Simulation simulation;

    public GridView(Simulation simulation) {
        this.simulation = simulation;
        ALIVE = Color.valueOf(System.getProperty("COLOR_ALIVE"));
        DEAD = Color.valueOf(System.getProperty("COLOR_DEAD"));
    }

    public void draw(GraphicsContext g) {
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
