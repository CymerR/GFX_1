package logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import views.MainView;

import java.util.ArrayDeque;
import java.util.Deque;

import static logic.State.ALIVE;

public class Simulation {
//    public static final int ALIVE = 1, DEAD  = 0;

    private Deque<Cell[][]> chronos;

    int width;
    int height;
    Cell[][] grid;
    MainView view;



    public Simulation(MainView view, int w, int h){
        width = w;
        height = h;

        grid = getGrid(w, h);

        chronos = new ArrayDeque<>();
        this.view = view;

        this.timeline = new Timeline(new KeyFrame(Duration.millis(500), this::doStep));
        timeline.setCycleCount(-1);
    }

    public int getState(int x, int y) {
        if (checkBounds(x, y)) {
            return (this.grid[x][y].getState() == ALIVE) ? 1 : 0;
        }
        return 0;
    }

    public void setAlive(int x, int y) {
        if (checkBounds(x, y)) this.grid[x][y].setAlive();
    }

    public void setDead(int x, int y) {
        if (checkBounds(x, y)) this.grid[x][y].setDead();
    }

    private int countNeihbours(int x, int y) {
        int count = 0;
        count += getState(x - 1, y - 1);
        count += getState(x, y - 1);
        count += getState(x + 1, y - 1);

        count += getState(x - 1, y);
        count += getState(x + 1, y);

        count += getState(x - 1, y + 1);
        count += getState(x, y + 1);
        count += getState(x + 1, y + 1);
        return count;
    }

    private Cell[][] getGrid(int w, int h) {
        var res = new Cell[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                res[x][y] = new Cell();
            }
        }
        return res;
    }

    public void step() {
        chronos.add(grid);
        var next = getGrid(width, height);
        for (int x = 0;x<width;x++) {
            for (int y = 0;y<height;y++) {
                int amount = countNeihbours(x,y);
                switch (this.grid[x][y].getState()) {
                    case ALIVE:
                        if (amount == 3 || amount == 2) {
                            next[x][y].setAlive();
                        } else {
                            next[x][y].setDead();
                        }
                        break;
                    case DEAD:
                        if (amount == 3) {
                            next[x][y].setAlive();
                        }
                        break;
                }
            }
        }
        this.grid = next;
    }

    public void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.grid[x][y].setDead();
            }
        }
        chronos.clear();
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    /**
     * @return true - if OK
     */
    private boolean checkBounds(int x, int y) {
        return !(x < 0 || x >= width) && !(y < 0 || y >= width);
    }


    public void stepBack() {
         var obj = chronos.pollLast();
         if (obj != null)
                this.grid = obj;
    }

    Timeline timeline;

    private void doStep(ActionEvent ev) {
        step();
        view.draw();
    }

    public void play() {
        timeline.play();
    }
    public void stop(){
        timeline.stop();
    }
}
