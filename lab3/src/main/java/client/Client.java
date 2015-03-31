package client;

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        try {
            boolean isAliveFromOtherEnd = true;
            Socket socket = new Socket("localhost", 1337);
            (new ClientReadThread(socket.getInputStream(), isAliveFromOtherEnd)).start();
            while (isAliveFromOtherEnd) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
                String userInput = userInputBR.readLine();
                out.println(userInput);
            }
            System.out.println("Connection closed.");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host exception:\n"+e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException caught:\n"+e.getMessage());
        }
    }
}
