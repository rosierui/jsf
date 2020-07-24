package org.moonwave.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Generic Message
 * 
 */
@SuppressWarnings("serial")
public class Message implements Serializable {
    
    boolean logged;
    Calendar timeStamp = Calendar.getInstance();
    MessageType messageType;
    String message; // saves translated message
    String key;
    Object[] messageArgs;
    Throwable cause;    

    /**
     * Creates a non-lookup message. 
     * @param message a message to be set.
     */
    public Message(String message) {    	
        this.initFields(MessageType.INFO, null, null, null);
        this.message = message;
    }
    
    /**
     * Create a message without uuid filed. 
     * @param messageType a message type value.
     * @param messageArgs an array of replacement arguments.
     */
    public Message(int messageType, Object[] messageArgs) {
        this.initFields(messageType, "genericMsg", messageArgs, null);
    }
    
    /**
     * Create a message. This is equilevent to call 
     * new Message(messageType, key, messageArgs, true);  
     * @param messageType a message type value.
     * @param key message key to resouce property file.
     * @param messageArgs an array of replacement arguments.
     */
    public Message(int messageType, String key, Object[] messageArgs) {
        this.initFields(messageType, key, messageArgs, null);
    }
    
    /**
     * Create a message. This is equilevent to call 
     * new Message(messageType, key, messageArgs, cause, true);  
     * @param messageType a message type value.
     * @param key message key to resouce property file.
     * @param messageArgs an array of replacement arguments.
     * @param cause a throwable object.
     */
    public Message(int messageType, String key, Object[] messageArgs, Throwable cause) {
        this.initFields(messageType, key, messageArgs, cause);
    }

    /**
     * Initializes class fields. 
     * @param messageType a message type value.
     * @param key message key to resouce property file.
     * @param messageArgs an array of replacement arguments.
     * @param cause a throwable object.
     * @param bNewUuid true to request a new uuid to be created, otherwise a uuid
     * is included in the last argument of the messageArgs. Default value: true.
     */
    protected void initFields(int messageType, String key, Object[] messageArgs,
                              Throwable cause) {
        this.logged = false;
        this.messageType = new MessageType(messageType);
        this.key = key;
        this.messageArgs = messageArgs;
        this.cause = cause;
    }
    
    /**
     * Converts a message to a log message. This includes converting key, 
     * replacement arguments to a string, and converting a throwable cause to
     * printable string if applicable. 
     */
    public String getMessage() {
    	Locale locale = Locale.getDefault();
    	ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE_FILE, locale);
        if ((message == null) && (key != null)) {
        	if(messageArgs == null) {
        		message = resourceBundle.getString(key);
        	} else {
        		MessageFormat formatter = new MessageFormat("");
        		//formatter.setLocale(epmLocale);
        		formatter.applyPattern(resourceBundle.getString(key));
        		message = formatter.format(messageArgs);
        	}
        }
            
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }
    
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    
    public Object[] getMessageArgs() {
        return messageArgs;
    }
    public void setMessageArgs(Object[] messageArgs) {
        this.messageArgs = messageArgs;
    }
    
    public String getTimeStamp() {
        //return DateUtil.format(timeStamp);
        return "";
    }
    
    /**
     * This method serves as a place holder, does nothing.
     * @param timeStamp
     */
    public void setTimeStamp(String timeStampIgnored) {
        return;
    }
        
    /**
     * This method serves as a place holder, does nothing.
     * @param timeStamp
     */
    public void setUuid(String uuidIgnored) {
        return;
    }

    /**
     * Indicates whether some other object is "equal to" this one by compare
     * message or translated message from key and messageArgs.
     * @param obj the reference object with which to compare.
     * @return true if this object's message is the same as the obj argument's;
     *         false otherwise.
     */
    public boolean equals(Object obj) {
        boolean bRet = false;
        if ((obj != null) &&  (obj instanceof Message)) {
            String message1 = getMessage();
            String message2 = ((Message) obj).getMessage();
            if ((message1 != null) && (message2 != null))  
                bRet = message1.equals(message2);
        }
        return bRet;
    }
    
    /**
     * Returns a String representation of this message for view on screen. If
     * this message is logged, show the reference number.
     * Note: this method is NOT being called by any code directly.      
     */
    public String toString() {
        return getMessage();
    }
        
    /**
     * Converts a message to a log message. This includes converting key, 
     * replacement arguments to a string, and converting a throwable cause to
     * printable string if applicable. 
     */
    public String toLogMessage() {
        this.logged = true; // this message is logged by a logger
        StringBuffer sb = new StringBuffer(500);
        sb.append(ThreadUtil.getThreadId());
        sb.append(getMessage());
        // convert cause to a string if applicable
        if (cause != null) {
            sb.append(Constants.NEW_LINE);
            sb.append(cause.toString());
            sb.append(Constants.NEW_LINE);
            sb.append(StackTrace.toString(cause));
        }
        return sb.toString();
    }
}
