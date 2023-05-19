package org.poker.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Main {
    /**
     * Main function, it creates server where you can start one of 3 types of games
     * @param args not used
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Dialogue dialogue = new Dialogue(new PrintStream(System.out));
        dialogue.welcome();
        while (true) { // menu loop
            dialogue.option();
            String answer = dialogue.getAnswer();
            if (answer.equals("play")) {
                Game game = new Game(dialogue);
                game.singlePlayer();
            } else if (answer.equals("sim")) {
                PrintStream outFile = new PrintStream("PokerSim.txt");
                Dialogue dialogueSim = new Dialogue(outFile);
                Game game = new Game(dialogueSim);
                game.simulation();
                outFile.close();
            }
            else if (answer.equals("network"))
            {
                int port = dialogue.getPort();
                try (ServerSocket s = new ServerSocket(port)) {

                    System.out.println("Server ready");

                    System.out.println("Waiting for 1st client...");
                    Socket client1 = s.accept();
                    Scanner in1 = new Scanner(client1.getInputStream());
                    PrintStream out1 = new PrintStream(client1.getOutputStream(), true);
                    System.out.println("First player connected");

                    System.out.println("Waiting for 2nd client...");
                    Socket client2 = s.accept();
                    Scanner in2 = new Scanner(client2.getInputStream());
                    PrintStream out2 = new PrintStream(client2.getOutputStream(), true);
                    Dialogue networkDialogue = new Dialogue(out1, in1, out2, in2);
                    System.out.println("Game is ready");
                    Game game = new Game(networkDialogue);
                    game.networkGame();

                    client1.close();
                    client2.close();
                }
            }
            else if (answer.equals("quit")) {
                dialogue.out("Thanks for playing!\n");
                break;
            } else {
                dialogue.out("WRONG INPUT\n");
            }
        }

    }
}