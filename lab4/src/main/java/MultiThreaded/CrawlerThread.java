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
        URL tmp = s.getFromQueue();
        System.out.println("Picking:" + tmp);
        while (tmp != null) {
            HTMLEditorKit.ParserCallback callback = new LinkGetter(tmp, s);
            try {
                // check filetype here plz
                URLConnection uc = tmp.openConnection();
                uc.setConnectTimeout(5000);
                if (uc.getContentType().contains("text/html;")) {
                    InputStream in = new BufferedInputStream(tmp.openStream());
                    InputStreamReader r = new InputStreamReader(in);
                    HTMLEditorKit.Parser parser = (new ParserGetter()).getParser();
                    parser.parse(r, callback, true);
                }
            } catch (NullPointerException e) {
                System.err.println("NPE at: " + tmp);
            } catch (java.io.FileNotFoundException e) {
                System.err.println("File not found at: " + tmp);
            } catch (IOException ex) {
                System.err.println(ex);
            }
            tmp = s.getFromQueue();
            System.out.println("Picking:" + tmp);
        }
        System.out.println("tmp was null");
    }


}
