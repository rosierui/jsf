/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2015 Jonathan Luo
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package org.moonwave.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Timer
 *
 * @author Jonathan Luo
 */
public class Timer {

    private static final Log log = LogFactory.getLog(Timer.class);
    
    public static boolean START_NOW = true; 
    public static int SHOW_MINUTES = 1; 
    public static int SHOW_SECONDS = 2; 
    public static int SHOW_MILLISECONDS = 3;
        
    long lStartTime;
    long lEndTime;
    
	public Timer() {
        start(); // start it right away by default        
    }    
    public Timer(boolean startNow) {
        if (startNow)
        	start();
    }
    public void start() {
        lStartTime = System.currentTimeMillis();
    }
    public long getMilliSeconds() {
        lEndTime = System.currentTimeMillis();
        return (lEndTime - lStartTime);
    }
    public void showOnConsole(String msg) {
        showOnConsole(msg, SHOW_MILLISECONDS);     
    }
    public void showOnConsole(String msg, int timeFormat) {
        if (log.isInfoEnabled()) {
            if (timeFormat == SHOW_MINUTES) {
                System.out.println(MessageUtil.info(this.toMMSS()));
            }
            else if (timeFormat == SHOW_SECONDS)
                System.out.println(MessageUtil.info(this.toSeconds()));
            else {
                if (log.isDebugEnabled())
                    System.out.println(MessageUtil.info(this.toMilliseconds()));
            }
        }
    }    
    public String toMMSS() {
        StringBuffer sb = new StringBuffer(300);
        long seconds = getMilliSeconds() / 1000; 
        long minutes = seconds / 60;
        seconds -= minutes * 60;
        sb.append(" in ").append(minutes).append(":").append(seconds).append(" (mm:ss)");
        return sb.toString();        
    }
    public String toSeconds() {
        StringBuffer sb = new StringBuffer(300);
        sb.append(" in ").append(getMilliSeconds() / 1000).append(" seconds");
        return sb.toString();
    }
    public String toMilliseconds() {
        StringBuffer sb = new StringBuffer(300);
        sb.append(" in ").append(getMilliSeconds()).append(" milliseconds");
        return sb.toString();
    }        
    public String toString() {
        return toMilliseconds();
    }
}
