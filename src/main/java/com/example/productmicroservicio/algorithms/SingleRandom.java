package com.example.productmicroservicio.algorithms;

import java.util.Random;

public class SingleRandom {

    private SingleRandom(){}

    public static final Random RAND = new Random();

    public static Random getInstance(){
        return RAND;
    }

}
