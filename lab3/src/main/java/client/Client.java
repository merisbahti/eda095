package client;
import main.ChatWindow;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            boolean isAlive = true;
            Socket socket = new Socket("localhost", 1337);
            (new ClientReadThread(socket)).start();
            while (isAlive) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
                String userInput = userInputBR.readLine();
                out.println(userInput);
                isAlive = !userInput.startsWith("/quit") && !socket.isClosed();
            }
            System.out.println("Exiting client.");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host exception:\n"+e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException caught:\n"+e.getMessage());
        }
    }
}
