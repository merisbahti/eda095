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
    
    public synchronized void addToQueue(String baseURL, String href) {
            try {
                URL tmp = (!href.startsWith("http")) ? new URL(new URL(baseURL), href) : new URL(href);
                if (tmp.getProtocol().equals("http") || tmp.getProtocol().equals("https")) {
                    if (!visited.contains(tmp) && !queue.contains(tmp)) queue.add(tmp);
                } else if (tmp.getProtocol().equals("mailto")) {
                    emails.add(tmp.toString());
                } else {
                    System.out.println("Not handled protocol: " + tmp);
                }
            } catch (Exception e) {
                //System.out.println("URL Exception prolly:" + e.getMessage());
            }
    }
    public synchronized URL getFromQueue() {
        try {
            URL url = queue.remove(0);
            visited.add(url);
            return url;
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
