package com.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.crazychef.game.MainMenuScreen;
import com.crazychef.game.ScreenLevel1;
import com.models.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.ObjectMap;
import javax.swing.text.View;

//classe responsavel por controlar as views do jogo e os models
public class Controller{
    private Chef chef;


    //movimenta o chef, checando se não há colisões com outros objetos
    public void up(){
        chef.y += 60;
    }
    public void down(){
        chef.y -= 60;
    }
    public void left(){
        chef.x -= 60;
    }
    public void right(){
        chef.x += 60;
    }
    public void setChef(Chef chef){
        this.chef = chef;
    }
}
