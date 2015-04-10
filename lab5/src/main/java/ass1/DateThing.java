package ass1;

import java.util.*;
import java.text.*;

public class DateThing {
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(now); 
        String dateTimeString = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, new Locale("sv", "SE")).format(now);
        String dateString = DateFormat.getTimeInstance(DateFormat.LONG, new Locale("sv", "SE")).format(now);
        String timeString = DateFormat.getDateInstance(DateFormat.LONG, new Locale("sv", "SE")).format(now);
        System.out.println("DT:   " + dateTimeString);
        System.out.println("Date: " + dateString);
        System.out.println("Time: " + timeString);
    }
}
