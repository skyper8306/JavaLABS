package com.zhunusov;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkWithAnimal {
    ArrayList<Animal> animals = new ArrayList<Animal>();

    public void print(){
        System.out.println("Animals[]:");
        for(Animal e: animals){
            System.out.println(e.toString());
        }
    }

    public static void sort(ArrayList<Animal> arr){
        Animal tmp;
        for(int i = arr.size()-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if(arr.get(j).quantity < arr.get(j+1).quantity) {
                    tmp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, tmp);
                }else if(arr.get(j).quantity == arr.get(j+1).quantity &&
                        String.CASE_INSENSITIVE_ORDER.compare(arr.get(j).name, arr.get(j+1).name)>0){
                    tmp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, tmp);
                }
            }
        }
    }

    public static ArrayList<Animal> FromFileToList(String fileName) throws IOException {
        ArrayList<Animal> arr = new ArrayList<Animal>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String sCurrentLine;
            String[] subStr;
            Animal sCurrentAnimal;

            while ((sCurrentLine = br.readLine()) != null) {
                subStr = sCurrentLine.split(" ");

                if(subStr.length!=3) throw new IllegalArgumentException();

                sCurrentAnimal=new Animal(subStr[0], TypeOfFood.valueOf(subStr[1]), Float.parseFloat(subStr[2])) {
                    @Override void calculate(){}
                };
                arr.add(sCurrentAnimal);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный формат входного файла!");
        } catch (IOException e) {
            System.out.println("Такого файла не существует!");
        }

        return arr;
    }

    public static void FromListToFile(ArrayList<Animal> animals, String fileName) throws FileNotFoundException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            for(Animal e: animals){
                bw.write(e.name+" "+e.typeOfFood+" "+e.quantity+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
