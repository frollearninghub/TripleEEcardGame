package com.example.webproject.model;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardSq {
    private String name;
    private Card[] cardCost;
    private int chipsIn;
    private String color ;

    public BoardSq(String name,Card [] cardCost) {
        this.chipsIn = 0;
        this.color = "black";
        this.name = name;
        this.cardCost = cardCost;

    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean hasCards(ArrayList<Card> cards ) {

        this.color = "black";

        switch(name) {
            case "heartKing":
                if (cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost)))) {
                    this.color = "green";
                    return true;
                }
                break;

            case "heartQueen":
                if (cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost)))) {
                    this.color = "green";
                    return true;
                }
                break;

            case "heartKingQueen":
                if (cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost)))) {
                    this.color = "green";
                    return true;
                }
                break;

            case "heartAce":
                if (cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost)))) {
                    this.color = "green";
                    return true;
                }
                break;

            case "heartTen":
                if (cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost)))) {
                    this.color = "green";
                    return true;
                }
                break;

            case "heartJack":
                if (cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost)))) {
                    this.color = "green";
                    return true;
                }
                break;

            case "pot":
                return false;


            case "kitty":
                return false;


            case "eightNineTenSingleSuit":

                if(cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost[0],cardCost[1],cardCost[2]))) ||
                   cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost[3],cardCost[4],cardCost[5]))) ||
                   cards.containsAll(new ArrayList<Card>(Arrays.asList(cardCost[6],cardCost[7],cardCost[8])))) {
                    this.color = "green";
                    return true;
                }
                break;


            default:
                return true;
        }
        return false;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
