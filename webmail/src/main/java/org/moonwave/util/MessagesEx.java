package org.moonwave.util;

/**
 * Extended <code>Messages</code> wrapper
 * 
 */
public class MessagesEx extends Messages implements java.io.Serializable {
        
   private static ThreadLocal tlMessagesEx = new ThreadLocal();
   
   // --------------------------------------------------------- Static methods
       
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
       Messages messages = (Messages) tlMessagesEx.get();
       if (messages == null) {
           messages = new MessagesEx();
           setMessages(messages);
       }
       return messages; 
       
   }
   
   /**
    * @param messages The messages to set.
    * Note: this method may not needed.
    */
   public static void setMessages(Messages messages) {
       tlMessagesEx.set(messages);
   }   
}
