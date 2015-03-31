package main;

import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.nio.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.concurrent.*;

import SingleURL.Runner;

public class Downloader {
  public static void main(String[] args) {
    try {
        URL url = new URL(args.length == 0 ? "http://cs.lth.se/eda095/tidigare-aar/eda095-2014/foerelaesningar/?no_cache=1" : args[0] );
        String downloadfolder = "downloadfolder/";
        // Read from URL and parse the text, add all URL's to pdfURLs
        BufferedReader bfr = new BufferedReader(new InputStreamReader(url.openStream()));
        Pattern p = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=\"([^\"]*\\.pdf)\"");
        ArrayList<URL> urls = new ArrayList<URL>();
        String line = ""; 
        ExecutorService pool = Executors.newFixedThreadPool(5);
        StringBuilder everything = new StringBuilder();
        while ((line = bfr.readLine()) != null) {
            everything.append(line);
        }
        Matcher m = p.matcher(everything);
        while (m.find()) {
            System.out.println(m.group(1));
            urls.add(new URL(m.group(1)));
        }
        // Create all threads, and start downloading: 
        for (URL urrl : urls) pool.submit(new Runner(urrl, downloadfolder));
        pool.shutdown();
    } catch (MalformedURLException e) {
        System.out.println("Malformed URL dude, try again.");
        System.out.println(e.getMessage());
    } catch (IOException e) {
        System.out.println("IOException dude.");
        System.out.println(e.getMessage());
    } 
  }
}
