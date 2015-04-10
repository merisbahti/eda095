package ass4;
import java.net.*;
import java.io.*;

public class MCServerOffer {

    public static void main(String args[]) {
        try {
            MulticastSocket ms = new MulticastSocket(4099);
            InetAddress ia = InetAddress.getByName("experiment.mcast.net");
            //InetAddress ia = InetAddress.getByName(args[0]);
            ms.joinGroup(ia);
            while(true) {
                byte[] buf = new byte[65536];
                DatagramPacket dp = new DatagramPacket(buf,buf.length);
                ms.receive(dp);
                String s = new String(dp.getData(),0,dp.getLength());
                System.out.println("Received: "+s);
                System.out.println("From: " + dp.getAddress() + ":" + dp.getPort());
                DatagramSocket ds = new DatagramSocket();
                byte[] msg = ("hello there").getBytes();
                DatagramPacket out = new DatagramPacket(msg, msg.length, dp.getAddress(), dp.getPort());
                ds.send(out);
            }
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }

}
