package com.zhunusov;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UniversalPropReader {

    private Properties prop;

    public UniversalPropReader(String path){
        prop = new Properties();
        try {
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }

    public String getProperty(String key){
        return prop.getProperty(key);
    }

    public void print(){
        for(String key : prop.stringPropertyNames()) {
            String value = prop.getProperty(key);
            System.out.println(key + " => " + value);
        }
    }
}
