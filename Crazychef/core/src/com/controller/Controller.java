package com.controller;

import com.models.*;
import com.models.mapdesign.Kitchen;

//classe responsavel por controlar as views do jogo e os models
public class Controller{
    private Chef chef;
    private Kitchen kitchen;


    //movimenta o chef, checando se não há colisões com outros objetos
    public void up(){
        chef.setOrientation(1);
        if(Math.round(chef.y/80)+1 < 9 && kitchen.getFloor(Math.round(chef.y/80)+1, Math.round(chef.x/80)).dontHaveObjects()){
            kitchen.getFloor(Math.round(chef.y/80), Math.round(chef.x/80)).removeObjects(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y/80)+1, Math.round(chef.x/80)).addObjects(chef); //adiciona o chef na nova posição
            chef.y += 80; //atualiza o atributo y do chef para a nova posição
        }
    }
    public void down(){
        chef.setOrientation(0);
        if(Math.round(chef.y/80)-1 >= 0 && kitchen.getFloor(Math.round(chef.y/80)-1, Math.round(chef.x/80)).dontHaveObjects()) {
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeObjects(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y / 80) - 1, Math.round(chef.x / 80)).addObjects(chef); //adiciona o chef na nova posição
            chef.y -= 80; //atualiza o atributo y do chef para a nova posição
        }
    }
    public void left(){
        chef.setOrientation(2);
        if(Math.round(chef.x/80)-1 >= 0 && kitchen.getFloor(Math.round(chef.y/80), Math.round(chef.x/80)-1).dontHaveObjects()) {
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeObjects(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)-1).addObjects(chef); //adiciona o chef na nova posição
            chef.x -= 80; //atualiza o atributo x do chef para a nova posição
        }
    }
    public void right(){
        chef.setOrientation(3);
        if(Math.round(chef.x/80)+1 < 16 && kitchen.getFloor(Math.round(chef.y/80), Math.round(chef.x/80)+1).dontHaveObjects()) {
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)).removeObjects(chef); //remove o chefe da posição anterior
            kitchen.getFloor(Math.round(chef.y / 80), Math.round(chef.x / 80)+1).addObjects(chef); //adiciona o chef na nova posição
            chef.x += 80; //atualiza o atributo x do chef para a nova posição
        }
    }
    public void setChef(Chef chef){
        this.chef = chef;
    }

    public void setKitchen(Kitchen cozinha){
        this.kitchen = cozinha;
    }
}
