package pt.c40task.l05wumpus;

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
      String movements = tk.retrieveMovements();
      
      //cria o objeto caverna
      Caverna caverna = new Caverna();

      //cria o objeto montador
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

      //altera o status visitado da posição do heroi para true
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

      //imprime e registra a caverna a cada etapa
      for(int i=0; i<movements.length(); i++){
         boolean saiu = false;
         if(movements.charAt(i) == 'c' ||movements.charAt(i) == 'k' || movements.charAt(i) == 'q'){
            saiu = controle.acao(movements.charAt(i), saiu);
            //checa se saiu e muda o status do controle
            if(saiu && heroi.ouro == 1){
               controle.setSatuts('w');
               break;
            }
         }
         else{
            controle.acao(movements.charAt(i));
         }
         
         //imprime a caverna
         for(int j=0; j<caverna.getMatriz().length; j++){
            for(int k=0; k<caverna.getMatriz().length; k++){
               impressaoCaverna[j][k] = caverna.getMatriz()[j][k].getPrioComponente();
               System.out.printf("%c ", impressaoCaverna[j][k]);
            }
            System.out.printf("\n");
         }
         System.out.println("Player: Sting");
         System.out.println("Score: " + controle.getPontos());

         //imprime mensagens de fim de jogo: perdeu, venceu ou saiu do jogo
         if(controle.getStatus()=='n'){
            System.out.println("Voce perdeu =( ...");
            break;
         }
         else if(controle.getStatus()=='w'){
            System.out.println("Voce ganhou =D !!!");
            break;
         }
         else if(saiu && controle.getStatus()=='x'){
            System.out.println("Volte sempre !");
            break;
         }
         tk.writeBoard(impressaoCaverna, controle.getPontos(), controle.getStatus());
      }
      
      tk.stop();
   }

}
