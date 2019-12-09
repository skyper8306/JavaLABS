package com.zhunusov;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length!=2){
            System.exit(-1);
        }
        WorkWithAnimal wwa = new WorkWithAnimal();
        wwa.animals.add(new Herbivorous("Harbivorous1",TypeOfFood.forHerbivorous, 5));
        wwa.animals.add(new Herbivorous("Herbivorous1",TypeOfFood.forHerbivorous,5));
        wwa.animals.add(new Omnivorous("Omnivorous1",TypeOfFood.forOmnivorous, 8));
        wwa.animals.add(new Predator("Psedator1",TypeOfFood.forPredator, 10));
        wwa.animals.add(new Predator("Aaadator1",TypeOfFood.forPredator, 10));
        wwa.animals.add(new Predator("Predator1",TypeOfFood.forPredator, 10));

        WorkWithAnimal.sort(wwa.animals);

        wwa.print();

        if(wwa.animals.size()>=5){
            System.out.println("\nПервые 5 имен из сортированного списка");
            for(int i=0;i<5;i++){
                System.out.println(wwa.animals.get(i).name);
            }
            System.out.println("\nПоследние 3 идентификатора из списка");
            for(int i = wwa.animals.size()-1; i>wwa.animals.size()-4;i--){
                System.out.println(wwa.animals.get(i).id);
            }
        }

        wwa.animals = WorkWithAnimal.FromFileToList(args[0]);
        wwa.print();

        WorkWithAnimal.FromListToFile(wwa.animals, args[1]);
    }
}
