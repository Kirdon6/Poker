package org.poker.server;


import java.util.List;

public interface IDialogue {

    /**
     * Sets the value of use_1.
     *
     * @param use_1 the value to set.
     */
    void setUse_1(boolean use_1);

    /**
     * Displays a welcome message.
     */
    void welcome();

    /**
     * Displays an option.
     */
    void option();

    /**
     * Gets the name from the user.
     *
     * @return the name entered by the user.
     */
    String getName();

    /**
     * Converts the given data to lowercase.
     *
     * @param data the data to convert.
     * @return the converted lowercase string.
     */
    String lower(String data);

    /**
     * Gets the answer from the user.
     *
     * @return the answer entered by the user.
     */
    String getAnswer();

    /**
     * Shows the cards held by the specified player.
     *
     * @param player the player whose cards to show.
     */
    void showCards(IPlayer player);

    /**
     * Shows the cards on the table.
     *
     * @param table the list of cards on the table.
     */
    void showTable(List<Card> table);

    /**
     * Asks the player what to do based on the current state of the game.
     *
     * @param bank  an array representing the current state of the bank.
     * @param money the amount of money the player has.
     */
    void whatToDo(int[] bank, int money);

    /**
     * Asks the player how much to bet based on the current state of the game.
     *
     * @param bank  an array representing the current state of the bank.
     * @param money the amount of money the player has.
     */
    void howMuch(int[] bank, int money);

    /**
     * Displays the given text.
     *
     * @param text the text to display.
     */
    void out(String text);

    /**
     * Announces the winner of the game.
     *
     * @param win true if the player won, false otherwise.
     */
    void winner(boolean win);

    /**
     * Checks if the given string consists only of digits.
     *
     * @param str the string to check.
     * @return true if the string consists only of digits, false otherwise.
     */
    boolean is_digits(String str);

    /**
     * Gets the port number from the user.
     *
     * @return the port number entered by the user.
     */
    int getPort();

    /**
     * Gets the address from the user.
     *
     * @return the address entered by the user.
     */
    String getAddress();

}