package ConcurrentAccess;

public class MailBox {
    private String s = null;
    
    public synchronized void setString(String s) {
       while (this.s != null) 
           try { 
               wait(); 
            } catch (InterruptedException e) { 
                System.err.println("Caught interruptedexception lol"); 
            }
       this.s = s; 
       notifyAll();
    }

    public synchronized String getString() {
        while (s == null) 
            try { 
                wait(); 
            } catch (InterruptedException e) { 
                System.err.println("Caught interruptedexception lol"); 
            }
        String tmpS = s;
        s = null;
        notifyAll();
        return tmpS;
    }

}
