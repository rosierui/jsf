package org.moonwave.util;

/**
 * Converts a Throwable object's stack trace information to a printable string. 
 * Works with JDK1.4 or later.
 * 
 */
public class StackTrace {
    public static String toString(Throwable cause) {
        StringBuffer sb = new StringBuffer(1024);
        if (cause != null) {
            StackTraceElement[] items = cause.getStackTrace();
            if (items != null) {
                for (int i = 0;  i < items.length; i++) {
                    StackTraceElement item = items[i];
                    sb.append(item.toString());
                    sb.append(Constants.NEW_LINE);
                }
            }
        }
        return sb.toString();
    }
}
