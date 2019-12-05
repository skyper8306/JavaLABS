package com.zhunusov;

import java.util.concurrent.locks.ReentrantLock;

class Account {
    private int id;
    private float amount;
    private ReentrantLock lock;  // блокировка

    Account(int id, float amount){
        this.id = id;
        this.amount = amount;
        lock = new ReentrantLock();
    }

    boolean changeAmount(float difference){
        lock.lock();
        try {
            if(amount+difference >= 0) {
                amount += difference;
                return  true;
            } else {
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    int getID(){
        return id;
    }

    float getAmount(){
        return amount;
    }
}
