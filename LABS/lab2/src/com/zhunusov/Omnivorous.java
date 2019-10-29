package com.zhunusov;

//Всеядные
public class Omnivorous extends Animal {
    public Omnivorous(String name, TypeOfFood forOmnivorous, float quantity) {
        this.name = name;
        this.quantity = quantity;
        this.typeOfFood = forOmnivorous;
    }

    @Override
    public void calculate() {
        typeOfFood = TypeOfFood.forOmnivorous;
        quantity = 8;
    }
}
