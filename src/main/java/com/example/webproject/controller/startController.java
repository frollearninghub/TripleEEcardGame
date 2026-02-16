package com.example.webproject.controller;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.example.webproject.Suit;
import com.example.webproject.model.BoardSq;
import com.example.webproject.model.Card;
import com.example.webproject.model.Hand;
import com.example.webproject.model.MathStuff;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.ArrayUtils;
import tools.jackson.databind.util.JSONPObject;
import org.springframework.http.ResponseEntity;

import java.util.*;

@Controller
public class startController {
    Random random = new Random();
    Card[] deck = new Card[52];
    List<Card> discardDeck = new ArrayList<Card>(52);
    BoardSq[][] board = new BoardSq[3][3];
    Hand hand = null;

    @PostConstruct
    public void init() {

        for (int i=0; i < 52; i ++ ) {
            if (i >= 0 &&  i < 13 ) {
                deck[i] = new Card(Suit.CLUB,i+1) ;
            } else if (i >= 13 &&  i < 26 ) {
                deck[i] = new Card(Suit.SPADE,i + 1 - 13) ;
            } else if (i >= 26 &&  i < 39 ) {
                deck[i] = new Card(Suit.DIAMOND,i + 1 - 26) ;
            } else if (i >= 39 &&  i < 52 ) {
                deck[i] = new Card(Suit.HEART,i + 1 - 39) ;
            }

        }

        board[0][0] = new BoardSq("heartKing",new Card[]{new Card(Suit.HEART,13)}) ;
        board[0][1] = new BoardSq("heartQueen",new Card[]{new Card(Suit.HEART,12)}) ;
        board[0][2] = new BoardSq("heartKingQueen",new Card[]{new Card(Suit.HEART,12),new Card(Suit.HEART,13)}) ;

        board[1][0] = new BoardSq("heartAce", new Card[]{new Card(Suit.HEART,1)});
        board[1][1] = new BoardSq("heartTen", new Card[]{new Card(Suit.HEART,10)});
        board[1][2] = new BoardSq("heartJack", new Card[]{new Card(Suit.HEART,11)});

        board[2][0] = new BoardSq("pot", null);
        board[2][1] = new BoardSq("kitty" , null);
        board[2][2] = new BoardSq("eightNineTenSingleSuit",  new Card[]{ new Card(Suit.HEART, 7),new Card(Suit.HEART, 8),new Card(Suit.HEART, 9),
                                                                               new Card(Suit.DIAMOND, 7),new Card(Suit.DIAMOND, 8),new Card(Suit.DIAMOND, 9),
                                                                               new Card(Suit.CLUB, 7),new Card(Suit.CLUB, 8),new Card(Suit.CLUB, 9),
                                                                               new Card(Suit.SPADE, 7),new Card(Suit.SPADE, 8),new Card(Suit.SPADE, 9)});



    }


    @GetMapping("start")
    public String handGet(@ModelAttribute("hand") Hand hand, Model model,@RequestParam(required = false, value="action" ) String action) {

        model.addAttribute("board",board);
        return "start";

    }

    @PostMapping("start")
    public String handPost(@ModelAttribute("hand") Hand hand, Model model, @RequestParam(required = false, value="action" ) String action) {

        if (action.equalsIgnoreCase("Pick Card")) {
            for (int i = 0 ; i < 5; i++)  {
                int cardNum = random.nextInt(0,51);

                while (deck[cardNum] == null  || hand.checkCardExists(deck[cardNum])) {
                    cardNum = random.nextInt(0,51);
                }


                hand.addCard(i,deck[cardNum]);

                deck[cardNum] = null;
            }
            this.hand = hand;
        } else if (action.equalsIgnoreCase("Play Card")) {
            for (int i = 0; i <= 2; i ++) {
                for (int j=0; j <=2; j++) {
                    board[i][j].hasCards(new ArrayList<Card>(Arrays.asList(this.hand.getHand())));
                }
            }


            hand.setHand(this.hand.getHand());

        }


       model.addAttribute("board",board);



        return "start";
    }

    @PostMapping("selected")
    public ResponseEntity<Map<String,Object>> selectPost(@RequestBody Map<String, Object> payload) {


        int cardNum = 0;
        do  {
            cardNum = random.nextInt(0,51);
        } while (deck[cardNum] == null  || hand.checkCardExists(deck[cardNum]));

        Integer handIndex = (Integer) payload.get("handIndex");
        hand.getHand()[handIndex] = deck[cardNum];

        Map<String,Object> cardObject = new HashMap<String,Object>();

        cardObject.put("index", (Integer) payload.get("handIndex"));
        cardObject.put("suit", deck[cardNum].getSuit());
        cardObject.put("number", deck[cardNum].getNumber());
        cardObject.put("img", deck[cardNum].getCardImg());

        return  ResponseEntity.ok(cardObject);
    }




}
