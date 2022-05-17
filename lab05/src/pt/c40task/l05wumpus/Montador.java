package pt.c40task.l05wumpus;

public class Montador{
    private Caverna cav;
    
    public void conecta(Caverna cav){
        this.cav = cav;
    }

    public void montaCaverna(String info){
        int[] pos = new int[2]; //vetor auxiliar, que atualiza com a posiçao passada no arquivo
        pos[0] = Character.getNumericValue(info.charAt(0)) - 1;
        pos[1] = Character.getNumericValue(info.charAt(2)) - 1;
        
        int espacoVazio = 0;

        //percorre o vetor de componente da sala ate encontrar um espaço vazio
        for(int i=0; i<6; i++){
            if(cav.getMatriz()[pos[0]][pos[1]].getComps()[i] != null){
                espacoVazio = i;
            }
        }
        
        //adiciona um novo objeto de cada componente na sala especificada, na posição vazia
        switch(info.charAt(4)){
            case 'P':
                cav.getMatriz()[0][0].getComps()[espacoVazio] = new Heroi(1, pos, cav);
            case 'B':
                cav.getMatriz()[pos[0]][pos[1]].getComps()[espacoVazio] = new Buraco(pos, cav);               
            case 'O':
                cav.getMatriz()[pos[0]][pos[1]].getComps()[espacoVazio] = new Ouro(pos, cav);
            case 'W':
                cav.getMatriz()[pos[0]][pos[1]].getComps()[espacoVazio] = new Wumpus(pos, cav);
        };
    }

}
