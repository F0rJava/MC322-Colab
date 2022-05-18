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
            case 'P':
                cav.getMatriz()[0][0].setComps(new Heroi(1, pos, cav), espacoVazio(cav,pos));
                break;
            case 'B':
                cav.getMatriz()[pos[0]][pos[1]].setComps(new Buraco(pos, cav), espacoVazio(cav,pos));
                adicionaSecundario('B', pos, cav);
                break;               
            case 'O':
                cav.getMatriz()[pos[0]][pos[1]].setComps(new Ouro(pos, cav), espacoVazio(cav,pos));
                break;
            case 'W':
                cav.getMatriz()[pos[0]][pos[1]].setComps(new Wumpus(pos, cav), espacoVazio(cav,pos));
                adicionaSecundario('W', pos, cav);
                break;
        };
    }

    //recebe a caverna, que pede para a sala o vetor de componentes
    //e percorre o vetor ate encontrar um espaço vazio
    public int espacoVazio(Caverna cav, int[] pos){
        for(int i=0; i<cav.getMatriz()[pos[0]][pos[1]].getComps().length; i++){
            if(cav.getMatriz()[pos[0]][pos[1]].getComps()[i] == null){
                return i;
            }
        }
        return cav.getMatriz()[pos[0]][pos[1]].getComps().length+1;
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
                    cav.getMatriz()[pos[0]-1][pos[1]].setComps(new Brisa(posSec, cav), espacoVazio(cav,posSec));
                    posSec[0] = pos[0];
                }
                if(pos[0]+1<cav.getMatriz().length && cav.getMatriz()[pos[0]+1][pos[1]].temComponente('b')){
                    posSec[0]++;
                    cav.getMatriz()[pos[0]+1][pos[1]].setComps(new Brisa(posSec, cav), espacoVazio(cav,posSec));
                    posSec[0] = pos[0];
                }
                if(pos[1]-1>=0 && !cav.getMatriz()[pos[0]][pos[1]-1].temComponente('b')){
                    posSec[1]--;
                    cav.getMatriz()[pos[0]][pos[1]-1].setComps(new Brisa(posSec, cav), espacoVazio(cav,posSec));
                    posSec[1] = pos[1];
                }
                if(pos[1]+1<cav.getMatriz().length && !cav.getMatriz()[pos[0]][pos[1]+1].temComponente('b')){
                    posSec[1]++;
                    cav.getMatriz()[pos[0]][pos[1]+1].setComps(new Brisa(posSec, cav), espacoVazio(cav,posSec));
                    posSec[1] = pos[1];
                }
            break;
            case 'W':
                if(pos[0]-1>0 && !cav.getMatriz()[pos[0]-1][pos[1]].temComponente('f')){
                    posSec[0]--;
                    cav.getMatriz()[pos[0]-1][pos[1]].setComps(new Fedor(posSec, cav), espacoVazio(cav,posSec));
                    posSec[0] = pos[0];
                }
                if(pos[0]+1<cav.getMatriz().length && cav.getMatriz()[pos[0]+1][pos[1]].temComponente('f')){
                    posSec[0]++;
                    cav.getMatriz()[pos[0]+1][pos[1]].setComps(new Fedor(posSec, cav), espacoVazio(cav,posSec));
                    posSec[0] = pos[0];
                }
                if(pos[1]-1>0 && !cav.getMatriz()[pos[0]][pos[1]-1].temComponente('f')){
                    posSec[1]--;
                    cav.getMatriz()[pos[0]][pos[1]-1].setComps(new Fedor(posSec, cav), espacoVazio(cav,posSec));
                    posSec[1] = pos[1];
                }
                if(pos[1]+1<cav.getMatriz().length && !cav.getMatriz()[pos[0]][pos[1]+1].temComponente('f')){
                    posSec[1]++;
                    cav.getMatriz()[pos[0]][pos[1]+1].setComps(new Fedor(posSec, cav), espacoVazio(cav,posSec));
                    posSec[1] = pos[1];
                }
            break;    
        }
    }
}
