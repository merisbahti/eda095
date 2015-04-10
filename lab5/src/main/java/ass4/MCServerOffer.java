package ass4;
import java.net.*;
import java.io.*;

public class MCServerOffer extends Thread {
    int timeServerPort; 

    public MCServerOffer(int timeServerPort) {
        this.timeServerPort = timeServerPort;
    }

    public void run() {
        try {
            MulticastSocket ms = new MulticastSocket(4099);
            InetAddress ia = InetAddress.getByName("experiment.mcast.net");
            ms.joinGroup(ia);
            while(true) {
                byte[] buf = new byte[65536];
                DatagramPacket dp = new DatagramPacket(buf,buf.length);
                ms.receive(dp);
                String s = new String(dp.getData(),0,dp.getLength());
                if (s.equals("q")) {
                    System.out.println("Quitting");
                    System.exit(0);
                }
                System.out.println("Received: "+s);
                System.out.println("From: " + dp.getAddress() + ":" + dp.getPort());
                // respond with timeserver port number
                DatagramSocket ds = new DatagramSocket();
                byte[] msg = (InetAddress.getLocalHost().getHostAddress().toString()+"|"+timeServerPort).getBytes();
                DatagramPacket out = new DatagramPacket(msg, msg.length, dp.getAddress(), dp.getPort());
                ds.send(out);
            }
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }

}
