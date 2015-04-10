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
                //send response
                byte[] buf = s.getBytes();
                DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,4099);
                ms.send(dp);
                //recieve response
                byte[] buf2 = new byte[1000];
                DatagramPacket recv = new DatagramPacket(buf2, buf2.length);
                ms.receive(recv);
                System.out.println("MC Response: " + new String(buf2, buf2.length));
                /*
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket offer = new DatagramPacket(new byte[100], 100);
                ds.read(offer);
                System.out.println("Recieved offer: "+ new String(offer.getData(), offer.getData().length));
                */
            }
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }

}
