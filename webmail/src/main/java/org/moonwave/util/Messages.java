package org.moonwave.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class which holds <code>Message</code>
 * 
 */
public class Messages implements java.io.Serializable {
        
    ArrayList messagesList = new ArrayList();
    
    private static ThreadLocal tlMessages = new ThreadLocal();
    // -------------------------------------------------- Public static methods
        
    /**
     * Clears all messages recorded by this object.
     */
    public static void clearAll() {
        getMessages().clear();
    }
    
    /**
     * @return Returns the messages.
     */
    public static Messages getMessages() {
        Messages messages = (Messages) tlMessages.get();
        if (messages == null) {
            messages = new Messages();
            setMessages(messages);
        }
        return messages; 
    }
    
    /**
     * @param messages The messages to set.
     * Note: this method may not needed.
     */
    public static void setMessages(Messages messages) {
        tlMessages.set(messages);
    }
        
    // --------------------------------------------------------- Public methods
    
    /**
     * Appends a new message to the list if the new message is not a duplicate
     * message. Does nothing otherwise. 
     * @param message a message to add.
     */
    public void add(Message message) {
        if (!isDuplicate(message))
            messagesList.add(message);
    }
    
    /**
     * Returns the list of all recorded messages;
     */
    public List getMessagesList() {
        return messagesList;        
    }    
    
    /**
     * Returns recorded message by index.
     * @param index the index.
     * @return recorded message if found, null otherwise.
     */
    public Message get(int index) {
        Message message = null;
        try {
            message = (Message) messagesList.get(index);            
        } catch (Exception e) {
            // ignore it, most likely is the out of index exception
        }
        return message;
    }

    /**
     * Returns true if there are no messages recorded in this collection, or false otherwise.
    */
    public boolean isEmpty() {
        return (size() == 0) ? true : false;
    }

    /**
     * Returns the number of messages recorded.
    */
    public int size() {
        return messagesList.size();
    }
    
    /**
     * Returns a String representation of this this Messages' contents.     
     */
    public String toString() {
        StringBuffer sb = new StringBuffer(200);
        for (int i = 0; i < size(); i++) {
            Message message = (Message) messagesList.get(i);
            sb.append(message.toString());
            sb.append("\n");
        }
        return (String) sb.toString();
    }
    
    // ------------------------------------------------------ Protected methods    
    /**
     * Clears all messages recorded by this object.
     */
    protected void  clear() {
        messagesList.clear();
    }
    
    /**
     * Checks whether a message is a duplicate message in the list.
     * @param message a message to check.
     * @return true if message already exists; false otherwise.
     */
    protected boolean isDuplicate(Message message) {
        boolean bRet = false;
        for (int i = 0; i < messagesList.size(); i++) {
            Message msg = (Message) messagesList.get(i);
            if (msg.equals(message)) {
                bRet = true;
                break;
            }            
        }        
        return bRet;
    }
}
