package pt.c40task.l05wumpus;

public class Montador{
    private Caverna cav;
    
    public void conecta(Caverna cav){
        this.cav = cav;
    }

    public void montaCaverna(String info[]){
        int[] pos = new int[2]; //vetor auxiliar, que atualiza com a posiçao passada no arquivo
        pos[0] = Character.getNumericValue(info[0].charAt(0)) - 1;
        pos[1] = Character.getNumericValue(info[1].charAt(0)) - 1;
        
        
        //adiciona um novo objeto de cada componente na sala especificada, na posição vazia
        //caso seja B ou W, adiciona tambem brisa e fedor, respectivamente
        switch(info[2].charAt(0)){
            case 'B':
                cav.getMatriz()[pos[0]][pos[1]].setComps(new Buraco(pos, cav), cav.getMatriz()[pos[0]][pos[1]].espacoVazio());
                adicionaSecundario('B', pos, cav);
                break;               
            case 'O':
                cav.getMatriz()[pos[0]][pos[1]].setComps(new Ouro(pos, cav), cav.getMatriz()[pos[0]][pos[1]].espacoVazio());
                break;
            case 'W':
                cav.getMatriz()[pos[0]][pos[1]].setComps(new Wumpus(pos, cav), cav.getMatriz()[pos[0]][pos[1]].espacoVazio());
                adicionaSecundario('W', pos, cav);
                break;
        };
    }

    public void adicionaSecundario(char compSec, int[] pos, Caverna cav){
        //vetor que recebe a posição do componente primario e se altera conforme a posição
        //que deve ir o componente secundario
        int[] posSec = new int[2];
        posSec[0] = pos[0];
        posSec[1] = pos[1];

        //cria um objeto respectivo pra cada componente primario, em cada direção da matriz (caso exista)
        //em cada condição, restaura o valor de posSec para o original para nao haver conflito
        switch(compSec){
            case 'B':
                if(pos[0]-1>=0 && !cav.getMatriz()[pos[0]-1][pos[1]].temComponente('b')){
                    posSec[0]--;
                    cav.getMatriz()[pos[0]-1][pos[1]].setComps(new Brisa(posSec, cav), cav.getMatriz()[pos[0]-1][pos[1]].espacoVazio());
                    posSec[0] = pos[0];
                }
                if(pos[0]+1<cav.getMatriz().length && !cav.getMatriz()[pos[0]+1][pos[1]].temComponente('b')){
                    posSec[0]++;
                    cav.getMatriz()[pos[0]+1][pos[1]].setComps(new Brisa(posSec, cav), cav.getMatriz()[pos[0]+1][pos[1]].espacoVazio());
                    posSec[0] = pos[0];
                }
                if(pos[1]-1>=0 && !cav.getMatriz()[pos[0]][pos[1]-1].temComponente('b')){
                    posSec[1]--;
                    cav.getMatriz()[pos[0]][pos[1]-1].setComps(new Brisa(posSec, cav), cav.getMatriz()[pos[0]][pos[1]-1].espacoVazio());
                    posSec[1] = pos[1];
                }
                if(pos[1]+1<cav.getMatriz().length && !cav.getMatriz()[pos[0]][pos[1]+1].temComponente('b')){
                    posSec[1]++;
                    cav.getMatriz()[pos[0]][pos[1]+1].setComps(new Brisa(posSec, cav), cav.getMatriz()[pos[0]][pos[1]+1].espacoVazio());
                    posSec[1] = pos[1];
                }
            break;
            case 'W':
                if(pos[0]-1>0 && !cav.getMatriz()[pos[0]-1][pos[1]].temComponente('f')){
                    posSec[0]--;
                    cav.getMatriz()[pos[0]-1][pos[1]].setComps(new Fedor(posSec, cav), cav.getMatriz()[pos[0]-1][pos[1]].espacoVazio());
                    posSec[0] = pos[0];
                }
                if(pos[0]+1<cav.getMatriz().length && !cav.getMatriz()[pos[0]+1][pos[1]].temComponente('f')){
                    posSec[0]++;
                    cav.getMatriz()[pos[0]+1][pos[1]].setComps(new Fedor(posSec, cav), cav.getMatriz()[pos[0]+1][pos[1]].espacoVazio());
                    posSec[0] = pos[0];
                }
                if(pos[1]-1>0 && !cav.getMatriz()[pos[0]][pos[1]-1].temComponente('f')){
                    posSec[1]--;
                    cav.getMatriz()[pos[0]][pos[1]-1].setComps(new Fedor(posSec, cav), cav.getMatriz()[pos[0]][pos[1]-1].espacoVazio());
                    posSec[1] = pos[1];
                }
                if(pos[1]+1<cav.getMatriz().length && !cav.getMatriz()[pos[0]][pos[1]+1].temComponente('f')){
                    posSec[1]++;
                    cav.getMatriz()[pos[0]][pos[1]+1].setComps(new Fedor(posSec, cav), cav.getMatriz()[pos[0]][pos[1]+1].espacoVazio());
                    posSec[1] = pos[1];
                }
            break;    
        }
    }
}
