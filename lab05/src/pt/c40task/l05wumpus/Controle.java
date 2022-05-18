package pt.c40task.l05wumpus;
import java.util.Random;

public class Controle {
    private Heroi hero;
    private int pontos;

    public Controle(Heroi hero){
        this.hero = hero;
        this.pontos = 0;
    }
    public int getPontos(){
        return pontos;
    }
    public void acao(int i, int j){
        //Método que recebe as novas posicoes de acordo com o comando('w', 'a', 's' ou 'd') e analisa se é uma posiçao valida
        if(i <= 3 && j <= 3){ //Posicao válida dentro do mapa (matriz 4x4)
            hero.mover(i, j);
            this.pontos -= 15;

            if(hero.flechaEquipada){ //O herói se moveu com a flecha equipada, ou seja, ele a atirou
                hero.atiraFlecha();
                this.pontos -= 100;

                if(hero.cav.getMatriz()[i][j].temComponente('W')){ //Se tem um Wumpus na sala o heroi tem 50% de chance de matá-lo
                    Random rand = new Random();
                    if(rand.nextInt(1) == 1){ //Herói morreu
                        this.pontos -= 1000;
                        hero.setSymbol(' ');
                    }
                    else{ //Herói matou o Wumpus
                        this.pontos += 500;
                        hero.cav.getMatriz()[i][j].eliminaComponente('W');
                    }
                }
            }
            else{ //Se ele não equipou a flecha e encontrou um Wumpus
                if(hero.cav.getMatriz()[i][j].temComponente('W')){
                    this.pontos -= 1000;
                    hero.setSymbol(' '); //Heroi morreu
                }
            }
            if(hero.cav.getMatriz()[i][j].temComponente('B')){
                this.pontos -= 1000;
                hero.setSymbol(' '); //Heroi morreu
            }
        }
    }
    public boolean acao(char c){
        //Recebe um char q será equivalente a uma das ações abaixo, e retorna um boolean se o jogador saiu ou não do jogo, se a acao for 'q' ele saiu do jogo
        if(c == 'k')
            hero.carregaFlecha();
        else if(c == 'c'){
            if(hero.cav.getMatriz()[hero.pos[0]][hero.pos[1]].temComponente('O')){
                hero.pegaOuro();
                this.pontos += 1000;
                hero.cav.getMatriz()[hero.pos[0]][hero.pos[1]].eliminaComponente('O');
            }
        }
        else if(c == 'q'){
            return true;
        }
        return false;
    }
}
