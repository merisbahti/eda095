package MultiThreaded;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import util.ParserGetter;

public class SingleThread {
    public static void main(String[] args) {
        Storage s = new Storage();
        try {
            s.addToURLs(new URL(new URL("http://cs.lth.se/"), "http://cs.lth.se"));
        } catch (Exception e) {
            System.out.println("Failed adding first link");
            System.exit(1);
        }
        (new AnnouncerThread(s)).start();
        (new CrawlerThread(s)).start();
        try {
            System.out.println("Sleeping for 2 seconds");
            Thread.sleep(2000);
            System.out.println("Awake.");
        } catch (Exception e) {
            System.err.println("Couldn't sleep.");
        }
        for (int i = 1; i < 10; i++) { 
            System.out.println("Starting thread " + i);
            (new CrawlerThread(s)).start();
        };
    }
}
