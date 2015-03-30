package MultiThreaded;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import util.ParserGetter;

public class CrawlerThread extends Thread {
    Storage s;

    public CrawlerThread(Storage s) {
        this.s = s;
    }

    public void run() {
        while (true) {
            ParserGetter kit                = new ParserGetter();
            HTMLEditorKit.Parser    parser  = kit.getParser();
            URL tmp = s.getFromQueue();
            if (tmp != null) {
                HTMLEditorKit.ParserCallback callback = new LinkGetter(tmp.toString(), s);
                try {
                    // check filetype here plz
                    if (tmp.openConnection().getContentType().contains("text/html;")) {
                        InputStream in = new BufferedInputStream(tmp.openStream());
                        InputStreamReader r = new InputStreamReader(in);
                        parser.parse(r, callback, true);
                    }
                } catch (NullPointerException e) {
                    System.err.println("NPE at: " + tmp);
                } catch (java.io.FileNotFoundException e) {
                    System.err.println("File not found at: " + tmp);
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            } else {
                System.out.println("tmp was null");
                //return;
            }
        }
    }


}
