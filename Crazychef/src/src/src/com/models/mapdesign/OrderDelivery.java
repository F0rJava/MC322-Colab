package com.models.mapdesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.models.Actors;
import com.models.Food;

public class OrderDelivery extends Actors {
    private Texture texture;
    private Floor floor;
    private Music deliverySound;

    public OrderDelivery(int x, int y, Texture texture, Floor floor){
        super(x, y);
        this.texture = texture;
        this.floor = floor;
        this.width = 80;
        this.height = 80;
        deliverySound = Gdx.audio.newMusic(Gdx.files.internal("Sounds/DeliverySound.mp3"));
        deliverySound.setVolume(0.25f);
    }
    public Texture getTexture(){
        return texture;
    }

    public boolean checkOrder(Order order){
        int aux = floor.getActors().size()-2;
        for(int i=2; i<floor.getActors().size(); i++){
            if(i-2 < 4) {
                if (order.getFood(i - 2) != null) {
                    if (floor.getActors().get(i) instanceof Food) {
                        if (floor.getActors().get(i).getClass() == order.getFood(i - 2).getClass()) {
                            aux--;
                        }
                    }
                }
            }
            if(aux==0){
                for(int j = 0; j < floor.getActors().size(); j++){
                    if(floor.getActors().get(j) instanceof Food){
                        floor.removeActors(floor.getActors().get(j));
                        j--;
                    }
                }
                deliverySound.play();
                return true;
            }
        }
        return false;
    }
}
