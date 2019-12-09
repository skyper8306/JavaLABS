package com.zhunusov;

import java.awt.*;
import java.awt.font.OpenType;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("C:/");
        Scanner sc = new Scanner(System.in);
        int num;
        File[] files;
        StringBuilder c = new StringBuilder();
        String[] tmp = new String[3];

        System.out.println("O-open,C-create,D-delete");
        do{
            files = file.listFiles();
            for(int i = 0; i < files.length; i++){
                System.out.println((i+1)+" "+files[i]);
            }

            if(sc.hasNextInt()) {
                num = sc.nextInt();
                if(num==0) {
                    c.delete(0,c.length());
                    c.append(file);
                    if(c.lastIndexOf("\\")!=-1) {
                        c.delete(c.lastIndexOf("\\"), c.length());
                        if(c.toString().endsWith(":")) c.append("\\");
                        file = new File(c.toString());
                    }
                    continue;
                }
                if(num > 0 && num <= files.length){
                    if(files[num-1].isDirectory()){
                        file = file.listFiles()[num-1];
                    } else {
                        if (files[num - 1].canRead()) {
                            Desktop.getDesktop().open(new File(file.listFiles()[num - 1].getPath().toString()));
                        }
                    }
                }
            }
            else if(sc.hasNext("[COD]*")) {
                try {
                    tmp[0] = sc.next();
                    tmp = tmp[0].split(" ");
                    switch (tmp[0]) {
                        case "C":
                            if (tmp[1].equals("-a")) {

                            }
                            break;
                        case "D":
                            break;
                        case "O":
                            break;
                        default:
                            //throw new IllegalStateException("Unexpected value: " + tmp[0]);
                    }
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + c);
                } finally {
                    
                }
            }
            else {
                c.insert(0,sc.next());
            }
        } while(!c.toString().toLowerCase().equals("exit"));
    }
}
