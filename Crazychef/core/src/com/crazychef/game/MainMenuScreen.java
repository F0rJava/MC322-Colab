package com.crazychef.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.controller.Controller;

//classe do Menu Principal, que seleciona as fases
public class MainMenuScreen implements Screen{
    final Crazychef game;
    Controller controller;
    Texture mainMenuBackground;
    Texture gameTitle;
    OrthographicCamera camera;

    public MainMenuScreen(Crazychef game, Controller controller) {
        this.game = game;
        this.controller = controller;
        mainMenuBackground = new Texture(Gdx.files.internal("Kitchen/MainMenuBackground.png"));
        gameTitle = new Texture(Gdx.files.internal("gameTitle.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
    }



    @Override
    public void render(float delta){

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin(); //inicia a renderização
        game.batch.draw(mainMenuBackground, 0,0, 1280, 720);//imagem do fundo
        game.batch.draw(gameTitle, 400, 450);//imagem do titulo
        game.batch.end();//finaliza a renderização

        if(Gdx.input.isTouched()){
            game.setScreen(new ScreenLevel1(game, this.controller));
            dispose();
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
    public void show() {
    }
}
