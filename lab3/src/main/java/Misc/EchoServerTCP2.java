package Misc;

import java.net.*;
import java.io.*;

/*
    Accepts more than 1 connection at the same time,
    can't start more than one server on same port.
*/
public class EchoServerTCP2 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1337);
            while (true) {
                (new ClientThread(server.accept())).start();
            }        
        }catch (IOException e) {

        }

    }

}
