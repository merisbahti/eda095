package Misc;

import java.net.*;
import java.io.*;

public class ClientThread extends Thread{
    Socket s; 

    public ClientThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter output = new PrintWriter(s.getOutputStream(), true);
            String line = "";
            while ((line = input.readLine()) != null) {
                output.println(line);
            }
        } catch (IOException e) {
            System.err.println("Clientthread ioexception crash lol");
        }
    }
}
