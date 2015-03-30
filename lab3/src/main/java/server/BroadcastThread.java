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
                for (Message m : mx)
                    for (Map.Entry<String, OutputStream> os : mb.getRecievers()) 
                        if (os.getKey() != m.getId()) { 
                            PrintWriter out = new PrintWriter(os.getValue(), true);
                            out.println(m.toString());
                            out.flush();
                        }
            } catch (InterruptedException e) {
                System.out.println("BroadcastThread Caught InterruptedException:\n" + e.getMessage());
                System.out.println("Continuing...");
            }
        }
    }
}
