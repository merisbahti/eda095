package ListURL;

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Runner implements Runnable{
    private ArrayList<URL> urls; 
    private String downloadfolder;
    private int threadNo;

    public Runner(ArrayList<URL> urls, String downloadfolder) {
        this.urls = urls;
        this.downloadfolder = downloadfolder;
        this.threadNo = 0;
    }

    public Runner(ArrayList<URL> urls, String downloadfolder, int threadNo) {
        this.urls = urls;
        this.downloadfolder = downloadfolder;
        this.threadNo = threadNo;
    }
    
    public void run() {
        while (urls.size() > 0) {
            URL u = popList();
            try {
                if (u.openConnection().getContentType().contains("application/pdf")) {
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
                System.err.println("IOException caught when downloading: " + u.getFile());
            } 
        }
        System.out.println("Thread " + threadNo + " going down. Nothing more to do.");
    }

    // atomic 
    public URL popList() {
        synchronized (urls) {
            return urls.remove(0);
        }
    }
}
