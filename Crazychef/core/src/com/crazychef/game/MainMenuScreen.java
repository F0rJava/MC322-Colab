package com.crazychef.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.controller.Controller;

//classe do Menu Principal, que seleciona as fases
public class MainMenuScreen implements Screen{
    private final Crazychef game;
    private Controller controller;
    private Texture mainMenuBackground;
    private Texture gameTitle;
    private OrthographicCamera camera;

    private Stage stage;
    private ImageButton Level1, Level2, Level3;
    public MainMenuScreen(Crazychef game, Controller controller) {
        this.game = game;
        this.controller = controller;
        mainMenuBackground = new Texture(Gdx.files.internal("Kitchen/MainMenuBackground.png"));
        gameTitle = new Texture(Gdx.files.internal("gameTitle.png"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        Level1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Level1Button.png")))));
        Level1.setPosition(50, 175);
        Level1.setSize(400, 197);

        Level2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Level2Button.png")))));
        Level2.setPosition(450, 175);
        Level2.setSize(400, 197);

        Level3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Level2Button.png")))));
        Level3.setPosition(850, 175);
        Level3.setSize(400, 197);

        stage =  new Stage(new ScreenViewport());
        stage.addActor(Level1);
        stage.addActor(Level2);
        stage.addActor(Level3);
        Gdx.input.setInputProcessor(stage);
    }



    @Override
    public void render(float delta){

        stage.act(Gdx.graphics.getDeltaTime());

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin(); //inicia a renderização
        game.batch.draw(mainMenuBackground, 0,0, 1280, 720);//imagem do fundo
        game.batch.draw(gameTitle, 300, 300, 700,420);//imagem do titulo
        game.batch.end();//finaliza a renderização
        stage.draw();

        if(Level1.isPressed()){
            game.setScreen(new ScreenLevel1(game, this.controller));
            dispose();
        } else if (Level2.isPressed()) {
            game.setScreen(new ScreenLevel2(game, this.controller));
            dispose();
        } else if (Level3.isPressed()) {
            game.setScreen(new ScreenLevel3(game, this.controller));
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
