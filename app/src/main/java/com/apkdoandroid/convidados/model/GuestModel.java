package com.apkdoandroid.convidados.model;

public class GuestModel {
    private int id;
    private String nome;
    private  int confirmation;

    public GuestModel() {
    }

    public GuestModel(String nome, int confirmation) {
        this.nome = nome;
        this.confirmation = confirmation;
    }

    public GuestModel(int id, String nome, int confirmation) {
        this.id = id;
        this.nome = nome;
        this.confirmation = confirmation;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
