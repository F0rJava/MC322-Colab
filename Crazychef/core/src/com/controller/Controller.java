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
    private Kitchen kitchen;


    //movimenta o chef, checando se não há colisões com outros objetos
    public void up(Objects[][] kitchen){
        if(kitchen[Math.round(chef.y/80)+1][Math.round(chef.x/80)]==null && Math.round(chef.y/80)+1<9){
            kitchen[Math.round(chef.y/80)][Math.round(chef.x/80)] = null; //remove o chefe da posição anterior
            kitchen[Math.round(chef.y/80)][Math.round(chef.x/80)] = chef;//adiciona o chef na nova posição
            chef.y += 80;
        }

    }
    public void down(Objects[][] kitchen){
        chef.y -= 80;
    }
    public void left(Objects[][] kitchen){
        chef.x -= 80;
    }
    public void right(Objects[][] kitchen){
        chef.x += 80;
    }
    public void setChef(Chef chef){
        this.chef = chef;
    }
}
