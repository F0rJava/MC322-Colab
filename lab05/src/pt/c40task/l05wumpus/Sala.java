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

    public boolean getVisitada(){
        return this.visitada;
    }

    public String getPrioComponente(){
        String aux = "";
        return aux;
    }
    public boolean temComponente(char c){
        //Método que verifica a existência de um Componente com o symbol == c no vetor de Componente da Sala
        for(int i = 0; i < 6; i++){
            if(comps[i] != null){
                if(comps[i].getSymbol() == c)
                    return true;
            }
            else //Acabaram os Componente da Sala
                return false;
        }
        return false;
    }
    public void mataWumpus(){
        //Método feito para o herói "matar" o Wumpus, alterando seu símbolo para ' '
        for(int i = 0; i < 6; i++){
            if(comps[i] != null){
                if(comps[i].getSymbol() == 'W'){ //Como só podemos ter um Wumpus por sala, assim que acharmos no vetor podemos sair do for
                    comps[i].setSymbol(' ');
                    break;
                }
            }
        }
    }
}
