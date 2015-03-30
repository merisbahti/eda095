package client;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1337);
            (new ClientReadThread(socket.getInputStream())).start();
            while (true) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
                String userInput = userInputBR.readLine();
                System.out.println();
                out.println(userInput);
            }
        } catch (UnknownHostException e) {
            System.out.println("Unknown host exception:\n"+e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException caught:\n"+e.getMessage());
        }
    }
}
