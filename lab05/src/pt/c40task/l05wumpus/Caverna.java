package pt.c40task.l05wumpus;

public class Caverna {
    private Sala[][] matriz;

    public Caverna(){
        matriz = new Sala[4][4];

        //adiciona novas salas à matriz de salas da caverna
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                matriz[i][j] = new Sala();
            }
        }
    }

    public Sala[][] getMatriz(){
        return matriz;
    }
}
