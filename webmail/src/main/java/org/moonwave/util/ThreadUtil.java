package org.moonwave.util;

/**
 * ThreadUtil
 *
 */
public class ThreadUtil {
    
    public static String getThreadId() {
        StringBuffer sb = new StringBuffer(30);
        sb.append("[thread Id: ").append(Thread.currentThread().getId()).append("] ");
        return sb.toString();
    }
}
