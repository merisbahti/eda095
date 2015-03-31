package client;

import java.net.*;
import java.io.*;

public class ClientReadThread extends Thread {
    Socket is;
    public ClientReadThread(Socket is) {
        this.is = is;
    }

    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) System.out.println(line);
            System.out.println("Closing connection");
            is.close();
        } catch (IOException e) {
            System.out.println("IOException in ClientReadThread:\n"+e.getMessage());
        }
    }
}
