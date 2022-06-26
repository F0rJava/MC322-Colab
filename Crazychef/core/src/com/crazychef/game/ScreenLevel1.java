package com.crazychef.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.controller.Controller;
import com.controller.OrderController;
import com.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.utils.TimeUtils;
import com.models.food.Plate;
import com.models.mapdesign.*;
import com.sun.tools.javac.jvm.Gen;
import sun.tools.jconsole.Tab;

import javax.swing.*;
import java.util.ArrayList;


public class ScreenLevel1 implements Screen{
    final Crazychef game;
    private Controller controller;
    private OrderController orderController;
    private Texture tableImageH;
    private Texture tableImageV;
    private Texture tableImageFront;
    private OrthographicCamera camera;
    private Chef chef;
    private Kitchen kitchen;

    public ScreenLevel1(final Crazychef game, Controller controller){
        this.game = game;
        this.controller = controller;
        this.orderController = new OrderController(kitchen);
        controller.setLevelTime(180); //tempo de duração da fase (180s)

        tableImageH = new Texture(Gdx.files.internal("Kitchen/tableH.png"));
        tableImageV = new Texture(Gdx.files.internal("Kitchen/tableV.png"));
        tableImageFront = new Texture(Gdx.files.internal("Kitchen/tableFront.png"));

        camera = new OrthographicCamera();//camera
        camera.setToOrtho(false, 1280, 720);
        kitchen = new Kitchen(10, 17, new Texture(Gdx.files.internal("Kitchen/chao_fases.png")));

        chef = new Chef(640,320);//pos na matriz [4][8]
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

        //Gerar as mesas da fase
        Table auxTable = null;
        //mesas que estão na horizontal, na matriz de [6][1] ate [6][7]
        for(int j=1; j<6;j++){
            if(kitchen.getFloor(6, j).dontHaveActors()) {
                Table tableV = new Table(80 * j, 480);
                kitchen.getFloor(6, j).addActors(tableV);
            }
            auxTable = (Table) kitchen.getFloor(6, j).getTable();
            game.batch.draw(tableImageV, auxTable.x, auxTable.y, auxTable.width, auxTable.height);
        }
        if(kitchen.getFloor(6, 7).dontHaveActors()) {
            Table tableV = new Table(80 * 7, 480);
            kitchen.getFloor(6, 7).addActors(tableV);
        }
        auxTable = (Table) kitchen.getFloor(6, 7).getTable();
        game.batch.draw(tableImageV, auxTable.x, auxTable.y, auxTable.width, auxTable.height);

        //mesas que estão na horizontal, na matriz de [6][9] ate [6][15]
        for(int j=9; j<15;j++){
            if(kitchen.getFloor(6, j).dontHaveActors()) {
                Table tableV = new Table(80 * j, 480);
                kitchen.getFloor(6, j).addActors(tableV);
            }
            auxTable = (Table) kitchen.getFloor(6, j).getTable();
            game.batch.draw(tableImageV, auxTable.x, auxTable.y, auxTable.width, auxTable.height);
        }

        //Cria o fim da mesa na posicao [0][1]
        if(kitchen.getFloor(0, 1).dontHaveActors()) {
            Table tableFront1 = new Table(80, 0);
            kitchen.getFloor(0, 1).addActors(tableFront1);
        }
        auxTable = (Table) kitchen.getFloor(0, 1).getTable();
        game.batch.draw(tableImageFront, auxTable.x, auxTable.y, auxTable.width, auxTable.height);

        //Cria mesas que estão na vertical
        if(kitchen.getFloor(3,1).dontHaveActors()) {
            Table tableH = new Table(80, 80 * 3);
            kitchen.getFloor(3, 1).addActors(tableH);
        }
        auxTable = (Table) kitchen.getFloor(3, 1).getTable();
        game.batch.draw(tableImageH, auxTable.x, auxTable.y, auxTable.width, auxTable.height);
        for(int i=5; i<7; i++){
            if(kitchen.getFloor(i,1).dontHaveActors()) {
                Table tableH = new Table(80, 80 * i);
                kitchen.getFloor(i, 1).addActors(tableH);
            }
            auxTable = (Table) kitchen.getFloor(i, 1).getTable();
            game.batch.draw(tableImageH, auxTable.x, auxTable.y, auxTable.width, auxTable.height);
        }
        if(kitchen.getFloor(1,1).dontHaveActors()) {
            Table tableH = new Table(80, 80);
            kitchen.getFloor(1, 1).addActors(tableH);
        }
        auxTable = (Table) kitchen.getFloor(1, 1).getTable();
        game.batch.draw(tableImageH, auxTable.x, auxTable.y, auxTable.width, auxTable.height);

        //Cria o outro final da mesa
        if(kitchen.getFloor(0, 14).dontHaveActors()) {
            Table tableFront2 = new Table(1120, 0);
            kitchen.getFloor(0, 14).addActors(tableFront2);
        }
        auxTable = (Table) kitchen.getFloor(0, 14).getTable();
        game.batch.draw(tableImageFront, auxTable.x, auxTable.y, auxTable.width, auxTable.height);

        if(kitchen.getFloor(2, 14).dontHaveActors()) {
            Table tableH = new Table(1120, 80 * 2);
            kitchen.getFloor(2, 14).addActors(tableH);
        }
        auxTable = (Table) kitchen.getFloor(2, 14).getTable();
        game.batch.draw(tableImageH, auxTable.x, auxTable.y, auxTable.width, auxTable.height);

        for(int i=4; i<7; i++){
            if(kitchen.getFloor(i, 14).dontHaveActors()) {
                Table tableH = new Table(1120, 80 * i);
                kitchen.getFloor(i, 14).addActors(tableH);
            }
            auxTable = (Table) kitchen.getFloor(i, 14).getTable();
            game.batch.draw(tableImageH, auxTable.x, auxTable.y, auxTable.width, auxTable.height);
        }

        //Gerador de hamburguer
        Generator auxGen;
        if(kitchen.getFloor(1, 14).dontHaveActors()) {
            Generator genBurger = new Generator(1120, 80, "burger", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
            kitchen.getFloor(1, 14).addActors(genBurger);
        }
        auxGen = (Generator) kitchen.getFloor(1, 14).getGen();
        game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);

        //Gera a comida caso não tenha
        if(kitchen.getFloor(1, 14).getFood() == null) {
            Generator auxGenBurger = (Generator) kitchen.getFloor(1, 14).getGen();
            kitchen.getFloor(1, 14).addActors(auxGenBurger.generateFood());
        }
        Food aux = (Food) kitchen.getFloor(1, 14).getFood();
        game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);

        //Gerador de prato
        if(kitchen.getFloor(2, 1).dontHaveActors()) {
            Generator genPlate = new Generator(80, 160, "plate", new Texture(Gdx.files.internal("Kitchen/tableH.png")));
            kitchen.getFloor(2, 1).addActors(genPlate);
        }
        auxGen = (Generator) kitchen.getFloor(2, 1).getGen();
        game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);

        //Gera um outro prato caso nao tenha
        if(kitchen.getFloor(2, 1).getFood() == null) {
            Generator auxGenPlate = (Generator) kitchen.getFloor(2,1).getGen();
            kitchen.getFloor(2, 1).addActors(auxGenPlate.generateFood());
        }
        aux = (Food) kitchen.getFloor(2, 1).getFood();
        game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);

        //Gerador de pao
        if(kitchen.getFloor(3, 14).dontHaveActors()) {
            Generator genPlate = new Generator(80*14, 80*3, "bun", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
            kitchen.getFloor(3, 14).addActors(genPlate);
        }
        auxGen = (Generator) kitchen.getFloor(3, 14).getGen();
        game.batch.draw(auxGen.getTexture(), auxGen.x, auxGen.y, auxGen.width, auxGen.height);

        //Gera um outro pao caso nao tenha
        if(kitchen.getFloor(3, 14).getFood() == null) {
            Generator auxGenPlate = (Generator) kitchen.getFloor(3,14).getGen();
            kitchen.getFloor(3, 14).addActors(auxGenPlate.generateFood());
        }
        aux = (Food) kitchen.getFloor(3, 14).getFood();
        game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);

        //Lixeira
        if(kitchen.getFloor(6, 6).dontHaveActors()){
            Trash trash = new Trash(80*6, 80*6, new Texture(Gdx.files.internal("Kitchen/trashImage.png")), kitchen.getFloor(6, 6));
            kitchen.getFloor(6, 6).addActors(trash);
        }
        Trash auxT = (Trash) kitchen.getFloor(6, 6).getTrash();
        auxT.deleteFood();
        game.batch.draw(auxT.getTexture(),auxT.x, auxT.y, auxT.width, auxT.height);

        //Fogão Horizontal
        if(kitchen.getFloor(6, 8).dontHaveActors()) {
            Oven oven = new Oven(640, 80*6, new Texture(Gdx.files.internal("Kitchen/ovenH.png")));
            kitchen.getFloor(6, 8).addActors(oven);
        }
        Oven auxO = (Oven) kitchen.getFloor(6, 8).getOven();
        game.batch.draw(auxO.getTexture(),auxO.x, auxO.y, auxO.width, auxO.height);
        aux = (Food) kitchen.getFloor(6, 8).getFood();
        if(aux != null && aux.getCookable()){
            auxO.startCooking(aux, delta);
        }

        //Fogao Vertical
        if(kitchen.getFloor(4, 1).dontHaveActors()) {
            Oven oven = new Oven(80, 80*4, new Texture(Gdx.files.internal("Kitchen/ovenV.png")));
            kitchen.getFloor(4, 1).addActors(oven);
        }
        auxO = (Oven) kitchen.getFloor(4, 1).getOven();
        game.batch.draw(auxO.getTexture(),auxO.x, auxO.y, auxO.width, auxO.height);
        aux = (Food) kitchen.getFloor(4, 1).getFood();
        if(aux != null && aux.getCookable()){
            auxO.startCooking(aux, delta);
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

        //movimento
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
        if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) game.setScreen(new MainMenuScreen(game, this.controller));

        //desenha o tempo da fase
        game.font.draw(game.batch, String.valueOf(controller.getLevelTime()/60)+":"+String.format("%02d",controller.getLevelTime()%60),100, 100);
        game.batch.end();

        //nao deixa o chef sair das bordas da tela
        if (chef.x < 0)
            chef.x = 0;
        if (chef.x > 1280 - 80)
            chef.x = 1280 - 80;
        if (chef.y > 720 - 80)
            chef.y = 720 - 80;
        if (chef.y < 0)
            chef.y = 0;
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
