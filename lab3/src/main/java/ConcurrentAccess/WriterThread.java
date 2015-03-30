package ConcurrentAccess;

public class WriterThread extends Thread {
    private MailBox m;
    private String id;

    public WriterThread(MailBox m, int id) {
        this.m = m;
        this.id = String.valueOf(id);
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try { 
                sleep((long) (Math.random()*100));
                m.setString("Thread number " + id + " passing its " + i + "'th message");
            } catch (InterruptedException e) {
                System.err.println("Got stupid error when: Thread number " + id + " was passing its " + i + "'th message");
            }
        }
    } 
}
