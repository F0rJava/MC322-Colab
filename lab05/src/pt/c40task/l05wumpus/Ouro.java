package pt.c40task.l05wumpus;

public class Ouro extends Componente {
    public Ouro(int[] pos, Caverna cav){
        this.pos = new int[2];
        this.pos[0] = pos[0];
        this.pos[1] = pos[1];
        this.cav = cav;
        this.symbol = 'O';
    }
}
