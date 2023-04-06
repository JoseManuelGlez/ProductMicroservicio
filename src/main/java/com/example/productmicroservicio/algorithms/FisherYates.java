package com.example.productmicroservicio.algorithms;

import java.util.List;
import java.util.Random;

public class FisherYates {

    private FisherYates(){}

    public static final Random RAND = SingleRandom.getInstance();

    public static List<String> sort(List<String> list){
        for(int i = 0; i < list.size(); i++){
            int j = RAND.nextInt(i + 1);
            String temp = list.get(i);
            list.set(j, temp);
        }
        return list;
    }

}
