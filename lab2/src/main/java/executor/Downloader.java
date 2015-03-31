package executor;

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.concurrent.*;

import ListURL.Runner;

public class Downloader {
  public static void main(String[] args) {
    try {
        URL url = new URL(args.length == 0 ? "http://cs.lth.se/eda095/tidigare-aar/eda095-2014/foerelaesningar/?no_cache=1" : args[0] );
        String downloadfolder = "downloadfolder/";
        // Read from URL and parse the text, add all URL's to pdfURLs
        BufferedReader bfr = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = ""; 
        ArrayList<URL> urls = new ArrayList<URL>();
        Pattern p = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=\"([^\"]*\\.pdf)\"");
        StringBuilder everything = new StringBuilder();
        while ((line = bfr.readLine()) != null) {
            everything.append(line);
        }
        Matcher m = p.matcher(everything);
        while (m.find()) {
            System.out.println(m.group(1));
            urls.add(new URL(m.group(1)));
        }
        for (int i = 0; i < 5; i++) (new Thread(new Runner(urls, downloadfolder, i))).start();
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
