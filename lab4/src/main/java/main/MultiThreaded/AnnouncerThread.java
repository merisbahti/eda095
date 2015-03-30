package MultiThreaded;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import util.ParserGetter;

public class AnnouncerThread extends Thread {
    Storage s;
    public AnnouncerThread (Storage s) {
        this.s = s;
    }

    public void run() {
        while (true) {
            try {
                sleep(10000l);
            } catch (InterruptedException e) {
                System.out.println("Announcer interrupted...?");
            }
            System.out.println("q size: " + s.getQueueSize());
            System.out.println("v size: " + s.getVisitedSize());
            System.out.println("emails: " + s.getEmails().size());
        }
    }


}
