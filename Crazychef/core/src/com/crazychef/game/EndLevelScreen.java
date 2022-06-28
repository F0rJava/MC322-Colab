package com.crazychef.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.controller.Controller;
import com.controller.OrderController;

public class EndLevelScreen implements Screen {
    private final Crazychef game;
    private Controller controller;
    private OrderController orderController;
    private Texture endLevelTexture;
    private OrthographicCamera camera;
    private Screen screen;

    public EndLevelScreen(Crazychef game, Controller controller, OrderController orderController, Screen screen) {
        this.game = game;
        this.controller = controller;
        this.orderController = orderController;
        if(screen instanceof ScreenLevel1){
            endLevelTexture = new Texture(Gdx.files.internal("Food/Level1/endGameLevel1.png"));
        } else if (screen instanceof  ScreenLevel2) {
            endLevelTexture = new Texture(Gdx.files.internal("Food/Level2/endGameLevel2.png"));

        }
        else{
            endLevelTexture = new Texture(Gdx.files.internal("Food/Level3/endGameLevel3.png"));
        }
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
        game.batch.draw(endLevelTexture, 0,0, 1280, 720);
        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/UVatrjTBDF_CifhmfyVHP_hy.ttf.fnt"));
        font.getData().setScale(1.8f, 1.8f);
        font.draw(game.batch, "LEVEL ENDED",450, 460);
        font.draw(game.batch, "FINAL SCORE",450, 410);
        font.draw(game.batch, String.format("%04d",orderController.getPoints()),560, 360);
        font.draw(game.batch, "PRESS ESC TO",440, 280);
        font.draw(game.batch, "RETURN TO MAIN MENU",320, 230);

        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenuScreen(game, this.controller));
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
