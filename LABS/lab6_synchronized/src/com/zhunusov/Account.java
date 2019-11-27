package com.zhunusov;

class Account {
    private int id;
    private float amount;

    Account(int id, float amount){
        this.id = id;
        this.amount = amount;
    }

    synchronized boolean changeAmount(float difference){
        if(amount+difference >= 0) {
            amount += difference;
            return  true;
        } else {
            return false;
        }
    }

    int getID(){
        return id;
    }

    float getAmount(){
        return amount;
    }
}