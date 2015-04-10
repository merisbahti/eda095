package server; 
public class Message {
    String sender;
    String message;

    public Message(String sender, String message) {
        this.sender  = sender;
        this.message = message;
        
    }

    public String getId() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    @Override public String toString() {
        return sender + ": " + message;
    }

}
