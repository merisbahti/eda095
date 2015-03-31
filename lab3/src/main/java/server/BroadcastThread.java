package server;

import java.net.*;
import java.io.*;
import java.util.*;

public class BroadcastThread extends Thread {
    Mailbox mb; 

    public BroadcastThread(Mailbox mb) {
        this.mb = mb; 
    }

    public void run() {
        while (true) {
            try {
                ArrayList<Message> mx = mb.getMessages();
                for (Message m : mx) {
                    boolean sendToSelf = !(m.getMessage().startsWith("/echo"));
                    for (Map.Entry<String, Socket> os : mb.getRecievers()) 
                        if ((os.getKey() != m.getId()) == sendToSelf) { 
                            try {
                                PrintWriter out = new PrintWriter(os.getValue().getOutputStream(), true);
                                out.println(m.toString());
                                out.flush();
                            } catch (IOException e) {
                                System.err.println("IOException when sending msg to" + os.getKey());
                            }
                        }
                }
            } catch (InterruptedException e) {
                System.out.println("BroadcastThread Caught InterruptedException:\n" + e.getMessage());
                System.out.println("Continuing...");
            }
        }
    }
}
