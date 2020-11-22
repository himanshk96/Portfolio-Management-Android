package com.example.portman;

public class ItemRow {

String stockName;
String quantity;
float currPrice;
float change;

    public String getStockName() {
        return stockName;
    }

    public String getQuantity() {
        return quantity;
    }

    public float getCurrPrice() {
        return currPrice;
    }

    public float getChange() {
        return change;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setCurrPrice(float currPrice) {
        this.currPrice = currPrice;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public ItemRow(String stockName, String quantity, float currPrice, float change) {
        this.stockName = stockName;
        this.quantity = quantity;
        this.currPrice = currPrice;
        this.change = change;
    }

}
