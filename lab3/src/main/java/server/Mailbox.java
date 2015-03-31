package server;

import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.*;

public class Mailbox {
    ArrayList<Message> mails;
    int lastClientId = 0;
    ConcurrentMap<String, Socket> recievers;

    public Mailbox() {
        mails = new ArrayList<Message>();
        recievers = new ConcurrentHashMap<String, Socket>();
    }

    public ArrayList<Message> getMessages() throws InterruptedException {
        synchronized (mails) {
            while (mails.size() == 0) mails.wait();
            ArrayList<Message> tmpList = mails;
            mails = new ArrayList<Message>();
            return tmpList;
        }
    }

    public void putMessage(Message m) throws InterruptedException {
        // check so that id is in recievers
        synchronized (mails) {
            mails.add(m);
            mails.notifyAll();
        }
    }

    public String registerReciever(Socket os) throws InterruptedException {
        synchronized (recievers) {
            lastClientId++;
            String id = String.valueOf(lastClientId);
            recievers.put(id, os); 
            return id;
        }
    }

    public boolean removeReciever(String id) {
        synchronized (recievers) {
            return recievers.remove(id) != null;
        }
    }
        
    public Set<Map.Entry<String, Socket>> getRecievers() {
        return recievers.entrySet();
    }
}
