package MultiThreaded;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import util.ParserGetter;

public class LinkGetter extends HTMLEditorKit.ParserCallback {

    static URL baseURL;
    private Storage s;

    public LinkGetter(URL baseURL, Storage s) {
        super();
        this.s = s;
        this.baseURL    = baseURL;
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
        //System.out.println("Start: " + tag + " Position: " + position);
        if (tag == HTML.Tag.A) {
            String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
            if (href != null)  {
                try {
                    URL tmp = new URL(baseURL, href);
                    if (tmp.getProtocol().equals("http") || tmp.getProtocol().equals("https")) {
                         s.addToURLs(tmp);
                    } else if (tmp.getProtocol().equals("mailto")) {
                        s.addToMails(tmp.toString());
                    } else {
                        System.out.println("Not handled protocol: " + tmp);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.out.println("URL Exception prolly:" + e.getMessage() + "\nAt baseURL :" + baseURL + "\n and URL: " + href);
                }
            }
        }
    }

    @Override
    public void handleEndTag(HTML.Tag tag, int position) {
        //System.out.println("End: " + tag + " Position: " + position);
    }

    @Override
    public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int pos) {
        //System.out.println("Simple: " + t + " Position: " + pos);
        if (tag == HTML.Tag.BASE) {
            String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
            if (href != null) {
                try {
                    baseURL = new URL(href);
                } catch (MalformedURLException e) {
                    System.out.println("URL fail in simpletag:" + e.getMessage() + "\nAt baseURL :" + baseURL + "\n and URL: " + href);
                }
            }
        }
        if (tag == HTML.Tag.IMG) {
        //    String href = (String) attributes.getAttribute(HTML.Attribute.SRC);
        //    System.out.println("Image: " + href);
        }
        if (tag == HTML.Tag.FRAME) {
            String href = (String) attributes.getAttribute(HTML.Attribute.SRC);
            if (href != null) {
                try {
                    URL tmp = new URL(baseURL, href);
                    if (tmp.getProtocol().equals("http") || tmp.getProtocol().equals("https")) {
                        s.addToURLs(tmp);
                    }
                } catch (MalformedURLException e) {
                        System.err.println("Failed adding frame url: " + href);
                }
            }
        }
    }

    @Override
    public void handleText(char[] text, int position) {
    }

}
