package pt.c40task.l05wumpus;

public class Sala {
    private Componente[] comps;
    private boolean visitada;

    public Sala(){
        comps = new Componente[6]; //Número total de componentes distintos
        visitada = false;
    }

    public Componente[] getComps(){
        return comps;
    }

    public void setComps(Componente comp, int i){
        this.comps[i] = comp;
    }

    public boolean getVisitada(){
        return this.visitada;
    }

    public char getPrioComponente(){
        //Método que pega o symbol do Componente com maior prioridade, para a impressão das salas
        char aux = ' ';
        if(visitada){
            for(int i = 0; i < comps.length; i++){
                if(comps[i] != null){
                    if(comps[i].getSymbol() == 'O')
                        return 'O';
                    else if(comps[i].getSymbol() == 'W')
                        return 'W';
                    else if(comps[i].getSymbol() == 'B')
                        return 'B';
                    else if(comps[i].getSymbol() == 'P')
                        aux = 'P';
                    else if(comps[i].getSymbol() == 'f' && aux != 'P')
                        aux = 'f';
                    else if(comps[i].getSymbol() == 'b' && aux == ' ')
                        aux = 'b';
                }
            }
            if(aux == ' ')
                return '#';
            return aux;
        }
        return '-';
    }

    public void visitou(){
        this.visitada = true;
    }

    public boolean temComponente(char c){
        //Método que verifica a existência de um Componente com o symbol == c no vetor de Componente da Sala
        for(int i = 0; i < 6; i++){
            if(comps[i] != null){
                if(comps[i].getSymbol() == c)
                    return true;
            }
        }
        return false;
    }
    public void eliminaComponente(char c){
        //Método feito para eliminar um componente do vetor, tornando seu simbolo = ' '
        for(int i = 0; i < 6; i++){
            if(comps[i] != null){
                if(comps[i].getSymbol() == c){ //Como só podemos ter, no máximo, um Componente de cada por sala
                    comps[i].setSymbol(' ');
                    break;
                }
            }
        }
    }
    public void retiraHeroi(){
        //Método que retira o Heroi de dentro desta sala
        for(int i = 0; i < comps.length; i++){
            if(comps[i].getSymbol() == 'P'){
                comps[i] = null;
                break;
            }
        }
    }
    public int espacoVazio(){
        //Método que retorna o primeiro espaço vazio no vetor de componentes, retornando o tamanho do vetor + 1 caso este espaço não exista
        for(int i=0; i<comps.length; i++){
            if(comps[i] == null){
                return i;
            }
        }
        return comps.length+1;
    }
}
