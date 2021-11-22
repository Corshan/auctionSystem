package controllers;

import main.Driver;

public class mainViewController {

    public void save(){
        try{
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void load(){
        try {
            Driver.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clear(){
        Driver.auctionAPI.clear();
    }
}
