package MultiThreaded;

import java.util.*;
import java.net.*;

public class Storage {
    private ArrayList<URL> queue;
    private Set<String> emails;
    private ArrayList<URL> visited;

    public Storage() {
        queue   = new ArrayList<URL>(); 
        emails  = new HashSet<String>(); 
        visited = new ArrayList<URL>(); 
    }
    
    public void addToMails(String href) {
        synchronized (emails) {
            emails.add(href);
        }
    }

    public void addToURLs(URL u) {
        synchronized (visited){
            synchronized (queue) {
                if (!visited.contains(u) && !queue.contains(u))
                queue.add(u);
            }
        }
    }

    public URL getFromQueue() {
        try {
            synchronized (visited) {
                synchronized (queue) {
                    URL url = queue.remove(0);
                    visited.add(url);
                    return url;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    //getQueueSize

    public synchronized int getQueueSize() {
        return queue.size();
    }

    //getVisitedSize

    public synchronized int getVisitedSize() {
        return visited.size();
    }

    //getEmails
    public synchronized Set<String> getEmails() {
        return emails;
    }
}
