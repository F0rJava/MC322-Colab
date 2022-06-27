package com.controller;

import com.models.food.Burger;
import com.models.mapdesign.Kitchen;
import com.models.mapdesign.Order;

public class OrderController {
    private Order orders[];
    private float lastGeneratedOrder; //tempo em segundos do ultimo pedido gerado

    public OrderController(){
        this.orders = new Order[3];//no maximo 3 pedidos simultaneos
    }

    public Order getOrders(int pos) {
        return orders[pos];
    }

    public void setOrders(Order orders, int pos) {
        this.orders[pos] = orders;
    }

    public void generateOrders(float dt){
        //checa se tem espaço para gerar uma nova order e se passaram 20s
        lastGeneratedOrder += dt;
        for(int i=0; i<3; i++){
            if(orders[i] == null){
                if(i == 0 && lastGeneratedOrder > 3){
                    orders[i] = new Order(i*320);
                    orders[i].generateOrder();
                    lastGeneratedOrder = 0;
                }
                else if(lastGeneratedOrder > 5){
                    orders[i] = new Order(i*320);
                    orders[i].generateOrder();
                    lastGeneratedOrder = 0;
                }
            }
        }
    }

    //atualiza e checa o tempo dos pedidos, se tiver zerado de algum deles, desloca tudo para a esquerda
    public void updateOrders(float dt){
        //atualiza o vetor de pedidos e o tempo
        for(int i=0; i<3; i++){
            if(orders[i]!=null){
                orders[i].updateTime(dt);
                if(orders[i].getRemainingTime()<=6){//barra vermelha
                    orders[i].setTimeBarTexture(2);
                }
                else if(orders[i].getRemainingTime()<=orders[i].getOrderTime()/2){//barra amarela
                    orders[i].setTimeBarTexture(1);
                }
                if(orders[i].getRemainingTime()==0){
                    shiftOrders(orders, i);
                }
            }
        }
    }

    public void shiftOrders(Order[] orders, int i){
        for(int j=i; j<3; j++){
            if(j+1!=3){
                if(orders[j+1]!=null){
                    orders[j] = orders[j+1];
                    orders[j].x -= 320;
                    for(int k=0; k<4; k++){//corrige pos das comidas
                        if(orders[j].getFood(k)!=null)
                            orders[j].getFood(k).x -=320;
                    }
                    orders[j].getTimeBar().x-=320;
                }
            }
            else orders[j] = null;
        }
    }

}

