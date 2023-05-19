package org.poker.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import org.poker.server.Dialogue;
import org.poker.server.IDialogue;

public class Client {

    public static void main(String[] args) throws IOException {
        IDialogue dialogue = new Dialogue(new PrintStream(System.out));

        String address = dialogue.getAddress();
        InetAddress addr = InetAddress.getByName(address);



        int port = dialogue.getPort();
        Socket socket = new Socket(addr, port);

        Scanner input = new Scanner(System.in);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintStream out = new PrintStream(socket.getOutputStream(), true)) {

            String msg;

            while ((msg = in.readLine()) != null) {
                System.out.println(msg);

                if(msg.startsWith(">")) {
                    String answer = input.next();
                    out.println(answer);
                }
            }
        }
        socket.close();
        input.close();
    }
}

