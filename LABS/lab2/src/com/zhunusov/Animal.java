package com.zhunusov;

enum TypeOfFood {
    forAnimal, forPredator, forOmnivorous, forHerbivorous;
}

public abstract class Animal {
    private static short ID = 0;
    protected short id;
    protected String name;
    protected float quantity;
    protected TypeOfFood typeOfFood;

    Animal(){
        ID++;
        id=ID;
        name = "";
        typeOfFood = TypeOfFood.forAnimal;
        quantity = 0;
    }

    Animal(String name, TypeOfFood food, float quantity) {
        ID++;
        id=ID;
        this.name = name;
        this.typeOfFood = food;
        this.quantity = quantity;
    }

    abstract void calculate();
    @Override
    public String toString(){
        return "id = " + id + "\t name = " + name + "\t type_of_food = " + typeOfFood + "\t quantity = " + quantity;
    }
}
