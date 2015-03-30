package MultiThreaded;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import util.ParserGetter;

public class LinkGetter extends HTMLEditorKit.ParserCallback {

    static String baseURL;
    private Storage s;

    public LinkGetter(String baseURL, Storage s) {
        super();
        this.s = s;
        this.baseURL    = baseURL;
    }

    @Override
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
        //System.out.println("Start: " + tag + " Position: " + position);
        if (tag == HTML.Tag.A) {
            String href = (String) attributes.getAttribute(HTML.Attribute.HREF);
            s.addToQueue(baseURL, href);
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
            s.addToQueue(baseURL, href);
            System.out.println("Frame: " + href);
        }
    }

    @Override
    public void handleText(char[] text, int position) {
    }

}
