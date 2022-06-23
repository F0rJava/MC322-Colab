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
    private Kitchen cozinha;


    //movimenta o chef, checando se não há colisões com outros objetos
    public void up(){

        if(Math.round(chef.y/80)+1 < 9 && cozinha.getObjects(Math.round(chef.y/80)+1, Math.round(chef.x/80))==null){
            cozinha.setObjects(null, Math.round(chef.y/80), Math.round(chef.x/80)); //remove o chefe da posição anterior
            cozinha.setObjects(chef, Math.round(chef.y/80)+1, Math.round(chef.x/80)); //adiciona o chef na nova posição
            chef.y += 80; //atualiza o atributo y do chef para a nova posição
        }

    }
    public void down(){

        if(Math.round(chef.y/80)-1 >= 0 && cozinha.getObjects(Math.round(chef.y/80)-1, Math.round(chef.x/80))==null) {
            cozinha.setObjects(null, Math.round(chef.y / 80), Math.round(chef.x / 80)); //remove o chefe da posição anterior
            cozinha.setObjects(chef, Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)); //adiciona o chef na nova posição
            chef.y -= 80; //atualiza o atributo y do chef para a nova posição
        }
    }
    public void left(){

        if(Math.round(chef.x/80)-1 >= 0 && cozinha.getObjects(Math.round(chef.y/80), Math.round(chef.x/80)-1)==null) {
            cozinha.setObjects(null, Math.round(chef.y / 80), Math.round(chef.x / 80)); //remove o chefe da posição anterior
            cozinha.setObjects(chef, Math.round(chef.y / 80), Math.round(chef.x / 80)-1); //adiciona o chef na nova posição
            chef.x -= 80; //atualiza o atributo y do chef para a nova posição
        }
    }
    public void right(){
        if(Math.round(chef.x/80)+1 < 16 && cozinha.getObjects(Math.round(chef.y/80), Math.round(chef.x/80)+1) ==null) {
            cozinha.setObjects(null, Math.round(chef.y / 80), Math.round(chef.x / 80)); //remove o chefe da posição anterior
            cozinha.setObjects(chef, Math.round(chef.y / 80), Math.round(chef.x / 80)+1); //adiciona o chef na nova posição
            chef.x += 80; //atualiza o atributo y do chef para a nova posição
        }
    }
    public void setChef(Chef chef){
        this.chef = chef;
    }

    public void setKitchen(Kitchen cozinha){
        this.cozinha = cozinha;
    }
}
