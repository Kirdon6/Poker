package org.poker.server;


public interface IGame {

    /**
     * Creates a player based on the specified mode.
     *
     * @param single true if single-player mode, false otherwise.
     */
    void createPlayer(boolean single);

    /**
     * Deals cards to the players.
     */
    void dealCards();

    /**
     * Starts a network game.
     */
    void networkGame();

    /**
     * Starts a simulation.
     */
    void simulation();

    /**
     * Sets up the starting settings for the game.
     */
    void startingSetting();

    /**
     * Gets the bet amount from the player.
     *
     * @param first true if it's the first player's turn, false otherwise.
     * @return the bet amount.
     */
    int getBet(boolean first);

    /**
     * Announces the winner of the game.
     *
     * @param win true if the player won, false otherwise.
     */
    void announceWinner(Boolean win);

    /**
     * Sets the game mode.
     *
     * @param mode the player's mode.
     */
    void setMode(String mode);

    /**
     * Starts a single-player game.
     */
    void singlePlayer();

    /**
     * Sets the difficulty level for the game.
     *
     * @param dif the difficulty level.
     */
    void setDifficulty(String dif);

    /**
     * Determines the winner of the game.
     */
    void determineWinner();

    /**
     * Displays a dialogue for selecting the difficulty level.
     */
    void difficultyDialogue();

    /**
     * Executes the network game loop.
     */
    void networkLoop();

    /**
     * Executes the single-player game loop.
     */
    void singleplayerLoop();

    /**
     * Executes the simulation loop.
     */
    void simulationLoop();

    /**
     * Creates the flop in the game.
     */
    void createFlop();

    /**
     * Displays options for the player.
     *
     * @param first true if it's the first player's turn, false otherwise.
     */
    void options(boolean first);


}