package com.crazychef.game;

import com.controller.Controller;
import com.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.*;
import com.models.mapdesign.Generator;
import com.models.mapdesign.Kitchen;
import com.models.mapdesign.Table;


public class ScreenLevel1 implements Screen{
    final Crazychef game;
    private Controller controller;
    private Texture tableImageH;
    private Texture tableImageV;
    private Texture tableImageFront;
    private OrthographicCamera camera;
    private Chef chef;
    private Kitchen kitchen;

    public ScreenLevel1(final Crazychef game, Controller controller){
        this.game = game;
        this.controller = controller;

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
        controller.setTimer();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin(); //inicia a renderização
        game.batch.draw(kitchen.getTexture(), 0,0, 1280, 720);//imagem do fundo

        //cria o mapa da sala
        for(int j=1; j<15;j++){
            Table tableV = new Table(80*j,480);
            kitchen.getFloor(6, j).addActors(tableV);
            game.batch.draw(tableImageV,tableV.x, tableV.y, tableV.width,tableV.height);
        }
        Table tableFront1 =  new Table(80, 0);
        kitchen.getFloor(0, 1). addActors(tableFront1);
        game.batch.draw(tableImageFront,tableFront1.x, tableFront1.y, tableFront1.width,tableFront1.height);
        for(int i=1; i<7; i++){
            Table tableH = new Table(80, 80*i);
            kitchen.getFloor(i, 1). addActors(tableH);
            game.batch.draw(tableImageH,tableH.x, tableH.y, tableH.width,tableH.height);
        }
        Table tableFront2 =  new Table(1120, 0);
        kitchen.getFloor(0, 1). addActors(tableFront2);
        game.batch.draw(tableImageFront,tableFront2.x, tableFront2.y, tableFront2.width,tableFront2.height);
        for(int i=2; i<7; i++){
            Table tableH = new Table(1120, 80*i);
            kitchen.getFloor(i, 14). addActors(tableH);
            game.batch.draw(tableImageH,tableH.x, tableH.y, tableH.width,tableH.height);
        }

        //Gerador de hamburguer
        Generator genBurger = new Generator(1120, 80, "burger", new Texture(Gdx.files.internal("Kitchen/genBurgerImage.png")));
        kitchen.getFloor(1, 14).addActors(genBurger);
        game.batch.draw(genBurger.getTexture(),genBurger.x, genBurger.y, genBurger.width,genBurger.height);
        kitchen.getFloor(1, 14).addActors(genBurger.generateFood());
        Food aux = (Food) kitchen.getFloor(1, 14).getFood();
        game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);

        //Gerador de prato
        Generator genPlate = new Generator(80, 160, "plate", new Texture(Gdx.files.internal("Kitchen/tableH.png")));
        kitchen.getFloor(2, 1).addActors(genPlate);
        game.batch.draw(genPlate.getTexture(), genPlate.x, genPlate.y ,genPlate.width, genPlate.height);
        kitchen.getFloor(2, 1).addActors(genPlate.generateFood());
        aux = (Food) kitchen.getFloor(2, 1).getFood();
        game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);

        //desenhando as comidas que estão pelo mapa
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 17; j ++){
                aux = (Food) kitchen.getFloor(i, j).getFood();
                if(aux != null)
                    game.batch.draw(aux.getBaseTexture(), aux.x, aux.y, aux.width, aux.height);
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
                            chef.getHand().get(i).y + chef.getHand().get(i).getOrientation()[1], chef.getHand().get(i).getWidth(), chef.getHand().get(i).getHeight());
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
        if(Gdx.input.isKeyJustPressed(Keys.Q)){
            controller.release();
        }
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
