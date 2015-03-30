package Misc;

import java.net.*;
import java.io.*;

/*
    Doesn't accept more than one connection at a time...
*/
public class EchoTCP1 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1337);
            while (true) {
                Socket s = server.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter output = new PrintWriter(s.getOutputStream(), true);
                String line = "";
                while ((line = input.readLine()) != null) {
                    output.println(line);
                }
            }        
        } catch (IOException e) { 
        }
    }

}
