package ConcurrentAccess;

public class ReaderThread extends Thread {
    private MailBox m;

    public ReaderThread(MailBox m) {
        this.m = m;
    }

    public void run() {
        while (true) {
            try { 
                sleep((long) (Math.random()*100));
                System.out.println(m.getString());
            } catch (InterruptedException e) {
                System.err.println("Got stupid error when: sleeping reader");
            }
        }
    } 
}
