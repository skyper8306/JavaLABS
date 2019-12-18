package com.zhunusov;

class Transaction {
    static boolean operation(Account from, Account to, int diff){
        if(diff > 0) { //сумма перевода > 0
            from.changeAmount(-diff);
            to.changeAmount(diff); // положить на счет To
            return true;
        } else { // сумма перевода должна быть положительной
            return false;
        }
    }
}
