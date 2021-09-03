package com.example.mascotas.Services;

public class clsServicios {
    private String corte;
    private String b1;
    private String b2;
    private String tam;
    private String raza;
    private Double costoParcial;
    public clsServicios(String corte, String b1, String b2, String raza, String tam,Double costoParcial) {
        this.corte = corte;
        this.b1 = b1;
        this.b2 = b2;
        this.raza = raza;
        this.tam = tam;
        this.costoParcial=costoParcial;
    }


    public String getCorte() {
        return corte;
    }

    public String getB1() {
        return b1;
    }

    public String getB2() {
        return b2;
    }

    public String getTam() {
        return tam;
    }

    public String getRaza() {
        return raza;
    }

    public Double getCostoParcial() { return costoParcial;}

}
