package com.crazychef.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.controller.Controller;

public class PauseScreen implements Screen {
    private final Crazychef game;
    private Screen screen;
    private Texture pauseScreenTexture;
    private OrthographicCamera camera;

    public PauseScreen(Crazychef game, Screen levelScreen) {
        this.game = game;
        this.screen = levelScreen;
        pauseScreenTexture = new Texture(Gdx.files.internal("Food/Level1/endGameLevel1.png"));
        camera = new OrthographicCamera();//camera
        camera.setToOrtho(false, 1280, 720);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(pauseScreenTexture, 0,0, 1280, 720);
        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/UVatrjTBDF_CifhmfyVHP_hy.ttf.fnt"));
        font.getData().setScale(1.8f, 1.8f);
        font.draw(game.batch, "GAME PAUSED",450, 400);
        font.draw(game.batch, "PRESS ESC TO CONTINUE",300, 330);
        font.draw(game.batch, "PRESS P TO QUIT",400, 260);
        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(screen);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)){
            game.setScreen(new MainMenuScreen(game, new Controller()));
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
}
