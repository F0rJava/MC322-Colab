package com.crazychef.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.controller.Controller;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Crazychef extends Game {
	//renderiza as texturas
	public SpriteBatch batch;
	//renderiza texto na tela
	public BitmapFont font;
	//controle do jogo
	public Controller controller;
	private Music musicalTheme;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		controller = new Controller();
		this.setScreen(new MainMenuScreen(this, controller));

		musicalTheme = Gdx.audio.newMusic(Gdx.files.internal("Sounds/musicalTheme.mp3"));
		musicalTheme.setVolume(0.025f);
		musicalTheme.setLooping(true);
		musicalTheme.play();
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
	}
}
