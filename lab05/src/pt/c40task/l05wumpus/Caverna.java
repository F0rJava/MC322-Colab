package pt.c40task.l05wumpus;

public class Caverna {
    private Sala[][] matriz;

    public Caverna(){
        matriz = new Sala[4][4];
    }
    public void setSala(int i, int j, Sala nova){
        matriz[i][j] = nova;
    }
    public Sala[][] getMatriz(){
        return matriz;
    }
}
