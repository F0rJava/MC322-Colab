package com.crazychef.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Null;
import com.controller.Controller;
import com.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.*;


public class ScreenLevel1 implements Screen{
    final Crazychef game;
    private Controller controller;
    private Texture chefFront;
    private Texture background;
    private Texture tableImageH;
    private Texture tableImageV;
    private OrthographicCamera camera;
    private Chef chef;
    private Objects objectsMatrix[][];

    public ScreenLevel1(final Crazychef game, Controller controller){
        this.game = game;
        this.controller = controller;
        chefFront = new Texture(Gdx.files.internal("Chef/chefFront.png"));//textura do chef
        background = new Texture(Gdx.files.internal("Kitchen/chao_fases.png"));//textura da fase
        tableImageH = new Texture(Gdx.files.internal("Kitchen/tableH.png"));
        tableImageV = new Texture(Gdx.files.internal("Kitchen/tableV.png"));

        camera = new OrthographicCamera();//camera
        camera.setToOrtho(false, 1280, 720);
        objectsMatrix = new Objects[9][16];

        chef = new Chef(640,320);
        chef.width = 80;
        chef.height = 120;
        controller.setChef(chef);

    }

    @Override
    public void render(float delta){
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin(); //inicia a renderização
        game.batch.draw(background, 0,0, 1280, 720);//imagem do fundo
        game.batch.draw(chefFront, chef.x, chef.y,chef.width,chef.height);

        //cria o mapa da sala
        for(int i=1; i<15;i++){
            Table tableV = new Table(80*i,480);
            objectsMatrix[2][i] = tableV;
            game.batch.draw(tableImageV,tableV.x, tableV.y, tableV.width,tableV.height);
        }

        game.batch.draw(chefFront, chef.x, chef.y,chef.width,chef.height);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Keys.LEFT))
            controller.left();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            controller.right();
        if (Gdx.input.isKeyPressed(Keys.UP)){
            if(objectsMatrix[(int) (chef.y/80)][(int) (chef.x/80)+1]==null){
                controller.up();
            }
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN))
            controller.down();

        //nao deixa o chef sair das bordas da tela
        if (chef.x < 0)
            chef.x = 0;
        if (chef.x > 1280 - 80)
            chef.x = 1280 - 80;
        if (chef.y > 720 - 120)
            chef.y = 720 - 120;
        if (chef.y <0)
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
        chefFront.dispose();
        background.dispose();
    }

    @Override
    public void show(){
    }
}
