# Texas Hold'em Poker Game

This is a Java implementation of the popular Texas Hold'em Poker game.
It allows players to compete against each other in a virtual poker game, following the rules and mechanics of the Texas Hold'em variant.

## Game Rules

Texas Hold'em Poker is a community card game where players aim to make the best possible hand using a combination of their hole cards (private cards) and the shared community cards. The game consists of several rounds:

  -  Preflop: Each player is dealt two private cards facedown.
  -  Flop: Three community cards are dealt face up on the table.
  -  Turn: A fourth community card is dealt face up.
  -  River: A fifth and final community card is dealt face up.
  -  Showdown: Players reveal their hole cards, and the best hand wins the pot.

The game follows standard poker hand rankings, including combinations like pairs, flushes, straights, and more. The winner of the pot is determined based on the strength of their hand.

## Used Libraries
Only standard Java libraries were used.

## User Interface

### Game Termination Conditions

1. During singleplayer gameplay, the game ends only if one of the players has a money value of 0.
2. During simulation, the game ends similarly to condition 1 or after simulating ten rounds.

## Game Controls
The entire game is played in the console using command-line communication.

### Modes

This game can be run in 3 modes: Singleplayer, Simulation and Network game. 


## Programming Part

### Structure

The main class Game consists all elements related to a single round of the game. It also manages the flow of the entire game.

It contains variables for players player, player2, opponent1, opponent2, a variable representing the table with cards table, a deck of cards deck, and an instance of the Dialogue class responsible for user communication.

Class Player  represents individual players. They can be created as a Player in the case of a user or using the AI class, which is a subclass of Player and represents computer-controlled virtual players.

Class Dialogue  handles the communication between the user and the game. It also manages writing simulations to files and communicates through network.

Class Deck  represents the actual game deck.

Class Card represents a single card.

Class Client represents players endpoint in the network game.


## AI Decision Making
Both difficulty levels have decision-making based on a similar principle.

### Hard Difficulty
The harder difficulty makes decisions based on three parameters: comparing its own money with opponents', aggression, and a random parameter.
Comparing money determines the chances of raising bets, calling, or folding cards. The computer will raise bets more often if it has more money compared to the opponents.
Aggression is calculated when dealing cards. It determines the potential of the player's hand. Aggression increases if the player has cards of the same suit and/or if the card values are close to each other.
The random parameter gives an advantage in certain situations (for example, the opponent never folds cards if they have the same amount of money in the pot, they prefer to wait for the next card).

### Easy Difficulty
The easier difficulty is similar to the harder one but does not use aggression, and the random parameter is uniform, meaning each possible next move is equally likely.

### Raise
Raising is also determined by a random parameter and aggression. These two variables define the range from which a random number is selected.

## Singleplayer
In a single-player game, the goal is to defeat one opponent. The user can choose one of the two difficulty levels mentioned above. The game does not support more than one opponent.

## Simulation
In the simulation, two computers play against each other. One is set to hard difficulty, and the other is set to easy difficulty. The result is a simulation recorded in a text file. Player 1 is named Jim, and Player 2 is named Joe.

## Network game
In the network game, two players can connect to server and play against each other. In order to play network game, it is necessary to create server (Main.java) choose network game and create two clients (Client.java).


## Potential Improvements
This game has several potential areas for improvement. Here are a few suggestions:

- Creating a graphical user interface
- Enhancing AI decision making by considering cards on the table and in the deck
- Expanding the game for more players
- Adding Big Blind and Small Blind to increase the game's dynamics
