package com.sarnavsky.pasz.nighlight2.Models;

import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmObject;

public class Nightlight extends RealmObject {

    private int number;
    private  int timer;

    public void setTimer(int timer) {
//        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void setNumber(int number) {

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addChangeListener(OrderedRealmCollectionChangeListener<Nightlight> orderedRealmCollectionChangeListener) {
    }
}
