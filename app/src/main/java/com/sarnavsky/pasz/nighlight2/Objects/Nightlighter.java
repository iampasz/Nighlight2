package com.sarnavsky.pasz.nighlight2.Objects;

import java.io.Serializable;

public class Nightlighter implements Serializable {

    int upImg;
    int downImg;
    int name;

    public Nightlighter(int upImg, int downImg, int name){
        this.upImg = upImg;
        this.downImg = downImg;
        this.name = name;

    }

    public int getName() {
        return name;
    }

    public int getDownImg() {
        return downImg;
    }

    public int getUpImg() {
        return upImg;
    }
}
