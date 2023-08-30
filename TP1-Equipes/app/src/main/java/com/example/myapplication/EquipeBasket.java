package com.example.myapplication;

import android.widget.ImageView;


public class EquipeBasket {
    int imageResourceId;
    //         set image ressource
    //mieux de separer dan sune autre classe
    String arenaInput;
    String coachName;

    public EquipeBasket(int imageResourceId, String arenaName, String coachName) {
        this.imageResourceId =  imageResourceId;
        this.arenaInput = arenaName;
        this.coachName = coachName;
    }



    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getArenaInput() {
        return arenaInput;
    }

    public void setArenaInput(String arenaInput) {
        this.arenaInput = arenaInput;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
}
