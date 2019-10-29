package com.zhunusov;

//Хищные животные
public class Predator extends Animal {
    public Predator(String name, TypeOfFood forPredator, float quantity) {
        this.name = name;
        this.quantity = quantity;
        this.typeOfFood = forPredator;
    }

    @Override
    public void calculate() {
        typeOfFood = TypeOfFood.forPredator;
        quantity = 10;
    }
}
