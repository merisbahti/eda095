package ConcurrentAccess;

public class Main {

    public static void main(String[] args) {
        MailBox m = new MailBox();
        ReaderThread r = new ReaderThread(m); 
        r.start();
        for (int i = 0; i < 5; i++) (new WriterThread(m, i)).start();
    }

}
