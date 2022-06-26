package com.models.mapdesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;
import com.models.food.Bun;
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
        food[0] = new Burger(this.x+15,this.y+17);//sempre tem um hamburger no pedido na pos inicial
        food[0].setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/cookedBurger.png")));
        //food[1] = new Bun(Math.round(food[0].x)+6,this.y+38);//sempre tem um pao no pedido (posição corrigida depois)
    }

    public Texture getBackGround(){
        return this.backGround;
    }

    public Food getFood(int i){
        return food[i];
    }

    public Texture getFoodTexture(int i){
        return food[i].getBaseTexture();
    }

    //gera o pedido aleatorio com até 4 comidas
    public void generateOrder(){
        Random rand = new Random();
        Food aux;
        int int_rand = rand.nextInt(2);
        for(int i=1; i<4; i++){
            //gera a comida aleatoria e insere no vetor de comidas do pedido
            switch(int_rand){
                case 1:
                    this.insertSorted(this.food,new Bun(this.x, this.y+17));
                    break;
                case 2:
                    break;
            }

        }
    }

    public void insertSorted(Food[] foodVec, Food food){
        //insere seguindo a ordem da comida
        int pos = 0;
        for(int i=0; i<4; i++){
            if(foodVec[i]!=null){
                if(foodVec[i].getPrio()<food.getPrio()){
                    pos++;
                }
                else break;
            }
        }

        for(int i = 2; i>=pos; i--){
            foodVec[i+1] = foodVec[i];
        }

        foodVec[pos] = food;
        foodVec[pos].x = foodVec[pos-1].x+15;
    }
}

