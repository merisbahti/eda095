package ass4;

public class TimeServerMain {
    public static void main(String[] args) {
        int port = (args.length == 0 ? 30000 : Integer.parseInt(args[0]));
        System.out.println("Starting time server main");
        (new TimeServerUDPThread(port)).start();
        (new MCServerOffer(port)).start();
    }

}
