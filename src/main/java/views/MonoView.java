package views;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import logic.MonoSimulator;

public class MonoView extends VBox {

    private final double countOfTiles;
    private Canvas canvas;
    private Affine affine;
    private MonoSimulator sim;

    private double width, height, tilesWidth;
    private int tilesHeight;


    public MonoView(double width, double height, double countOfTiles) {
        this.width = width;
        this.height = height;
        this.countOfTiles = countOfTiles;
        this.tilesWidth = width / countOfTiles;
        this.tilesHeight = (int) (height / tilesWidth);

        this.affine = new Affine();
        affine.appendScale(width/countOfTiles, height/countOfTiles);


        this.setPrefSize(width,height);

        canvas = new Canvas(width, height);
        sim = new MonoSimulator((int) tilesWidth, (int) tilesHeight);

        this.getChildren().addAll(
                canvas
        );
    }

    public void draw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.LIGHTGRAY);
        g.setTransform(affine);

        g.fillRect(0,0, countOfTiles, tilesHeight);



        var field = sim.getField();
        for (int y = 0; y < field.length; y++) {
            for(int x = 0; x < field[y].length; x++){
                if(field[y][x] == MonoSimulator.ALIVE){
                    g.setFill(Color.VIOLET);
                } else {
                    g.setFill(Color.SILVER);
                }
                g.fillRect(x,y,tilesWidth,tilesWidth);
            }
        }


        //draw grid
        g.setStroke(Color.BLACK);
        g.setLineWidth(0.1);
        for (int x = 0; x < countOfTiles; x++) {
            for (int y = 0; y <= tilesHeight; y++) {
                g.strokeRect(x,y,tilesWidth, tilesWidth);
            }
        }

    }

}
