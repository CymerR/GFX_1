package logic;

public class MonoSimulator {

    public static final int ALIVE = 1, DEAD = 0;
    int[] states;
    int size, height;

    public MonoSimulator(int length, int height) {
        states = new int[length];
        states[states.length/2] = ALIVE;
        size = length;
        this.height = height;
    }


    public int[][] getField() {
        //rule 30
        int[][] res = new int[height][size];
        res[0] = states;
        for (int y = 1; y < res.length; y++) {
            int[] current = new int[size];
            for (int x = 1; x < res[y].length-1; x++) {
                if (
                        (res[y-1][x-1] == ALIVE) &
                        (res[y-1][x] == DEAD) &
                        (res[y-1][x+1] == DEAD) ||

                        (res[y-1][x-1] == DEAD) &
                        (res[y-1][x] == ALIVE) &
                        (res[y-1][x+1] == ALIVE) ||

                        (res[y-1][x-1] == DEAD) &
                        (res[y-1][x] == ALIVE) &
                        (res[y-1][x+1] == DEAD) ||

                        (res[y-1][x-1] == DEAD) &
                        (res[y-1][x] == DEAD) &
                        (res[y-1][x+1] == ALIVE)


                ){
                    current[x] = ALIVE;
                } else {
                    current[x] = DEAD;
                }
            }
            res[y] = current;
        }
        return res;
    }


    public int getState(int x) {
        if (x >= 0 && x < size) return states[x];
        throw new IllegalArgumentException();
    }

    public int getSize() {
        return size;
    }
}
