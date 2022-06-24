package com.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.models.*;
import com.models.food.Plate;
import com.models.mapdesign.Kitchen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import jdk.tools.jlink.internal.Platform;

//classe responsavel por controlar as views do jogo e os models
public class Controller{
    private Chef chef;
    private Kitchen kitchen;
    private float timeCount;
    private Integer worldTimer;

    private Label timeLabel;
    private Label countdownLabel;

    public Controller(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    }


    //movimenta o chef, checando se não há colisões com outros objetos
    public void up(){
        chef.setOrientation(1);
        chef.updateActorsCoordinates();
        if(Math.round(chef.y/80)+1 < 9 && kitchen.getFloor(Math.round(chef.y/80)+1, Math.round(chef.x/80)).dontHaveActors()){
            kitchen.getFloor(Math.round(chef.y/80), Math.round(chef.x/80)).removeActors(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y/80)+1, Math.round(chef.x/80)).addActors(chef); //adiciona o chef na nova posição
            chef.y += 80; //atualiza o atributo y do chef para a nova posição
        }
    }
    public void down(){
        chef.setOrientation(0);
        chef.updateActorsCoordinates();
        if(Math.round(chef.y/80)-1 >= 0 && kitchen.getFloor(Math.round(chef.y/80)-1, Math.round(chef.x/80)).dontHaveActors()) {
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeActors(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).addActors(chef); //adiciona o chef na nova posição
            chef.y -= 80; //atualiza o atributo y do chef para a nova posição
        }
    }
    public void left(){
        chef.setOrientation(2);
        chef.updateActorsCoordinates();
        if(Math.round(chef.x/80)-1 >= 0 && kitchen.getFloor(Math.round(chef.y/80), Math.round(chef.x/80)-1).dontHaveActors()) {
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeActors(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)-1).addActors(chef); //adiciona o chef na nova posição
            chef.x -= 80; //atualiza o atributo x do chef para a nova posição
        }
    }
    public void right(){
        chef.setOrientation(3);
        chef.updateActorsCoordinates();
        if(Math.round(chef.x/80)+1 < 16 && kitchen.getFloor(Math.round(chef.y/80), Math.round(chef.x/80)+1).dontHaveActors()) {
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeActors(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)+1).addActors(chef); //adiciona o chef na nova posição
            chef.x += 80; //atualiza o atributo x do chef para a nova posição
        }
    }

    public void pickup(){
        Food aux1 = null;
        if(chef.getOrientation(0)){
            if(Math.round(chef.y/80)-1 >= 0) {
                aux1 = (Food) kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).getFood();

                if(chef.hold(aux1))
                    kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).removeActors(aux1);
            }
        }
        else if (chef.getOrientation(1)){
            if(Math.round(chef.y/80)+1 < 9) {
                aux1 = (Food) kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round((chef.x / 80))).getFood();
                if(chef.hold(aux1))
                    kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)).removeActors(aux1);
            }
        }
        else if (chef.getOrientation(2)) {
            if(Math.round(chef.x/80)-1 >= 0) {
                aux1 = (Food) kitchen.getFloor(Math.round(chef.y / 80), Math.round((chef.x / 80)) - 1).getFood();
                if(chef.hold(aux1))
                    kitchen.getFloor(Math.round(chef.y / 80) , Math.round(chef.x / 80) - 1).removeActors(aux1);
            }
        }
        else {
            if (Math.round(chef.x / 80) + 1 < 16) {
                aux1 = (Food) kitchen.getFloor(Math.round(chef.y / 80), Math.round((chef.x / 80)) + 1).getFood();
                if(chef.hold(aux1))
                    kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1).removeActors(aux1);
            }
        }
        chef.updateActorsCoordinates();
    }

    public void release(){
        if(chef.getOrientation(0)){
            if(Math.round(chef.y/80)-1 >= 0) {
                if(kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).getFood() == null || kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).getFood() instanceof Plate)
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)));
            }
        }
        else if (chef.getOrientation(1)){
            if(Math.round(chef.y/80)+1 < 9) {
                if(kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)).getFood() == null || kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)).getFood() instanceof Plate)
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80) + 1, Math.round(chef.x / 80)));
            }
        }
        else if (chef.getOrientation(2)) {
            if(Math.round(chef.x/80)-1 >= 0) {
                if(kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1).getFood() == null || kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1).getFood() instanceof Plate)
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) - 1));
            }
        }
        else {
            if (Math.round(chef.x / 80) + 1 < 16) {
                if(kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1).getFood() == null || kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1).getFood() instanceof Plate)
                    chef.release(kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80) + 1));
            }
        }
    }
    public void setChef(Chef chef){
        this.chef = chef;
    }

    public void setKitchen(Kitchen cozinha){
        this.kitchen = cozinha;
    }

    public void setTimer() {

    }
}
