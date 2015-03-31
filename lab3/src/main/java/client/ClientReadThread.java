package client;

import java.net.*;
import java.io.*;

public class ClientReadThread extends Thread {
    InputStream is;
    boolean isAlive;
    public ClientReadThread(InputStream is, boolean isAlive) {
        this.is = is;
        this.isAlive = isAlive;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) System.out.println(line);
            System.out.println("Setting isalive to false");
            isAlive = false; 
        } catch (IOException e) {
            System.out.println("IOException in ClientReadThread:\n"+e.getMessage());
        }
    }
}
