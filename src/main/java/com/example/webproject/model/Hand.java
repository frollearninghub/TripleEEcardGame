package com.example.webproject.model;

import com.example.webproject.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Hand {
    Card[] hand = new Card[5];
    String pokerHandName;
    int pokerHandValue;

    public Card[] getHand() {
        return hand;
    }

    public void setPokerHand(){
        if (Arrays.asList(this.getHand()).containsAll(Arrays.asList(new Card(Suit.CLUB,1), new Card(Suit.DIAMOND,13),
                                                                    new Card(Suit.DIAMOND,12), new Card(Suit.DIAMOND,11),
                                                                    new Card(Suit.DIAMOND,10))) ||
             Arrays.asList(this.getHand()).containsAll(Arrays.asList(new Card(Suit.CLUB,1), new Card(Suit.HEART,13),
                                                                     new Card(Suit.HEART,12), new Card(Suit.HEART,11),
                                                                     new Card(Suit.HEART,10))) ||
             Arrays.asList(this.getHand()).containsAll(Arrays.asList(new Card(Suit.CLUB,1), new Card(Suit.CLUB,13),
                                                                     new Card(Suit.CLUB,12), new Card(Suit.CLUB,11),
                                                                     new Card(Suit.CLUB,10))) ||
             Arrays.asList(this.getHand()).containsAll(Arrays.asList(new Card(Suit.SPADE,1), new Card(Suit.SPADE,13),
                                                                     new Card(Suit.SPADE,12), new Card(Suit.SPADE,11),
                                                                     new Card(Suit.SPADE,10)))) {

                this.pokerHandName= "RoyalFlush";
                this.pokerHandValue= 10;

        }  else {
            boolean isFlush = true;
            boolean isStraight = true;
            Suit firstSuit = null;
            int minCard = 0;
            int[] numberCount = new int[13];
            boolean isFour = false;
            boolean isThree = false;
            boolean isPair = false;
            for (Card card :Arrays.asList(this.getHand())) {

                if (firstSuit == null) {
                     firstSuit = card.getSuit();
                } else {
                  if (!firstSuit.equals(card.getSuit()))   {
                      isFlush = false;
                  }
                }

                if (minCard == 0) {
                    for (Card cardMinCheck :Arrays.asList(this.getHand())) {
                        if (minCard == 0 || minCard > cardMinCheck.getNumber()) {
                             minCard = cardMinCheck.getNumber();
                        }
                    }
                }

                if (minCard == 1) {

                    if ((card.getNumber() != minCard ||
                            card.getNumber() != minCard + 12 ||
                            card.getNumber() != minCard + 11 ||
                            card.getNumber() != minCard + 10 ||
                            card.getNumber() != minCard + 9) && isStraight) {
                        isStraight = false;
                    }
                } else {
                    if ((card.getNumber() != minCard ||
                         card.getNumber() != minCard + 1 ||
                         card.getNumber() != minCard + 2 ||
                         card.getNumber() != minCard + 3 ||
                         card.getNumber() != minCard + 4) && isStraight) {
                            isStraight = false;
                    }
                }



                numberCount[card.getNumber() - 1] =  ++numberCount[card.getNumber() - 1];

                if (numberCount[card.getNumber() - 1] == 4) {
                    isFour= true;
                }

                if (numberCount[card.getNumber() - 1] == 3) {
                    isThree = true;
                }
                if (numberCount[card.getNumber() - 1] == 2) {
                    isPair = true;
                }
            }

            if (isFlush && isStraight) {
                this.pokerHandName= "StraightFlush";
                this.pokerHandValue= 9;
            } else if (isFour) {
                this.pokerHandName= "FourOfAKind";
                this.pokerHandValue= 8;
            } else if (isThree && isPair) {
                this.pokerHandName= "FullHouse";
                this.pokerHandValue= 7;
            } else if (isFlush) {
                this.pokerHandName= "Flush";
                this.pokerHandValue= 6;
            } else if (isStraight) {
                this.pokerHandName= "Straight";
                this.pokerHandValue= 5;
            } else if (isThree) {
                this.pokerHandName= "ThreeOfKind";
                this.pokerHandValue= 4;
            } else {
                if (isPair) {
                    int pairCount = 0;
                    for (int num: numberCount) {
                        if(num == 2) {
                            pairCount++;
                        }
                    }
                    if (pairCount ==2) {
                        this.pokerHandName= "TwoPairs";
                        this.pokerHandValue= 3;
                    } else {
                        this.pokerHandName= "pair";
                        this.pokerHandValue= 2;
                    }
                } else {
                    this.pokerHandName= "HighCard";
                    this.pokerHandValue= 1;
                }
            }
        }
    }



    public void addCard(int index, Card card) {
        if (index <= 5) {


            if (!checkCardExists(card)) {
                hand[index] = card;
            }

        }
    }

    public boolean checkCardExists(Card card) {
        boolean cardExists= false;
        for (Card handCard : hand) {
            if (handCard != null) {
                if (handCard.getSuit() == card.getSuit() &&
                        handCard.getNumber() == card.getNumber()) {
                    cardExists = true;
                }
            }
        }
        return cardExists;
    }


    public String getPokerHandName() {
        return pokerHandName;
    }

    public void setPokerHandName(String pokerHandName) {
        this.pokerHandName = pokerHandName;
    }

    public int getPokerHandValue() {
        return pokerHandValue;
    }

    public void setPokerHandValue(int pokerHandValue) {
        this.pokerHandValue = pokerHandValue;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
        this.setPokerHand();
    }

}
