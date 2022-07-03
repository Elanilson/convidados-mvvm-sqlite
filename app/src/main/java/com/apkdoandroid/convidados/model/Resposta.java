package com.apkdoandroid.convidados.model;

public class Resposta {

    public Resposta() {
    }

    public Resposta(String mensagem) {
        this.mensagem = mensagem;
    }

    public Resposta(Boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public  boolean isSucess(){
        return  sucesso;
    }

    private Boolean sucesso = true;
    private String mensagem = "";
}
