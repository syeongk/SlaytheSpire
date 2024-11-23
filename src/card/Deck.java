package card;

import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> deck;

    public Deck(){
        deck = new LinkedList<>();
    }

    public LinkedList<Card> getDeck(){
        return deck;
    }
}
