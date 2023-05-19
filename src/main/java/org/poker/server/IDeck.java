package org.poker.server;

public interface IDeck {
    /**
     * Shuffles the deck of cards.
     */
    void shuffleDeck();

    /**
     * Deals a card from the deck.
     *
     * @return the card dealt from the deck.
     */
    Card dealCard();


}