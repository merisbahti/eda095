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
            //DatagramSocket ds = new DatagramSocket(4040);
            //while(true) {
            byte[] buf = (Integer.toString(4040)).getBytes();
            DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,4099);
            ms.send(dp);
            //recieve response containing time server port
            byte[] buf2 = new byte[65536];
            DatagramPacket recv = new DatagramPacket(buf2, buf2.length);
            ms.receive(recv);
            String addrAndPort = new String(recv.getData(), recv.getData().length).trim();
            System.out.println(addrAndPort);
            for (String z : addrAndPort.split("\\|"))
                System.out.println(z);
            int port = Integer.parseInt(addrAndPort.split("\\|")[1]);
            System.out.println("Found timeserver at: " + port);
            //ask time server for DATE
            InetAddress addr = InetAddress.getByName(addrAndPort.split("\\|")[0]);
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket out = new DatagramPacket("date".getBytes(), 4, addr, port);
            ds.send(out);
            DatagramPacket in = new DatagramPacket(new byte[100], 100);
            ds.receive(in);
            System.out.println(new String(in.getData(), 0, in.getData().length));
            //}
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }

}
