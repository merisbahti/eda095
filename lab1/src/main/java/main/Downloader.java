package main;

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Downloader {
  public static void main(String[] args) {
    try {
        //url = new URL("http://cs.lth.se/eda031/examination/");
        URL url = new URL(args.length == 0 ? "http://cs.lth.se/eda095/tidigare-aar/eda095-2014/foerelaesningar/?no_cache=1" : args[0] );
        String downloadfolder = "downloadfolder/";
        // Read from URL and parse the text, add all URL's to pdfURLs
        BufferedReader bfr = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = ""; 
        ArrayList<URL> pdfURLs = new ArrayList<URL>();
        Pattern p = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=\"([^\"]*\\.pdf)\"");
        StringBuilder everything = new StringBuilder();
        while ((line = bfr.readLine()) != null) {
            everything.append(line);
        }
        Matcher m = p.matcher(everything);
        while (m.find()) {
            pdfURLs.add(new URL(m.group(1)));
            System.out.println(m.group(1));
        }
        // download all pdfs and save them.        
        for (URL u : pdfURLs) {
            try {
            ReadableByteChannel rbc = Channels.newChannel(u.openStream());
            String tempResName = u.getFile();
            String filename = tempResName.substring(tempResName.lastIndexOf('/')+1, 
                tempResName.length() );
            System.out.println(filename);
            FileOutputStream fos = new FileOutputStream(downloadfolder+filename);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }catch (IOException e) {
                System.err.println("IOException when downloading " + u.getFile());
            }
        }
    } catch (MalformedURLException e) {
        System.out.println("Malformed URL dude, try again.");
        return;
    } catch (IOException e) {
        System.out.println("IOException dude.");
        System.out.println(e.getMessage());
        return;
    }
  }
}
