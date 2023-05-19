package org.poker.server;

import java.util.Random;

public class Deck implements IDeck {

    private Card[] cards;
    private int topDeck;

    public Deck() {
        /*
        constructor for the deck
        it creates every single card
        */
        int numberOfCards = 52;
        int cardsInColor = 13;
        cards = new Card[numberOfCards];
        for (int i = 0; i < numberOfCards; i++) {
            cards[i] = new Card();
            cards[i].setValue((i % cardsInColor) + 2);
            switch (i / cardsInColor) {
                case 0:

                    cards[i].setColor(ICard.Color.clubs);
                    break;
                case 1:
                    cards[i].setColor(ICard.Color.hearts);
                    break;
                case 2:
                    cards[i].setColor(ICard.Color.diamonds);
                    break;
                case 3:
                    cards[i].setColor(ICard.Color.spades);
                    break;
            }
        }
    }

    @Override
    public void shuffleDeck() {
        /*
        shuffle deck by switching two cards from random positions
        */
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < cards.length; j++) {
                int r = random.nextInt(cards.length);
                Card c = cards[j];
                cards[j] = cards[r];
                cards[r] = c;
            }
        }
        topDeck = 0;
    }

    @Override
    public Card dealCard() {
        return cards[topDeck++];
    }

}

