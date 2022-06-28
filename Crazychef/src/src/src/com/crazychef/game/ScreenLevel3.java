package com.crazychef.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.controller.Controller;
import com.controller.OrderController;
import com.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.*;
import com.models.mapdesign.*;
import java.util.ArrayList;


public class ScreenLevel3 implements Screen{
    final Crazychef game;
    private Controller controller;
    private OrderController orderController;
    private Texture tableImageH;
    private Texture tableImageV;
    private Texture tableImageFront;
    private OrthographicCamera camera;
    private Chef chef;
    private Kitchen kitchen;

    public ScreenLevel3(final Crazychef game, Controller controller){
        this.game = game;
        this.controller = controller;
        this.orderController = new OrderController(5);
        controller.setLevelTime(180); //tempo de duração da fase (180s)

        tableImageH = new Texture(Gdx.files.internal("Kitchen/tableH.png"));
        tableImageV = new Texture(Gdx.files.internal("Kitchen/tableV.png"));
        tableImageFront = new Texture(Gdx.files.internal("Kitchen/tableFront.png"));

        camera = new OrthographicCamera();//camera
        camera.setToOrtho(false, 1280, 720);
        kitchen = new Kitchen(10, 17, new Texture(Gdx.files.internal("Kitchen/chao_fases.png")));

        chef = new Chef(80*12,80*5);//pos na matriz [4][8]
        kitchen.getFloor(Math.round(chef.y/80), Math.round(chef.x/80)).addActors(chef);
        controller.setChef(chef);
        controller.setKitchen(kitchen);
    }

    @Override
    public void render(float delta){
        camera.update();
        controller.updateTime(delta);//atualiza o tempo da fase para o controle
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin(); //inicia a renderização
        game.batch.draw(kitchen.getTexture(), 0,0, 1280, 720);//imagem do fundo

        //cria o mapa da sala
        //Variaveis auxiliares
        Table auxTable = null;
        Generator auxGen;
        CutBoard auxCut;
        Food aux;
        Oven auxO;
        Hole auxH;

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 16; j++) {
                if((i == 0 || i == 6) && (j == 0 || (j >= 6 && j <= 9) )){//Mesas
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Table table = new Table(80*j, 80*i);
                        kitchen.getFloor(i, j).addActors(table);
                    }
                    auxTable = (Table) kitchen.getFloor(i, j).getTable();
                    game.batch.draw(tableImageV, auxTable.x, auxTable.y, auxTable.width, auxTable.height);
                } else if ((i == 0 || i == 6) && j == 1) { //Lixeiras
                    if(kitchen.getFloor(i, j).dontHaveActors()){
                        Trash trash = new Trash(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/trashImage.png")), kitchen.getFloor(i, j));
                        kitchen.getFloor(i, j).addActors(trash);
                    }
                    Trash auxT = (Trash) kitchen.getFloor(i, j).getTrash();
                    auxT.deleteFood();
                    game.batch.draw(auxT.getTexture(),auxT.x, auxT.y, auxT.width, auxT.height);
                } else if ((i == 0 || i == 6) && j == 2) { //Gerador de Queijo
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Generator genBurger = new Generator(80*j, 80*i, "cheese", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
                        kitchen.getFloor(i, j).addActors(genBurger);
                    }
                    auxGen = (Generator) kitchen.getFloor(i, j).getGen();
                    game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);
                    if(kitchen.getFloor(i, j).getFood() == null) {
                        Generator auxGenBurger = (Generator) kitchen.getFloor(i, j).getGen();
                        kitchen.getFloor(i, j).addActors(auxGenBurger.generateFood());
                    }
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);
                } else if ((i == 0 || i == 6) && j == 3) { //Tábua de corte
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        CutBoard cutBoard = new CutBoard(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/cuttingBoardV.png")));
                        kitchen.getFloor(i, j).addActors(cutBoard);
                    }
                    auxCut = (CutBoard) kitchen.getFloor(i, j).getCutBoard();
                    game.batch.draw(auxCut.getTexture(), auxCut.x, auxCut.y, auxCut.width, auxCut.height);
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    if(aux != null) {
                        if(!aux.getCut()) {
                            auxCut.startCutting(aux, delta);
                            chef.setCanMove(false);
                        }
                        else
                            chef.setCanMove(true);
                    }
                    else
                        chef.setCanMove(true);
                } else if ((i == 0 || i == 6) && j == 4) {//Fornos
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Oven oven = new Oven(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/ovenH.png")));
                        kitchen.getFloor(i, j).addActors(oven);
                    }
                    auxO = (Oven) kitchen.getFloor(i, j).getOven();
                    game.batch.draw(auxO.getTexture(),auxO.x, auxO.y, auxO.width, auxO.height);
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    auxO.startCooking(aux, delta);
                } else if (i == 0 && j == 5) { //Gerador de Tomate
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Generator genBurger = new Generator(80*j, 80*i, "tomato", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
                        kitchen.getFloor(i, j).addActors(genBurger);
                    }
                    auxGen = (Generator) kitchen.getFloor(i, j).getGen();
                    game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);
                    if(kitchen.getFloor(i, j).getFood() == null) {
                        Generator auxGenBurger = (Generator) kitchen.getFloor(i, j).getGen();
                        kitchen.getFloor(i, j).addActors(auxGenBurger.generateFood());
                    }
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);
                } else if (i == 6 && j == 5) {//Gerador de Alface
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Generator genBurger = new Generator(80*j, 80*i, "lettuce", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
                        kitchen.getFloor(i, j).addActors(genBurger);
                    }
                    auxGen = (Generator) kitchen.getFloor(i, j).getGen();
                    game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);
                    if(kitchen.getFloor(i, j).getFood() == null) {
                        Generator auxGenBurger = (Generator) kitchen.getFloor(i, j).getGen();
                        kitchen.getFloor(i, j).addActors(auxGenBurger.generateFood());
                    }
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);
                } else if (i == 3 && ((j >=6 && j <= 9) || j == 15)) { //Mesas
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Table table = new Table(80*j, 80*i);
                        kitchen.getFloor(i, j).addActors(table);
                    }
                    auxTable = (Table) kitchen.getFloor(i, j).getTable();
                    game.batch.draw(tableImageV, auxTable.x, auxTable.y, auxTable.width, auxTable.height);
                } else if (i == 3 && j == 9) { //Tábua de Corte
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        CutBoard cutBoard = new CutBoard(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/cuttingBoardH.png")));
                        kitchen.getFloor(i, j).addActors(cutBoard);
                    }
                    auxCut = (CutBoard) kitchen.getFloor(i, j).getCutBoard();
                    game.batch.draw(auxCut.getTexture(), auxCut.x, auxCut.y, auxCut.width, auxCut.height);
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    if(aux != null) {
                        if(!aux.getCut()) {
                            auxCut.startCutting(aux, delta);
                            chef.setCanMove(false);
                        }
                        else
                            chef.setCanMove(true);
                    }
                    else
                        chef.setCanMove(true);
                } else if (i == 3 && j == 10) { //Forno
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Oven oven = new Oven(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/ovenH.png")));
                        kitchen.getFloor(i, j).addActors(oven);
                    }
                    auxO = (Oven) kitchen.getFloor(i, j).getOven();
                    game.batch.draw(auxO.getTexture(),auxO.x, auxO.y, auxO.width, auxO.height);
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    auxO.startCooking(aux, delta);
                } else if (i == 3 && j == 11) { //Gerador de Prato
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Generator genBurger = new Generator(80*j, 80*i, "plate", new Texture(Gdx.files.internal("Kitchen/tableV.png")));
                        kitchen.getFloor(i, j).addActors(genBurger);
                    }
                    auxGen = (Generator) kitchen.getFloor(i, j).getGen();
                    game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);
                    if(kitchen.getFloor(i, j).getFood() == null) {
                        Generator auxGenBurger = (Generator) kitchen.getFloor(i, j).getGen();
                        kitchen.getFloor(i, j).addActors(auxGenBurger.generateFood());
                    }
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);
                } else if (i == 3 && (j == 12 || j == 14)) { //Gerador de Hamburguer
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Generator genBurger = new Generator(80*j, 80*i, "burger", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
                        kitchen.getFloor(i, j).addActors(genBurger);
                    }
                    auxGen = (Generator) kitchen.getFloor(i, j).getGen();
                    game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);
                    if(kitchen.getFloor(i, j).getFood() == null) {
                        Generator auxGenBurger = (Generator) kitchen.getFloor(i, j).getGen();
                        kitchen.getFloor(i, j).addActors(auxGenBurger.generateFood());
                    }
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);
                } else if (i == 3 && j == 13) {//Gerador de pão
                    if(kitchen.getFloor(i, j).dontHaveActors()) {
                        Generator genBun = new Generator(80*j, 80*i, "bun", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
                        kitchen.getFloor(i, j).addActors(genBun);
                    }
                    auxGen = (Generator) kitchen.getFloor(i, j).getGen();
                    game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);
                    if(kitchen.getFloor(i, j).getFood() == null) {
                        Generator auxGenBun = (Generator) kitchen.getFloor(i,j).getGen();
                        kitchen.getFloor(i, j).addActors(auxGenBun.generateFood());
                    }
                    aux = (Food) kitchen.getFloor(i, j).getFood();
                    game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);
                } else if ( i == 6 && j == 15) { //Delivery
                    if(kitchen.getFloor(i, j).dontHaveActors()){
                        OrderDelivery delivery = new OrderDelivery(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/orderTableUpH.png")), kitchen.getFloor(i, j));
                        kitchen.getFloor(i, j).addActors(delivery);
                    }
                    OrderDelivery auxOrd = (OrderDelivery) kitchen.getFloor(i, j).getOrderDelivery();
                    orderController.connectOrderDelivery(auxOrd);
                    orderController.checkOrders();
                    game.batch.draw(auxOrd.getTexture(),auxOrd.x, auxOrd.y, auxOrd.width, auxOrd.height);
                } else if ( i == 0 && j == 15) { //Delivery
                        if(kitchen.getFloor(i, j).dontHaveActors()){
                            OrderDelivery delivery = new OrderDelivery(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/orderTableUpH.png")), kitchen.getFloor(i, j));
                            kitchen.getFloor(i, j).addActors(delivery);
                        }
                        OrderDelivery auxOrd = (OrderDelivery) kitchen.getFloor(i, j).getOrderDelivery();
                        orderController.connectOrderDelivery(auxOrd);
                        orderController.checkOrders();
                        game.batch.draw(auxOrd.getTexture(),auxOrd.x, auxOrd.y, auxOrd.width, auxOrd.height);
                } else if ((i == 2 || i == 5) && (j == 6 || j == 9)) { //Buracos
                    if(kitchen.getFloor(i, j).dontHaveActors()){
                        Hole hole = new Hole(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/hole.png")), chef, kitchen.getFloor(i, j), kitchen.getFloor(5, 12));
                        kitchen.getFloor(i, j).addActors(hole);
                    }
                    auxH = (Hole) kitchen.getFloor(i, j).getHole();
                    auxH.fellInTheHole();
                    game.batch.draw(auxH.getTexture(),auxH.x, auxH.y, auxH.width, auxH.height);
                } else if (i == 3 && (j == 1 || j == 4)) {
                    if(kitchen.getFloor(i, j).dontHaveActors()){
                        Hole hole = new Hole(80*j, 80*i, new Texture(Gdx.files.internal("Kitchen/hole.png")), chef, kitchen.getFloor(i, j), kitchen.getFloor(5, 12));
                        kitchen.getFloor(i, j).addActors(hole);
                    }
                    auxH = (Hole) kitchen.getFloor(i, j).getHole();
                    auxH.fellInTheHole();
                    game.batch.draw(auxH.getTexture(),auxH.x, auxH.y, auxH.width, auxH.height);
                }
            }
        }

        //desenhando as comidas que estão pelo mapa
        ArrayList<Actors> auxA;
        int a ;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 17; j ++){
                auxA = kitchen.getFloor(i,j).getActors();
                a = 0;
                for(int k = 0; k < auxA.size(); k++){
                    if(auxA.get(k) instanceof Food) {
                        aux = (Food) auxA.get(k);
                        game.batch.draw(aux.getBaseTexture(), aux.x, aux.y+5*a, aux.width, aux.height);
                        a++;
                    }
                }
            }
        }

        //desenha o chef na tela
        game.batch.draw(chef.getTexture(), chef.x, chef.y, chef.width, chef.height);

        //desenha as comidas que estão na mão do chef
        for(int i = 0; i < chef.getHand().size(); i++){
            if(chef.getHand().get(i).getOrientation()[0] == 0 && chef.getHand().get(i).getOrientation()[1] == 0) {
                game.batch.draw(chef.getHand().get(i).getVanishTexture(), chef.getHand().get(i).x,
                        chef.getHand().get(i).y, chef.getHand().get(i).getWidth(), chef.getHand().get(i).getHeight());
            }
            else {
                game.batch.draw(chef.getHand().get(i).getBaseTexture(), chef.getHand().get(i).x + chef.getHand().get(i).getOrientation()[0],
                        chef.getHand().get(i).y + chef.getHand().get(i).getOrientation()[1] +5*i, chef.getHand().get(i).getWidth(), chef.getHand().get(i).getHeight());
            }
        }

        //Comandos
        if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
            controller.left();
            chef.updateActorsCoordinates();
        }
        if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
            controller.right();
            chef.updateActorsCoordinates();
        }
        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            controller.up();
            chef.updateActorsCoordinates();
        }
        if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            controller.down();
            chef.updateActorsCoordinates();
        }
        if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
            controller.pickup();
            chef.updateActorsCoordinates();
        }
        if(Gdx.input.isKeyJustPressed(Keys.D)){
            controller.release();
        }
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) game.setScreen(new PauseScreen(game, this));

        //chama o metodo que faz pedidos do orderController e desenha os pedidos
        orderController.generateOrders(delta);
        orderController.updateOrders(delta);//atualiza os pedidos
        for(int i=0; i<3; i++){
            if(orderController.getOrders(i)!= null){
                game.batch.draw(orderController.getOrders(i).getBackGround(), orderController.getOrders(i).x, orderController.getOrders(i).y,
                        orderController.getOrders(i).width, orderController.getOrders(i).height);
                game.batch.draw(orderController.getOrders(i).getTimeBarTexture(), orderController.getOrders(i).getTimeBar().x,
                        orderController.getOrders(i).getTimeBar().y, orderController.getOrders(i).getTimeBar().width, orderController.getOrders(i).getTimeBar().height);
                for(int j=0; j<4; j++){
                    if(orderController.getOrders(i).getFood(j)!=null){
                        game.batch.draw(orderController.getOrders(i).getFood(j).getBaseTexture(), orderController.getOrders(i).getFood(j).x,
                                orderController.getOrders(i).getFood(j).y, orderController.getOrders(i).getFood(j).width, orderController.getOrders(i).getFood(j).height);
                    }
                }
            }
        }

        //desenha o tempo da fase
        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/UVatrjTBDF_CifhmfyVHP_hy.ttf.fnt"));
        font.getData().setScale(1.8f, 1.8f);
        font.draw(game.batch, String.valueOf(controller.getLevelTime()/60)+":"+String.format("%02d",controller.getLevelTime()%60),1130, 620);
        font.getData().setScale(1.5f, 1.5f);
        font.draw(game.batch, "TIME",1130, 690);

        //desenha a pontuação
        font.getData().setScale(1.5f, 1.5f);
        font.draw(game.batch, "SCORE",970, 690);
        font.getData().setScale(1.5f, 1.5f);
        font.draw(game.batch, String.format("%04d",orderController.getPoints()),980, 620);

        game.batch.end();

        //nao deixa o chef sair das bordas da tela
        if (chef.x < 0)
            chef.x = 0;
        if (chef.x > 1280 - 80)
            chef.x = 1280 - 80;
        if (chef.y > 560 - 80)
            chef.y = 560 - 80;
        if (chef.y < 0)
            chef.y = 0;

        //fim do jogo
        if(controller.getLevelTime()==0){
            game.setScreen(new EndLevelScreen(game, this.controller, this.orderController, this));
        }

    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void show(){
    }
}
