package org.poker.server;


import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;


import java.util.Locale;

public class Dialogue implements IDialogue {

    private final Scanner scanner1;
    private final PrintStream output1;
    private final Scanner scanner2;
    private final PrintStream output2;
    private boolean use_1 = true;
    @Override
    public void setUse_1(boolean use_1) {
        this.use_1 = use_1;
    }

    private String promptString = null;

    public Dialogue(PrintStream outfile) {
        this.scanner1 = new Scanner(System.in);
        this.output1 = outfile;
        scanner2 = null;
        output2 = null;

    }
    public Dialogue(PrintStream outfile1, Scanner input1, PrintStream outfile2, Scanner input2)
    {
        scanner1 = input1;
        output1 = outfile1;
        scanner2 = input2;
        output2 = outfile2;
        promptString = ">";
    }

    @Override
    public void welcome() {
        /*
        creating opening
        */
        out("***********************************\n");
        out("Welcome to the Texas Hold'em Poker!\n");
        out("***********************************\n");
        out("\n");
    }

    @Override
    public void option() {
        /*
        creating option menu
        */
        out("For playing game type 'play'\n");
        out("For simulation type 'sim'\n");
        out("For network game type 'network'\n");
        out("For quit type 'quit'\n");
        out("\n");
    }

    @Override
    public String getName() {
        out("What is your name?\n");
        return getAnswer();
    }

    @Override
    public String lower(String data) {
        /*
        change all letters to lower case
        */
        return data.toLowerCase(Locale.ROOT);
    }

    @Override
    public String getAnswer() {
        /*
        communicating with user
        */
        if (promptString != null)
        {
            out(promptString + "\n");
        }
        String answer = use_1 ? scanner1.next() : scanner2.next();


        return lower(answer);
    }

    @Override
    public void showTable(List<Card> table) {
        out("Cards on table:");
        for (Card card : table) {
            out(card.showCard() + " ");
        }
        out("\n");
    }

    @Override
    public void showCards(IPlayer player) {
        out(player.showName() + "'s cards are:");
        out(player.getCards()+" \n");
    }


    @Override
    public void whatToDo(int[] bank, int money) {
        /*
        giving options what player can do
        */
        if (use_1)
        {
            out("Your options are:");
            if (money == 0) {
                out("Check\n");
            } else if (bank[0] >= bank[1]) {
                out("Raise   Fold   Check\n");
            } else {
                out("Call   Raise    Fold\n");
            }
        }
        else
        {
            out("Your options are:");
            if (money == 0) {
                out("Check\n");
            } else if (bank[1] >= bank[0]) {
                out("Raise   Fold   Check\n");
            } else {
                out("Call   Raise    Fold\n");
            }
        }

    }

    @Override
    public void howMuch(int[] bank, int money) {
        /*
        giving options how much to bet
        */
        out("How much do you want to bet?\n");
        out("From " + (bank[1] - bank[0]) + " to " + money + "\n");
    }

    @Override
    public void out(String text) {
        /*
        output
        */
        if (use_1)
        {
            output1.print(text);
        }
        else
        {
            output2.print(text);
        }

    }

    @Override
    public void winner(boolean win) {
        /*
        announcing winner
        */
        if (scanner2 != null)
        {
            if (win) {
                setUse_1(true);
                out("You won! Congratulation!\n");
                setUse_1(false);
                out("You lost! Try again!\n");
            } else {
                setUse_1(true);
                out("You lost! Try again!\n");
                setUse_1(false);
                out("You won! Congratulation!\n");
            }
        }
        else
        {
            if (win) {
                out("You won! Congratulation!\n");
            } else {
                out("You lost! Try again!\n");
            }
        }

    }

    @Override
    public boolean is_digits(String str) {
        /*
        checking if argument consists only digits
        */
        return str.chars().allMatch(Character::isDigit);
    }

    @Override
    public int getPort()
    {
        while (true)
        {
            out("Choose port number:\n");
            String answer =  getAnswer();
            if (is_digits(answer))
            {
                return Integer.parseInt(answer);
            }
            else
            {
                out("WORNG INPUT!");
            }
        }


    }

    @Override
    public String getAddress()
    {
        out("Type address:\n");
        String answer =  getAnswer();

        return answer;

    }

}

