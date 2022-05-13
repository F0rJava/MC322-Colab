package pt.c40task.l05wumpus;

public class Heroi extends Componente{
    int flecha;
    boolean flechaEquipada;
    int ouro;

    public Heroi(int flecha, int[] pos, Caverna cav){
        //Específicos do Herói
        this.flecha = flecha; //Criado assim para ser mais fácil a expanão no caso de um jogo com mais flechas
        this.flechaEquipada = false;
        this.ouro = 0;

        //Geral
        this.pos = new int[2];
        this.pos[0] = pos[0];
        this.pos[1] = pos[1];
        this.cav = cav;
        this.symbol = 'P';
    }

    /*Métodos específicos do Herói*/

    public void carregaFlecha(){
        //Método que carrega a flecha, que será atirada no próx movimento
        this.flechaEquipada = true;
    }
    public void atiraFlecha(){
        //Método que atira a flecha, automaticamente desequipando e diminuindo em 1 o número total de flechas
        this.flechaEquipada = false;
        this.flecha -= 1;
    }
    public void pegaOuro(){
        //Método que adiciona a quantidade de ouro do herói em 1
        this.ouro += 1;
    }
    public void mover(int i, int j){
        //Método que move o herói para a pos[i][j]
        this.pos[0] = i;
        this.pos[1] = j;
    }
    public boolean temPerigo(int i, int j, char perigo){
        //Método que analisa se tem um "perigo"(Wumpus ou Buraco) na Sala que está na matriz[i][j]
        Sala aux;
        aux = cav.getMatriz()[i][j];
        return aux.temComponente(perigo);
    }
}
