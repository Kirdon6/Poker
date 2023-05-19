package org.poker.server;


import java.util.Random;

public class AI extends Player {

    private int money;
    private int aggression;
    private boolean hard = true;

    /**
     * Gets the difficulty of the AI.
     *
     * @return The difficulty of the AI.
     */
    public boolean isHard() {
        return hard;
    }

    /**
     * Sets the difficulty of the AI.
     *
     * @param hard The difficulty of the AI.
     */
    public void setHard(boolean hard) {
        this.hard = hard;
    }

    /**
     * Gets the money of the AI.
     *
     * @return The money of the AI.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the money of the AI.
     *
     * @param money The money of the AI.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Gets the aggression level of the AI.
     *
     * @return The aggression level of the AI.
     */
    public int getAggression() {
        return aggression;
    }

    /**
     * Sets the aggression level of the AI.
     *
     * @param aggression The aggression level of the AI.
     */
    public void setAggression(int aggression) {
        this.aggression = aggression;
    }

    /**
     * Creates a new AI instance with the given money and aggression level.
     *
     * @param money      The initial money of the AI.
     * @param aggression The initial aggression level of the AI.
     */
    public AI(int money, int aggression) {
        setMoney(money);
        this.aggression = aggression;
    }

    /**
     * Determines how much money the AI wants to raise based on its aggression level, available money, and opponent's money.
     * It depends only on how aggressive the AI is, how much money it has, and how much its opponent has.
     *
     * @param opMoney The opponent's money.
     * @param over    Indicates if the opponent has raised.
     * @param inBank  The amount of money in the bank.
     * @return The amount of money the AI wants to raise.
     */
    public int raise(int opMoney, boolean over, int inBank) {
        Random random = new Random();

        // If opponent didn't raise
        if (!over) {
            if (aggression > 5) {
                if (opMoney > getMoney()) {
                    int lowerBound = getMoney() / 4;
                    int upperBound = getMoney() / 2;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                } else {
                    int lowerBound = getMoney() / 3;
                    int upperBound = 3 * getMoney() / 4;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                }
            } else if (aggression < 5) {
                if (opMoney > getMoney()) {
                    int lowerBound = getMoney() / 5;
                    int upperBound = 2 * getMoney() / 5;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                } else {
                    int lowerBound = getMoney() / 4;
                    int upperBound = getMoney() / 2;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                }
            } else {
                if (opMoney > getMoney()) {
                    int lowerBound = getMoney() / 8;
                    int upperBound = getMoney() / 4;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                } else {
                    int lowerBound = getMoney() / 4;
                    int upperBound = getMoney() / 3;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                }
            }
        } else {
            if (aggression > 5) {
                if (opMoney > getMoney()) {
                    int lowerBound = inBank;
                    int upperBound = inBank + (getMoney() - inBank) / 2;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                } else {
                    int lowerBound = inBank;
                    int upperBound = inBank + 3 * (getMoney() - inBank) / 4;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                }
            } else if (aggression < 5) {
                if (opMoney > getMoney()) {
                    int lowerBound = inBank;
                    int upperBound = inBank + 2 * (getMoney() - inBank) / 5;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                } else {
                    int lowerBound = inBank;
                    int upperBound = inBank + 3 * (getMoney() - inBank) / 5;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                }
            } else {
                if (opMoney > getMoney()) {
                    int lowerBound = inBank;
                    int upperBound = inBank + (getMoney() - inBank) / 8;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                }
                else
                {
                    int lowerBound = inBank;
                    int upperBound = inBank + (getMoney() - inBank) / 6;
                    int amount = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    return amount;
                }
            }
        }
    }
    /**
     * Determines the aggressiveness of the AI.
     *
     * @return The aggressiveness level as an integer.
     */
    public int aggressiveness()
    {
        /*
        setting aggressiveness of AI
        */
        int agr = 0;
        int card_difference = Math.abs(cards[0].getValue() - cards[1].getValue());
        if (card_difference <= 2) {
            agr = agr + 5;
        }
        if (cards[0].getColor() == cards[1].getColor()) {
            agr = agr + 5;
        }
        return agr;
    }

    /**
     * Makes a decision based on the opponent's money and the current bank.
     *
     * @param op_money The opponent's money.
     * @param bank The current bank.
     * @return The decision as a string.
     */
    public String decision(int op_money, int[] bank)
    {
        int opponent_position = (getPosition() + 1) % 2;
        if (getMoney() < bank[0] - bank[1])
        {
            return "fold";
        }
        if (getMoney() == 0)
        {
            return "check";
        }

        Random random = new Random();
        int randomnum  = random.nextInt((10 - 1 ) + 1)  + 1;

        if (hard)
        {
            if (getAggression() > 5)
            {
                if (op_money + bank[opponent_position] >= getMoney() + bank[getPosition()]) //checking who has more money
                {
                    if (bank[opponent_position] == bank[getPosition()]) //options are -> raise, check
                    {
                        // 40 % chance to raise -> bluffing
                        if (randomnum <= 4)
                        {
                            return "raise";
                        }
                        // 60 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }
                    }
                    else if (bank[opponent_position] > bank[getPosition()]) // options are -> call, raise, fold
                    {
                        // 60 % chance to call -> not committing much
                        if (randomnum <= 6)
                        {
                            return "call";
                        }
                        // 20 % chance to fold -> giving up
                        else if (randomnum <= 8 && randomnum > 6)
                        {
                            return "fold";
                        }
                        // 20 % chance to raise -> feeling confident
                        else
                        {
                            if (getMoney() < bank[opponent_position] - bank[getPosition()]) // if it can't raise because it doesn't have money it will call
                            {
                                return "call";
                            }
                            return "raise";
                        }
                    }
                    else // more money in the bank -> it will never fold
                    {
                        // 50 % chance to raise -> feeling confident
                        if (randomnum <= 5)
                        {
                            return "raise";
                        }
                        // 50% to check -> waiting for next card
                        else
                        {
                            return "check";
                        }
                    }
                }
                else if (op_money + bank[opponent_position] < getMoney() + bank[getPosition()]) //checking who has more money
                {
                    if (bank[opponent_position] == bank[getPosition()]) //options are -> raise, check
                    {
                        if (op_money == 0)
                        {
                            return "check";
                        }
                        // 70 % chance to raise -> feeling confident
                        if (randomnum <= 7)
                        {
                            return "raise";
                        }
                        // 30 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }
                    }
                    else if (bank[opponent_position] > bank[getPosition()]) // options are -> call, raise, fold
                    {
                        // 50 % chance to raise -> feeling confident
                        if (randomnum <= 5)
                        {

                            if (getMoney() < bank[opponent_position] - bank[getPosition()])
                            {
                                return "call";
                            }
                            return "raise";
                        }
                        // 50 % chance to call -> not committing much
                        else
                        {
                            return "call";
                        }
                    }
                    else // more money in the bank -> it will never fold
                    {
                        if (op_money == 0)
                        {
                            return "check";
                        }
                        // 50 % chance to raise -> feeling confident
                        if (randomnum <= 5)
                        {
                            return "raise";
                        }
                        // 50 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }

                    }
                }

            }
            else if (getAggression() == 5) //less agressive playing
            {
                if (op_money + bank[opponent_position] >= getMoney() + bank[getPosition()]) //checking who has more money
                {
                    if (bank[opponent_position] == bank[getPosition()]) //options are -> raise, check
                    {
                        // 20 % chance to raise -> playing aggressively
                        if (randomnum <= 2)
                        {
                            return "raise";
                        }
                        // 80 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }
                    }
                    else if (bank[opponent_position] > bank[getPosition()]) // options are -> call, raise , fold
                    {
                        // 70 % chance to call -> not committing much
                        if (randomnum <= 7)
                        {
                            return "call";
                        }
                        // 20 % chance to fold -> giving up
                        else if (randomnum <= 9 && randomnum > 7)
                        {
                            return "fold";
                        }
                        // 10 % chance to raise -> playing aggressively
                        else
                        {

                            if (getMoney() < bank[opponent_position] - bank[getPosition()])
                            {
                                return "call";
                            }
                            return "raise";
                        }
                    }
                    else
                    {
                        // 30 % chance to raise -> playing aggressively
                        if (randomnum <= 3)
                        {
                            return "raise";
                        }
                        // 70 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }

                    }
                }
                else if (op_money + bank[opponent_position] < getMoney() + bank[getPosition()]) //checking who has more money
                {
                    if (bank[opponent_position] == bank[getPosition()]) //options are -> raise, check
                    {
                        if (op_money == 0)
                        {
                            return "check";
                        }
                        // 60 % chance to raise -> palying aggressively
                        if (randomnum <= 6) //bluff
                        {
                            return "raise";
                        }
                        // 40 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }
                    }
                    else if (bank[opponent_position] > bank[getPosition()]) // options are -> call , raise , fold
                    {
                        randomnum = random.nextInt((10 - 1 ) + 1)  + 1;;
                        // 30 % chance to raise -> aplying aggressively
                        if (randomnum <= 3)
                        {
                            if (getMoney() < bank[opponent_position] - bank[getPosition()])
                            {
                                return "call";
                            }
                            return "raise";
                        }
                        // 50 % chance to call -> not committing much
                        else if (randomnum > 3 && randomnum <= 8)
                        {
                            return "call";
                        }
                        // 20 % chance to fold -> giving up
                        else
                        {
                            return "fold";
                        }

                    }
                    else
                    {
                        // 30 % chance to raise -> playing aggressively
                        if (randomnum <= 3)
                        {
                            return "raise";
                        }
                        // 70 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }

                    }
                }
            }
            else
            {
                if (op_money + bank[opponent_position] >= getMoney() + bank[getPosition()]) //checking who has more money
                {
                    if (bank[opponent_position] == bank[getPosition()]) //options are -> raise, check
                    {
                        // 10 % chance to raise -> palying aggressively
                        if (randomnum <= 1)
                        {
                            return "raise";
                        }
                        // 90 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }
                    }
                    else if (bank[opponent_position] > bank[getPosition()]) // options are -> call , raise , fold
                    {
                        // 40 % chance to call -> not committing much
                        if (randomnum <= 4)
                        {
                            return "call";
                        }
                        // 60 % chance to fold -> giving up
                        else
                        {
                            return "fold";
                        }
                    }
                    else //playing safe
                    {
                        return "check";

                    }
                }
                else if (op_money + bank[opponent_position] < getMoney() + bank[getPosition()])
                {
                    if (bank[opponent_position] == bank[getPosition()]) //options are -> raise, check
                    {
                        if (op_money == 0)
                        {
                            return "check";
                        }
                        // 20 % chance to raise -> palying aggressively
                        if (randomnum <= 2) //bluff
                        {
                            return "raise";
                        }
                        // 80 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }
                    }
                    else if (bank[opponent_position] > bank[getPosition()]) // options are -> call , raise , fold
                    {
                        // 50 % chance to call -> not committing much
                        if (randomnum <= 5)
                        {
                            return "call";
                        }
                        // 50 % chance to fold -> giving up
                        else
                        {
                            return "fold";
                        }
                    }
                    else
                    {
                        if (op_money == 0)
                        {
                            return "check";
                        }
                        // 20 % chance to raise -> feeling confident
                        if (randomnum <= 2)
                        {
                            return "raise";
                        }
                        // 80 % chance to check -> waiting for next card
                        else
                        {
                            return "check";
                        }

                    }
                }
            }
        }
        // easy difficiulty using uniform distribution between options
        else
        {
            randomnum = random.nextInt((2 - 1 ) + 1)  + 1;
            if (bank[opponent_position] == bank[getPosition()])
            {
                if (op_money == 0)
                {
                    return "check";
                }
                // 50 % chance to raise and 50% to check
                if (randomnum == 1)
                {
                    return "raise";
                }
                else
                {
                    return "check";
                }
            }
            else if (bank[opponent_position] > bank[getPosition()])
            {
                // 50 % chance to call and 50% to fold
                if (randomnum == 1)
                {
                    return "call";
                }
                else if (randomnum == 2)
                {
                    return "fold";
                }
            }
            else
            {
                if (op_money == 0)
                {
                    return "check";
                }
                // 50% to raise and 50% to check
                if (randomnum == 1)
                {
                    return "raise";
                }
                else
                {
                    return "check";
                }
            }

        }
        return "null";
    }
}




