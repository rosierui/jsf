package org.moonwave.util;

/**
 * MessageUtil
 * 
 */
public class MessageUtil {

    /**
     * Returns a internal error message with UUID and stack info.
     * 
     * @param cause a throwable object.
     * @return message created.
     */
    public static Message createInternalErrorWithUUID(Throwable cause) {
        Message message = new Message(MessageType.ERROR, "generic.error.internal", new Object[] {UUID.newId()}, cause);
        Messages.getMessages().add(message);
        return message;
    }
    
    /**
     * Returns a internal error message with UUID and stack info.
     * 
     * @param messageKey message key.
     * @param cause a throwable object.
     * @return message created.
     */
    public static Message createErrorMsgWithUUID(String messageKey, Throwable cause) {
        Message message = new Message(MessageType.ERROR, messageKey, new Object[] {UUID.newId()}, cause);
        Messages.getMessages().add(message);
        return message;
    }
    
    /**
     * Create a message. This is equivalent to call 
     * new Message(messageType, key, messageArgs, true);  
     * @param messageType a message type value.
     * @param key message key to resource property file.
     * @param messageArgs an array of replacement arguments.
     * @return message created.
     */
    public static Message newMessage(int messageType, String key, Object[] messageArgs) {
        Message message = new Message(messageType, key, messageArgs);
        Messages.getMessages().add(message);
        return message;
    }
    
    /**
     * Create a message. This is equivalent to call 
     * new Message(messageType, key, messageArgs, cause, true);  
     * @param messageType a message type value.
     * @param key message key to resource property file.
     * @param messageArgs an array of replacement arguments.
     * @param cause a throwable object.
     * @return message created.
     */
    public static Message newMessage(int messageType, String key, Object[] messageArgs, Throwable cause) {
        Message message = new Message(messageType, key, messageArgs, cause);
        Messages.getMessages().add(message);
        return message;
    }
        
    /**
     * Create an DEBUG type message. 
     * @param msg message to be logged.
     * @return message to be logged.
     */
    public static String debug(Object msg) {
        Message message = new Message(MessageType.DEBUG, new Object[] {msg});
        return message.toLogMessage();
    }
    
    /**
     * Create an INFO type message. 
     * @param msg message to be logged.
     * @return message to be logged.
     */
    public static String info(Object msg) {
        Message message = new Message(MessageType.INFO, new Object[] {msg});
        return message.toLogMessage();
    }
    
    /**
     * Create a WARN type message. 
     * @param msg message to be logged.
     * @return message to be logged.
     */
    public static String warn(Object msg) {
        Message message = new Message(MessageType.WARN, new Object[] {msg});
        return message.toLogMessage();
    }

    /**
     * Create an ERROR type message. 
     * @param msg message to be logged.
     * @return message to be logged.
     */
    public static String error(Object msg) {
        Message message = new Message(MessageType.ERROR, new Object[] {msg});
        return message.toLogMessage();
    }
    
    /**
     * Create an FATAL type message. 
     * @param msg message to be logged.
     * @return message to be logged.
     */
    public static String fatal(Object msg) {
        Message message = new Message(MessageType.FATAL, new Object[] {msg});
        return message.toLogMessage();
    }    
}
