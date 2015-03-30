package SingleURL;

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Runner implements Runnable {
    private URL u; 
    private String downloadfolder;
    public Runner(URL u, String downloadfolder) {
        this.u = u;
        this.downloadfolder = downloadfolder;
    }
    
    public void run() {
        try {
            if (u.openConnection().getContentType().contains("application/pdf")) {
                System.out.println(u.getFile() + " started");
                ReadableByteChannel rbc = Channels.newChannel(u.openStream());
                String tempResName = u.getFile();
                String filename = tempResName.substring(tempResName.lastIndexOf('/')+1, 
                    tempResName.length());
                FileOutputStream fos = new FileOutputStream(downloadfolder+filename);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } else {
                System.out.println("Not proper pdf " + u.getFile() + " type was: " + u.openConnection().getContentType());
            }
        } catch (IOException e) {
            System.out.println("IOException caught");
            System.out.println(e.getMessage());
        } 
    }
}
