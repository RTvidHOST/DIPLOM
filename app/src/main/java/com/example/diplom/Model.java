package com.example.diplom;

public class Model {
    private int id;
    private byte[]proavatar;
    private String username, price, firm, model, type;


    public Model(int id, byte[] proavatar, String username, String price, String firm, String model, String type) {
        this.id = id;
        this.proavatar = proavatar;
        this.username = username;
        this.price = price;
        this.firm = firm;
        this.model = model;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getProavatar() {
        return proavatar;
    }

    public void setProavatar(byte[] proavatar) {
        this.proavatar = proavatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
