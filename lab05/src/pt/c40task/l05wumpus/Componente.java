package pt.c40task.l05wumpus;

public abstract class Componente {
    protected int[] pos;
    protected Caverna cav;
    protected char symbol;

    public int[] getPos(){
        return pos;
    }
    public char getSymbol(){
        return symbol;
    }
    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
}
