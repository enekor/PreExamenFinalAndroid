package com.example.preexamenfinalandroid.home.model;

public class Intercambio {
    private static Intercambio instance;
    private Intercambio(){}

    public static Intercambio getInstance(){
        if(instance==null){
            instance = new Intercambio();
        }
        return instance;
    }

    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
