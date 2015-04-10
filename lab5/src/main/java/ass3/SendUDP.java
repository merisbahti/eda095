package ass3;

import java.net.*;
import java.util.*;
import java.text.*;

class SendUDP {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(args.length + " arguments recieved");
            System.err.println("Please specify 2 arguments: port message");
            for (String s : args) 
                System.out.println(s);
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        byte[] msg = args[1].getBytes();
        try { 
            InetAddress addr = InetAddress.getByName("localhost");
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket out = new DatagramPacket(msg, msg.length, addr, port);
            ds.send(out);
            DatagramPacket in = new DatagramPacket(new byte[100], 100);
            ds.receive(in);
            System.out.println(new String(in.getData(), 0, in.getData().length));
        } catch (SocketException e) {
            System.err.println("Exception caught: " + e.getMessage());
        } catch (java.io.IOException e) {
            System.err.println("Expeptlin: " + e.getMessage());
        }     
    }
}
