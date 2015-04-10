package ass2;

import java.util.*;
import java.text.*;

public class TimeServer2{
    public static void main(String[] args) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            String cmd = scan.nextLine(); 
            Date now = new Date();
            switch (cmd) {
                case "both": 
                    System.out.println(
                        DateFormat.
                        getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, new Locale("sv", "SE")).
                        format(now)
                    );
                    break;
                case "time": 
                    System.out.println(DateFormat.getTimeInstance(DateFormat.LONG, new Locale("sv", "SE")).format(now));
                    break;
                case "date": 
                    System.out.println(DateFormat.getDateInstance(DateFormat.LONG, new Locale("sv", "SE")).format(now));
                    break;
                default:     
                    System.err.println("Specify either time, date, or both as arguments");
            }
        }
    }
}
