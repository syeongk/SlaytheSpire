package test;

import characters.Ironclad;
import org.junit.Test;
import card.Card;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class IroncladTest {

    @Test
    public void testIroncladCards() {
        Ironclad ironclad = new Ironclad();
        LinkedList<Card> ironcladDeck = ironclad.getDeck();
        LinkedList<Card> ironcladDrawPile = ironclad.getDrawPile();

        for (Card card : ironcladDeck){
            System.out.print(card.getCardName()+ " ");
        }
        System.out.println();
        for (Card card : ironcladDrawPile){
            System.out.print(card.getCardName()+ " ");
        }

        assertEquals(10, ironcladDeck.size());
        assertEquals(10, ironcladDrawPile.size());
    }

}
