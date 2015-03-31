package server;

import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {
    Socket client;
    Mailbox mb; 
    String id;

    public ClientHandler(Socket client, Mailbox mb, String id) {
        this.client = client;
        this.mb = mb;
        this.id = id;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = "";
            boolean online = true;
            while (online) {
                line = br.readLine();
                mb.putMessage(new Message(id, line));
                if (line.startsWith("/quit")) online = false;
            }
        } catch (IOException e) {
            System.out.println("IOException caught:\n" + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught:\n" + e.getMessage());
        } finally {
            System.out.println("Client with id " + id + " disconnected.");
            System.out.println("reciever deregistered: " + (mb.removeReciever(id) ? "true" : "false"));
            try {
                client.shutdownInput();
                client.shutdownOutput();
                client.close();
            } catch (IOException e)  {
                System.err.println("Failed to close client socket."); 
            }
        }
  }
}
