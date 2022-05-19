package pt.c40task.l05wumpus;
import java.util.Scanner;

public class AppWumpus {

   public static void main(String[] args) {
      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);  
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      //recupera as entradas de caverna e movimentos dos arquivos
      String cave[][] = tk.retrieveCave();

      //cria o objeto caverna
      Caverna caverna = new Caverna();

      //cria o objeto montador e o conecta com a caverna
      Montador montador = new Montador();
      montador.conecta(caverna);

      //instancia o heroi que sempre começa na sala 1,1
      int[] posInicial = {0,0};
      Heroi heroi = new Heroi(1, posInicial, caverna);
      caverna.getMatriz()[0][0].setComps(heroi, 0);

      //monta a caverna
      for(int i=1; i<cave.length; i++){
         montador.montaCaverna(cave[i]);
      }

      //altera o status visitado da posição do heroi para true, pois o herói começa nesta posição
      caverna.getMatriz()[0][0].visitou();

      //vetor de char para impressao da caverna
      char impressaoCaverna[][] = new char[4][4];

      //instancia o controle
      Controle controle = new Controle(heroi);

      //imprime o estado inicial da caverna e registra no arquivo
      for(int j=0; j<caverna.getMatriz().length; j++){
         for(int k=0; k<caverna.getMatriz().length; k++){
            impressaoCaverna[j][k] = caverna.getMatriz()[j][k].getPrioComponente();
            System.out.printf("%c ", impressaoCaverna[j][k]);
         }
         System.out.printf("\n");
      }
      System.out.println("Player: Sting");
      System.out.println("Score: " + controle.getPontos());
      tk.writeBoard(impressaoCaverna, controle.getPontos(), controle.getStatus());

      boolean saiu = false;
      if(arquivoMovimentos == null){
         //Modo interativo

         while(!saiu){
            int auxPontos = controle.getPontos();
            Scanner keyboard = new Scanner(System.in);
            String commands = keyboard.nextLine();
            if(commands.equals("w") || commands.equals("a") || commands.equals("s") || commands.equals("d") || 
               commands.equals("k") || commands.equals("c") || commands.equals("q")){
               if(commands.charAt(0) == 'c' || commands.charAt(0) == 'k' || commands.charAt(0) == 'q')
                  saiu = controle.acao(commands.charAt(0), saiu);
               else
                  controle.acao(commands.charAt(0));
               imprimeCaverna(caverna, tk, impressaoCaverna, controle, saiu);
               if(controle.getStatus() == 'L')
                  saiu = true;
               //imprime comentários de ação
               if(commands.charAt(0) == 'k' && heroi.getFlecha() > 0)
                  System.out.println("Flecha Equipada!");
               if(commands.charAt(0) == 'c' && auxPontos < controle.getPontos())
                  System.out.println("Ouro Pego!");   
            }
         }
      }
      else{
         //Modo automático

         //imprime e registra a caverna a cada etapa
         String movements = tk.retrieveMovements();
         for(int i=0; i<movements.length(); i++){
            if(movements.charAt(i) == 'w' || movements.charAt(i) == 'a' || movements.charAt(i) == 's' || movements.charAt(i) == 'd' || 
               movements.charAt(i) == 'c' || movements.charAt(i) == 'k' || movements.charAt(i) == 'q'){
               int auxPontos = controle.getPontos();
               if(movements.charAt(i) == 'c' ||movements.charAt(i) == 'k' || movements.charAt(i) == 'q')
                  saiu = controle.acao(movements.charAt(i), saiu);
               else
                  controle.acao(movements.charAt(i));
               imprimeCaverna(caverna, tk, impressaoCaverna, controle, saiu);
               if(controle.getStatus() == 'L')
                  saiu = true;
               //imprime comentários de ação
               if(movements.charAt(i) == 'k' && heroi.getFlecha() > 0)
                  System.out.println("Flecha Equipada!");
               if(movements.charAt(i) == 'c' && auxPontos < controle.getPontos())
                  System.out.println("Ouro Pego!");
            }   
         }
      }
      tk.stop();
   }

   public static void imprimeCaverna(Caverna caverna, Toolkit tk, char[][] impressaoCaverna, Controle controle, boolean saiu){
      for(int j=0; j<caverna.getMatriz().length; j++){
         for(int k=0; k<caverna.getMatriz().length; k++){
            impressaoCaverna[j][k] = caverna.getMatriz()[j][k].getPrioComponente();
            System.out.printf("%c ", impressaoCaverna[j][k]);
         }
         System.out.printf("\n");
      }
      System.out.println("Player: Sting");
      System.out.println("Score: " + controle.getPontos());

      tk.writeBoard(impressaoCaverna, controle.getPontos(), controle.getStatus());      
      //imprime mensagens de fim de jogo: perdeu, venceu ou saiu do jogo
      if(controle.getStatus()=='L'){
         System.out.println("Voce perdeu =( ...");
         return;
      }
      else if(controle.getStatus()=='W'){
         System.out.println("Voce ganhou =D !!!");
         return;
      }
      else if(saiu && controle.getStatus()=='P'){
         System.out.println("Volte sempre !");
         return;
      }
   }
}