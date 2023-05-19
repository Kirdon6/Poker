package org.poker.server;

import java.util.List;

public interface IPlayer {

    /**
     * @return name of player
     */
    String showName();

    /**
     * assigns cards to player's hand
     * @param first first card
     * @param second secodn card
     */
    void setCards(Card first, Card second);

    /**
     * @return cards in the hand
     */
    String getCards();

    /**
     * calculates value of the best combination from 7 cards
     * @param table represents 5 cards on the table
     */
    void calculateValue(List<Card> table);

    /**
     * @return value of cards of Player
     */
    int getVal();

    /**
     * set value of cards in player's hand
     * @param val new value to assign
     */
    void setVal(int val);

    /**
     * @return money of the player
     */
    int getMoney();

    /**
     * assigns amount of money for player
     * @param money new value to assign
     */
    void setMoney(int money);

    /**
     * assigns name to player
     * @param name new value to assign
     */
    void setName(String name);

    /**
     * @return position of player
     */
    int getPosition();

    /**
     * assings position in round for player
     * @param position new value to assign
     */
    void setPosition(int position);

    /**
     * creates the best possible combination from player's hand and table
     * @param all cards from which program calculates the best combination of 5 cards from total of 7
     * @return best possible combination
     */
    List<Card> bestComb(List<Card> all);

    /**
     * recursive function for creating combinations
     */
    void combinations(List<Card> all, int k, int offset);

    /**
     * calculate value of 5 cards
     * @param five 5 cards for which we calculate value
     * @return value of 5 cards
     */
    int evaluate(List<Card> five);

    /**
     * next functions give value according to combination that fullfils
     * @param five 5 cards for which we check certain combination
     * @return value of combination
     */
    int straightFlush(List<Card> five);

    int fourOfAKind(List<Card> five);

    int fullHouse(List<Card> five);

    int flush(List<Card> five);

    int straight(List<Card> five);

    int triple(List<Card> five);

    int twoPair(List<Card> five);

    int pair(List<Card> five);

    int highCard(List<Card> five);

    /**
     * sorts hand in descending order
     * @param five combination that needs to be sorted
     * @return sorted cards
     */
    List<Card> sortHand(List<Card> five);

    /**
     * deciding according to value what combination player has
     * @return String with anme of combination
     */
    String hand();

}