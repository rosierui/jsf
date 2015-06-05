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
 * Extended <code>Messages</code> wrapper
 * 
 * @author Jonathan Luo
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