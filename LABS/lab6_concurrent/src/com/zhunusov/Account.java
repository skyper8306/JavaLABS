package com.zhunusov;

import java.util.concurrent.atomic.AtomicInteger;

class Account {
    private int id;
    private AtomicInteger amount = new AtomicInteger(0);

    Account(int id, int amount) {
        this.id = id;
        this.amount = new AtomicInteger(amount);
    }

    void changeAmount(int difference) {
        this.amount.addAndGet(difference);
    }

    int getID() {
        return id;
    }
}
