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

/**
 * MessageType
 * 
 * @author Jonathan Luo
 */
public class MessageType implements java.io.Serializable {
    // This is similar to log4j log levels.
    public static final int FATAL = 0;
    public static final int ERROR = 1;
    public static final int WARN = 2;
    public static final int INFO = 3;
    public static final int CONFIRM = 4;
    public static final int DEBUG = 5;    
    
    private int messageType;    
    
    public MessageType(int messageType) {
        this.messageType = messageType;        
    }
    
    public int getMessageType() {
        return messageType;
    }
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
    
    public boolean isFatal() {
        return (messageType == FATAL);
    }
    public boolean isError() {
        return (messageType == ERROR);
    }
    public boolean isWarn() {
        return (messageType == WARN);
    }
    public boolean isInfo() {
        return (messageType == INFO);
    }
    public boolean isConfirm() {
        return (messageType == CONFIRM);
    }
    public boolean isDebug() {
        return (messageType == DEBUG);
    }
    
    /**
     * Returns a String representation for this message type.     
     */
    @Override
    public String toString() {
        if (messageType == FATAL)
            return "FATAL";
        else if (messageType == ERROR)
            return "ERROR";
        else if (messageType == WARN)
            return "WARN";
        else if (messageType == INFO)
            return "INFO";
        else if (messageType == CONFIRM)
            return "CONFIRM";
        else if (messageType == DEBUG)
            return "DEBUG";
        else
            return "";
    }    
}
