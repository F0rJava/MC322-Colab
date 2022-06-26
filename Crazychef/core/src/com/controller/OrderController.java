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
                else if(lastGeneratedOrder > 20){
                    orders[i] = new Order(i*320);
                    orders[i].generateOrder();
                    lastGeneratedOrder = 0;
                }
            }
        }
    }
    /*public void updateOrders(float dt){

    }*/

}

