package com.example.mascotas.Services;

public class RazaItem {
    private String queraza;
    private String queimg;
    private Double costoraza;

    public RazaItem(String queraza,String queimg,Double costoraza){
        this.queraza=queraza;
        this.queimg=queimg;
        this.costoraza=costoraza;
    }

    public String getQueraza() {
        return queraza;
    }

    public String getQueimg() {
        return queimg;
    }


    public Double getCostoraza() {
        return costoraza;
    }

}
