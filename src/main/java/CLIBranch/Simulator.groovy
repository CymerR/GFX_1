package CLIBranch

class Simulator {

    public static final int ALIVE = 1, DEAD = 0

    int[][] states
    int size

    Simulator(int size) {
        this.size = size
        states = new int[size][size];
    }


    void setAlive(int x, int y) {
        states[x][y] = ALIVE
    }


    int countNeighbors(int x, int y) {
        int res = 0;

        res += getState(x - 1,y - 1)
        res += getState(x - 1,y)
        res += getState(x - 1, y + 1)

        res += getState(x, y - 1)
        res += getState(x, y + 1)

        res += getState(x + 1, y - 1)
        res += getState(x + 1, y)
        res += getState(x + 1, y + 1)

        res
    }

    void step() {
        int[][] next = new int[size][size]
        for (x in 0..<size) {
            for (y in 0..<size) {
                int cunt = countNeighbors(x,y)
                if(states[x][y] == ALIVE) {
                    if (cunt < 2 || cunt > 3)
                        next[x][y] = DEAD
                    else
                        next[x][y] = ALIVE
                } else {
                    if (cunt == 3)
                        next[x][y] = ALIVE
                    else
                        next[x][y] = DEAD
                }
            }
        }
        this.states = next
    }

    private int getState(int x, int y) {
        if(x >= size || y >= size || y < 0 || x < 0) return 0
        states[x][y]
    }

    private boolean checkBounds(int x, int y) {
        return !(x < 0 || x >= size) && !(y < 0 || y >= size);
    }


    @Override
    String toString() {
       def sb = new StringBuilder()
        for (y in 0..<size) {
            for (x in 0..<size) {
                sb.append(states[x][y] == DEAD ? '- ' : '* ')
            }
            sb.append('\n')
        }
        sb.toString()
    }
}
