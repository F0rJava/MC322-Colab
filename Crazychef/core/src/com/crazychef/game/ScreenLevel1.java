package com.crazychef.game;

import com.controller.Controller;
import com.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.*;


public class ScreenLevel1 implements Screen{
    final Crazychef game;
    Controller controller;
    Texture chefFront;
    Texture Background;
    OrthographicCamera camera;
    Chef chef;

    public ScreenLevel1(final Crazychef game, Controller controller){
        this.game = game;
        this.controller = controller;
        chefFront = new Texture(Gdx.files.internal("Chef/chefFront.png"));//textura do chef
        Background = new Texture(Gdx.files.internal("Kitchen/chao_fases.png"));//textura da fase

        camera = new OrthographicCamera();//camera
        camera.setToOrtho(false, 1280, 720);

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
        game.batch.draw(Background, 0,0, 1280, 720);//imagem do fundo
        //game.batch.draw(chef, chef.x, chef.y);
        game.batch.draw(chefFront, chef.x, chef.y,chef.width,chef.height);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Keys.LEFT))
            controller.left();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            controller.right();
        if (Gdx.input.isKeyPressed(Keys.UP))
            controller.up();
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            controller.down();

        //nao deixa o chef sair das bordas da tela
        if (chef.x < 0)
            chef.x = 0;
        if (chef.x > 1280 - 60)
            chef.x = 1280 - 60;
        if (chef.y > 720 - 60)
            chef.y = 720 - 60;
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
        Background.dispose();
    }

    @Override
    public void show(){
    }
}
