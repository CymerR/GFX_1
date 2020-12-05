public class Simulation {
    int width;
    int height;
    int[][] states;

    Simulation(int w, int h){
        width = w;
        height = h;
        states = new int[w][h];
    }

    int getState(int x, int y) {
        if (checkBounds(x, y)) return this.states[x][y];
        return 0;
    }

    void setAlive(int x, int y) {
        if (checkBounds(x, y)) this.states[x][y] = 1;
    }

    void setDead(int x, int y) {
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

    void step() {
        int[][] next = new int[width][height];
        for (int x = 0;x<width;x++) {
            for (int y = 0;y<height;y++) {
                int amount = countNeihbours(x,y);
                switch (this.states[x][y]) {
                    case 1:
                        if (amount == 3 || amount == 2) {
                            next[x][y] = 1;
                        } else {
                            next[x][y] = 0;
                        }
                        break;
                    case 0:
                        if (amount == 3) {
                            next[x][y] = 1;
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
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getStates() {
        return states;
    }

    public void setStates(int[][] states) {
        this.states = states;
    }

    /**
     *
     * @param x
     * @param y
     * @return true - if OK
     */
    private boolean checkBounds(int x, int y) {
        return !(x < 0 || x >= width) && !(y < 0 || y >= width);
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
