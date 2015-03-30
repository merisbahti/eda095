package SingleThreaded;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import util.ParserGetter;

public class LinkGetter extends HTMLEditorKit.ParserCallback {

    static String baseURL;
    private ArrayList<URL> queue;
    private Set<String> mails;
    private ArrayList<URL> visited;


    public LinkGetter(String baseURL, ArrayList<URL> queue, Set<String> mails, ArrayList<URL> visited) {
        super();
        this.queue      = queue;
        this.baseURL    = baseURL;
        this.mails      = mails;
        this.visited    = visited;
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
        //System.out.println("Start: " + tag + " Position: " + position);
        if (tag == HTML.Tag.A) {
            String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
            addToQueue(href);
        }
    }

    @Override
    public void handleEndTag(HTML.Tag tag, int position) {
        //System.out.println("End: " + tag + " Position: " + position);
    }

    private void addToQueue(String href) {
            try {
                URL tmp = new URL(new URL(baseURL), href);
                if (tmp.getProtocol().equals("http") || tmp.getProtocol().equals("https")) {
                    if (!visited.contains(tmp) && !queue.contains(tmp)) queue.add(tmp);
                } else if (tmp.getProtocol().equals("mailto")) {
                    mails.add(tmp.toString());
                } else {
                    System.out.println("Not handled protocol: " + tmp);
                }
            } catch (Exception e) {
                //System.out.println("URL Exception prolly:" + e.getMessage());
            }
    }

    @Override
    public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {
        //System.out.println("Simple: " + t + " Position: " + pos);
        if (tag == HTML.Tag.BASE) {
        //    String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
        //    baseURL = href;
        //    System.out.println("Base URL: " + href);
        }
        if (tag == HTML.Tag.IMG) {
        //    String href = (String) attributes.getAttribute(HTML.Attribute.SRC);
        //    System.out.println("Image: " + href);
        }
        if (tag == HTML.Tag.FRAME) {
            String href = (String) attributes.getAttribute(HTML.Attribute.SRC);
            addToQueue(href);
            System.out.println("Frame: " + href);
        }
    }

    @Override
    public void handleText(char[] text, int position) {
    }

}
