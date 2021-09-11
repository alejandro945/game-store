package model;

public class Game {

    private int code;
    private String gameName;
    private String review;
    private int price;
    private int amount;

    public Game(int code, String gameName, String review, int price, int amount) {
        this.code = code;
        this.gameName = gameName;
        this.review = review;
        this.price = price;
        this.amount = amount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}