/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Debug;

/**
 *
 * @author Drew
 */
public class out {
    public static Boolean outputDebug = true;
    public static void println(boolean x) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(char x) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(char[] x) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(double x) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(float x ) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(int x ) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(long x ) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(Object x ) {
        if ( outputDebug )
            System.out.println(x);
    }
    public static void println(String x ) {
        if ( outputDebug )
            System.out.println(x);
    }
}
