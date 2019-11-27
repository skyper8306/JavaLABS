package com.zhunusov;

class Transaction {
    synchronized static boolean operation(Account from, Account to, float diff){
        if(diff > 0) { //сумма перевода > 0
            if(!from.changeAmount(-diff)){ //попытка перевести от From, если денег нет, то false
                return false;
            }
            to.changeAmount(diff); // положить на счет To
            return true;
        } else { // сумма перевода должна быть положительной
            return false;
        }
    }
}
