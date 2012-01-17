/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
import java.util.*;

/**
 *
 * @author Drew
 */
public class Time {
    public double getCurrentTime() {
        // To get current time with this method
        // we must unfortunately create a new calendar
        // Since java uses a garbage collector can't call a delete
        calendar = new GregorianCalendar();
        
        double retValue = getCurMS() / 1000.0;
        retValue += getCurSecond();
        retValue += getCurMinute() * 60;
        retValue += getCurHour() * 60 * 60;
        return retValue;
    }
    
    private int getCurDay() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }
    
    private int getCurHour() {
        return calendar.get(Calendar.HOUR);
    }
    
    private int getCurMinute() {
        return calendar.get(Calendar.MINUTE);
    }
    
    private int getCurSecond() {
        return calendar.get(Calendar.SECOND);
    }
    
    private int getCurMS() {
        return calendar.get(Calendar.MILLISECOND);
    }
    
    private Calendar calendar;
}
