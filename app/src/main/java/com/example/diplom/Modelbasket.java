package com.example.diplom;

public class Modelbasket {
    private int id;
    private String nameBasket, priceBasket;


    public Modelbasket(int id, String nameBasket, String priceBasket) {
        this.id = id;
        this.nameBasket = nameBasket;
        this.priceBasket = priceBasket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBasket() {
        return nameBasket;
    }

    public void setNameBasket(String nameBasket) {
        this.nameBasket = nameBasket;
    }

    public String getPriceBasket() {
        return priceBasket;
    }

    public void setPriceBasket(String priceBasket) {
        this.priceBasket = priceBasket;
    }
}
