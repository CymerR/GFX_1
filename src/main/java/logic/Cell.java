package logic;

public class Cell {
    private State state;

    public Cell() {
        state = State.DEAD;
    }
    public void setAlive() {
        state = State.ALIVE;
    }
    public void setDead() {
        state = State.DEAD;
    }
    public State getState() {return state;}
}
