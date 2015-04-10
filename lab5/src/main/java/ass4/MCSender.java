package ass4;
import java.net.*;
import java.io.*;
import java.util.*;

public class MCSender {

    public static void main(String args[]) {
        try {
            MulticastSocket ms = new MulticastSocket();
            ms.setTimeToLive(1);
            InetAddress ia = InetAddress.getByName((args.length == 0 ? "experiment.mcast.net" : args[0]));
            Scanner scan = new Scanner(System.in);
            while(true) {
                String s = scan.nextLine();
                System.out.println("Sending message: "+s);
                byte[] buf = s.getBytes();
                DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,4099);
                ms.send(dp);
            }
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }

}
