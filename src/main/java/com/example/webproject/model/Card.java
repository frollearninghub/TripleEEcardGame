package com.example.webproject.model;

import com.example.webproject.Suit;

public class Card {

    Suit suit;
    int number;
    String cardImg;
    boolean selected = false;

    public Card(Suit suit, int number) {
        this.suit = suit;
        this.number = number;
        this.cardImg ="/img/card/" + this.suit.toString().toLowerCase() + "/" + this.number + ".png";
    }

    public String getCardImg() {
        return cardImg;
    }


    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }


    public int getNumber() {
        return number;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object card) {

        if (card instanceof  Card) {
            if (((Card)card).getNumber() == this.getNumber() && ((Card)card).getSuit() == this.getSuit()) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

   public String toString() {
        return getNumber() + " of " + getSuit().toString();
   }









}
