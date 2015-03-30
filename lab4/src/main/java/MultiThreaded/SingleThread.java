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
        ParserGetter kit = new ParserGetter();
        HTMLEditorKit.Parser    parser = kit.getParser();
        Storage s = new Storage();
        s.addToQueue("http://cs.lth.se/", "http://cs.lth.se");
        (new AnnouncerThread(s)).start();
        for (int i = 0; i < 10; i++) (new CrawlerThread(s)).start();
    }
}
