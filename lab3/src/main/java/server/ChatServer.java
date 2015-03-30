package server;

import java.net.*;
import java.io.*;

public class ChatServer {
  public static void main(String[] args) {
        Mailbox mb = new Mailbox();
        BroadcastThread bc = new BroadcastThread(mb);
        bc.start();
        try {
            ServerSocket socket = new ServerSocket(1337);
            while (true) {
                Socket newClient = socket.accept();
                String id = mb.registerReciever(newClient.getOutputStream());
                (new ClientHandler(newClient.getInputStream(), mb, id)).start();
            }
        } catch (IOException e) {
            System.out.println("IOException caught in ChatServer:\n" + e.getMessage());
        }
  }
}
