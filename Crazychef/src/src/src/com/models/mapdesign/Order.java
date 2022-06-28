package com.models.mapdesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.models.Food;
import com.models.food.*;

import java.util.Random;

import java.awt.*;

public class Order extends Rectangle {
    private Texture backGround;
    private Rectangle timeBar;
    private Texture timeBarTexture;
    private Texture timeBarTextureVec[];
    private Food food[];
    private Integer orderTime;
    private Integer remainingTime;
    private Integer numOfFood;
    private float timeCount;

    public Order(int x, int numOfFood){
        this.x = x;
        this.y = 560;
        this.width = 320;
        this.height = 160;
        this.numOfFood = numOfFood;

        orderTime = 50;//tempo maximo que o pedido aparece na tela
        remainingTime = orderTime;//tempo restante do pedido

        backGround = new Texture(Gdx.files.internal("Kitchen/Order/orderBackground.png"));

        timeBar = new Rectangle();
        timeBar.width = 264;
        timeBar.height = 10;
        timeBar.x = x + 28;
        timeBar.y = y + 115;

        timeBarTextureVec = new Texture[3];
        timeBarTextureVec[0] = new Texture(Gdx.files.internal("Kitchen/Order/timeBarGreen.png"));
        timeBarTextureVec[1] = new Texture(Gdx.files.internal("Kitchen/Order/timeBarYellow.png"));
        timeBarTextureVec[2] = new Texture(Gdx.files.internal("Kitchen/Order/timeBarRed.png"));
        timeBarTexture = timeBarTextureVec[0];


        food = new Food[4];//no maximo 4 itens no pedido
        food[0] = new Burger(this.x+15, this.y+17);//tem pelo menos 1 burger (como tem a maior prioridade, começa na primeira posição)
        food[0].setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/cookedBurger.png")));
    }

    public Texture getBackGround(){
        return this.backGround;
    }

    public Food getFood(int i){
        return food[i];
    }

    public int getFoodSize(){return food.length;}
    public Integer getOrderTime(){
        return this.orderTime;
    }
    public Integer getRemainingTime(){
        return this.remainingTime;
    }

    public Rectangle getTimeBar(){
        return timeBar;
    }

    public Texture getTimeBarTexture(){
        return timeBarTexture;
    }
    public void setTimeBarTexture(int i){
        this.timeBarTexture = timeBarTextureVec[i];
    }


    //gera o pedido aleatorio com até 4 comidas (pao sempre no final
    public void generateOrder(){
        Random rand = new Random();
        Food aux;
        for(int i=1; i<3; i++){
            int int_rand = rand.nextInt(numOfFood);
            //gera a comida aleatoria e insere no vetor de comidas do pedido
            switch(int_rand){
                case 1:
                    aux = new Burger(this.x, this.y+17);
                    aux.setBaseTexture(new Texture(Gdx.files.internal("Food/Level1/cookedBurger.png")));
                    this.insertSorted(this.food, aux);
                    break;
                case 2:
                    aux = new Cheese(this.x, this.y+17);
                    this.insertSorted(this.food, aux);
                    break;
                case 3:
                    aux = new Tomato(this.x, this.y+17);
                    aux.setBaseTexture(new Texture(Gdx.files.internal("Food/Level2/tomatoCut.png")));
                    this.insertSorted(this.food, aux);
                    break;
                case 4:
                    aux = new Lettuce(this.x, this.y+17);
                    aux.setBaseTexture(new Texture(Gdx.files.internal("Food/Level2/lettuceCut.png")));
                    this.insertSorted(this.food, aux);
                    break;
            }
        }
        //posiciona o pao no final
        for(int j=0; j<4; j++){
            if(food[j] == null){
                food[j] = new Bun(Math.round(food[j-1].x + food[j-1].width-10),this.y+17);
                break;
            }
        }
    }

    public void updateTime(float dt){
        timeCount+=dt;
        if(remainingTime>0){
            if(timeCount>=1){
                remainingTime--;
                timeCount = 0;
            }
        }
    }

    public void insertSorted(Food[] foodVec, Food food){
        //insere seguindo a ordem da comida
        int pos = 0;
        for(int i=0; i<4; i++){
            if(foodVec[i]!=null){
                if(foodVec[i].getPrio()<=food.getPrio()){
                    pos++;
                }
                else break;
            }
        }

        for(int i = 2; i>pos; i--){
            foodVec[i+1] = foodVec[i];
        }

        foodVec[pos] = food;
        foodVec[pos].x = foodVec[pos-1].x + foodVec[pos-1].width-10;
    }
}

