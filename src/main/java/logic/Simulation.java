package logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Simulation {
    public static final int ALIVE = 1, DEAD  = 0;

    private Deque<int[][]> chronos;

    int width;
    int height;
    int[][] states;

    public Simulation(int w, int h){
        width = w;
        height = h;
        states = new int[w][h];
        chronos = new ArrayDeque<>();
    }

    public int getState(int x, int y) {
        if (checkBounds(x, y)) return this.states[x][y];
        return 0;
    }

    public void setAlive(int x, int y) {
        if (checkBounds(x, y)) this.states[x][y] = 1;
    }

    public void setDead(int x, int y) {
        if (checkBounds(x, y)) this.states[x][y] = 0;
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

    public void step() {
        chronos.add(states);
        int[][] next = new int[width][height];
        for (int x = 0;x<width;x++) {
            for (int y = 0;y<height;y++) {
                int amount = countNeihbours(x,y);
                switch (this.states[x][y]) {
                    case ALIVE:
                        if (amount == 3 || amount == 2) {
                            next[x][y] = ALIVE;
                        } else {
                            next[x][y] = DEAD;
                        }
                        break;
                    case DEAD:
                        if (amount == 3) {
                            next[x][y] = ALIVE;
                        }
                        break;
                }
            }
        }
        this.states = next;
    }

    public void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.states[x][y] = 0;
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
                this.states = obj;
    }

    public void setNewSize(int newSize) {
        int[][] newBoard= new int[newSize][newSize];
        //todo: array copy
//        if (newSize > width-1) {
//            System.arraycopy(states,0,newBoard,0,width-1);
//        }

        this.width = newSize;
        this.height = newSize;
        this.states = newBoard;
    }
}
