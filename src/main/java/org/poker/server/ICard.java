package org.poker.server;


public interface ICard {

    /**
     * Represents the color of a card.
     */
    enum Color {
        clubs,
        hearts,
        diamonds,
        spades
    }

    /**
     * Returns a string representation of the card.
     *
     * @return a string representation of the card.
     */
    String showCard();

    /**
     * Gets the color of the card.
     *
     * @return the color of the card.
     */
    Color getColor();

    /**
     * Sets the color of the card.
     *
     * @param color the color to set.
     */
    void setColor(Color color);

    /**
     * Gets the value of the card.
     *
     * @return the value of the card.
     */
    int getValue();

    /**
     * Sets the value of the card.
     *
     * @param value the value to set.
     */
    void setValue(int value);


}