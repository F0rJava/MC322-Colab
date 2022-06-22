package com.crazychef.game;

import com.controller.Controller;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Crazychef extends Game {
	//renderiza as texturas
	public SpriteBatch batch;
	//controle do jogo
	public Controller controller;

	public void create() {
		batch = new SpriteBatch();
		controller = new Controller();
		this.setScreen(new MainMenuScreen(this, controller));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
	}
}
