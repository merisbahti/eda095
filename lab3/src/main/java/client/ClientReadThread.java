package client;

import java.net.*;
import java.io.*;

public class ClientReadThread extends Thread {
    InputStream is;

    public ClientReadThread(InputStream is) {
        this.is = is;
        System.out.println("Starting client read thread");
    }

    public void run() {
        while (true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while ((line = br.readLine()) != null) System.out.println(line);
            } catch (IOException e) {
                System.out.println("IOException in ClientReadThread:\n"+e.getMessage());
            }
        }
    }
}
