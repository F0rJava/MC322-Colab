package pt.c40task.l05wumpus;

public abstract class Componente {
    protected int[] pos;
    protected Caverna cav;
    protected String symbol;

    public String getSymbol(){
        return symbol;
    }
    public void conecta(Caverna cav){
        this.cav = cav;
    }
}
