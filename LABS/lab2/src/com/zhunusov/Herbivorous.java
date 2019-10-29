package com.zhunusov;

//Травоядные
public class Herbivorous extends Animal {
    public Herbivorous(String name, TypeOfFood forHerbivorous, float quantity) {
        this.name = name;
        this.quantity = quantity;
        this.typeOfFood = forHerbivorous;
    }

    @Override
    public void calculate() {
        typeOfFood = TypeOfFood.forHerbivorous;
        quantity = 5;
    }
}
