package SingleThreaded;

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
        ArrayList<URL>          queue  = new ArrayList<URL>(); 
        Set<String>             emails  = new HashSet<String>(); 
        ArrayList<URL>          visited = new ArrayList<URL>(); 
        try {
            queue.add(new URL("http://cs.lth.se"));
        } catch (MalformedURLException e) { // forgot how to spell malformedurlexception
            System.err.println(e.getMessage());
            return;
        }
        while (queue.size() > 0) {
            URL url = queue.remove(0);
            visited.add(url);
            System.out.println("Visiting url: " + url);
            HTMLEditorKit.ParserCallback callback = new LinkGetter2(url, queue, emails, visited);
            try {
                if (url.openConnection().getContentType().contains("text/html;")) { // filecheck
                    InputStream in = new BufferedInputStream(url.openStream());
                    InputStreamReader r = new InputStreamReader(in);
                    parser.parse(r, callback, true);
                }
            } catch (NullPointerException e) {
                System.err.println("NPE at: " + url);
            } catch (java.io.FileNotFoundException e) {
                System.err.println("File not found at: " + url);
            } catch (IOException ex) {
                System.err.println(ex);
            }
            System.out.println("queue s: " + queue.size());
            System.out.println("visited: " + visited.size());
            System.out.println("emails : " + emails.size());
        }
    }
}
