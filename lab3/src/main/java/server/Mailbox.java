package server;

import java.util.concurrent.*;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class Mailbox {
    ArrayList<Message> mails;
    int lastClientId = 0;
    ConcurrentMap<String, OutputStream> recievers;

    public Mailbox() {
        mails = new ArrayList<Message>();
        recievers = new ConcurrentHashMap<String, OutputStream>();
    }

    // Make this return an ArrayList<String> later on, hokay.
    public synchronized ArrayList<Message> getMessages() throws InterruptedException {
        while (mails.size() == 0) wait();
        ArrayList<Message> tmpList = mails;
        mails = new ArrayList<Message>();
        return tmpList;
    }

    public synchronized void putMessage(Message m) {
        // check so that id is in recievers
        mails.add(m);
        notifyAll();
    }

    public String registerReciever(OutputStream os) {
        lastClientId++;
        String id = String.valueOf(lastClientId);
        recievers.put(id, os); 
        return id;
    }

    public boolean removeReciever(String id) {
        return (recievers.remove(id) != null ? true : false);
    }
        
    public Set<Map.Entry<String, OutputStream>> getRecievers() {
        return recievers.entrySet();
    }
}
