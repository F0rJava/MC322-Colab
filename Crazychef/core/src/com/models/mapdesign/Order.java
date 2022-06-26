package com.models.mapdesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;
import com.models.food.Burger;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.Random;

import java.awt.*;

public class Order extends Rectangle {
    private Texture backGround;
    private Rectangle progressionBar;
    private Texture progressionBarTexture[];
    private Food food[];
    private Integer orderTime;
    private Integer remainingTime;

    public Order(int x){
        this.x = x;
        this.y = 560;
        this.width = 320;
        this.height = 160;

        orderTime = 30;
        remainingTime = 30;

        backGround = new Texture(Gdx.files.internal("Kitchen/Order/orderBackground.png"));

        progressionBar = new Rectangle();
        progressionBar.width = 264;
        progressionBar.height = 10;
        progressionBar.x = this.x + 28;
        progressionBar.y = this.y + 115;

        progressionBarTexture = new Texture[3];
        progressionBarTexture[0] = new Texture(Gdx.files.internal("Kitchen/Order/progressionBarGreen.png"));
        progressionBarTexture[1] = new Texture(Gdx.files.internal("Kitchen/Order/progressionBarYellow.png"));
        progressionBarTexture[2] = new Texture(Gdx.files.internal("Kitchen/Order/progressionBarred.png"));

        food = new Food[4];//no maximo 4 itens no pedido
        food[0] = new Burger(this.x+9,this.y+22);//sempre tem um hamburger no pedido na pos inicial
    }

    public Texture getBackGround(){
        return this.backGround;
    }

    //gera o pedido aleatorio com até 4 comidas
    public void generateOrder(){
        Random rand = new Random();
        int int_rand = rand.nextInt(4);
        for(int i=1; i<4; i++){
            //gera a comida aleatoria
            switch(int_rand){
                case 1:
                    this.food[i] = new Burger();
                    break;
                case 2:
                    //food = "bun";
                    break;
            }

        }
    }

    public Food[] insertSorted(Food food){
        return this.food;
    }
}

