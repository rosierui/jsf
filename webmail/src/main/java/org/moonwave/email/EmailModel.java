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

package org.moonwave.email;

import java.util.List;

import org.moonwave.jpa.model.pojo.EmailAddress;
import org.moonwave.util.mail.SimpleMail;

/**
 * Email Model
 * 
 * @author Jonathan Luo
 */
public class EmailModel extends SimpleMail implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    boolean groupMail;
    String sendTo;
    String showCc = "false"; // hidden show CC status
    String showBcc = "false"; // hidden show BCC status
    String showPostscript = "false";
    List<EmailAddress> emailAddressList;

    public boolean isGroupMail() {
        return groupMail;
    }
    public void setGroupMail(boolean groupMail) {
        this.groupMail = groupMail;
    }
    public String getSendTo() {
        return sendTo;
    }
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public List<EmailAddress> getEmailAddressList() {
        return emailAddressList;
    }
    public void setEmailAddressList(List<EmailAddress> emailAddressList) {
        this.emailAddressList = emailAddressList;
    }

    public String getShowCc() {
        return showCc;
    }
    public void setShowCc(String showCc) {
        this.showCc = showCc;
    }
    public void setShowCc(boolean showCc) {
        this.showCc = showCc ? "true" : "false";
    }

    public String getShowBcc() {
        return showBcc;
    }
    public void setShowBcc(String showBcc) {
        this.showBcc = showBcc;
    }
    public void setShowBcc(boolean showBcc) {
        this.showBcc = showBcc ? "true" : "false";
    }


    public String getShowPostscript() {
        return showPostscript;
    }
    public void setShowPostscript(String showPostscript) {
        this.showPostscript = showPostscript;
    }
    public void setShowPostscript(boolean showPostscript) {
        this.showPostscript = showPostscript ? "true" : "false";
    }

    public boolean isShowCc() {
        return showCc.equals("true");
    }
    public boolean isShowBcc() {
        return showBcc.equals("true");
    }
    public boolean isShowPostscript() {
        return showPostscript.equals("true");
    }

    public SimpleMail toSimpleMail() {
        SimpleMail sm = new SimpleMail();
        sm.setFrom(from);
        sm.setReplyTo(replyTo);
        sm.setTo(to);
        sm.setCc(cc);
        sm.setBcc(bcc);
        sm.setSubject(subject);
        sm.setBody(body);
        sm.setPostscript(postscript);
        sm.setHtmlMail(htmlMail);
        sm.setAppendPostscript(appendPostscript);
        return sm;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(from: ").append(from);
        sb.append(", replyTo: ").append(replyTo);
        sb.append(", sendTo: ").append(sendTo);
        sb.append(", to: ").append(to);
        sb.append(", showCc: ").append(showCc);
        sb.append(", showBcc: ").append(showBcc);
        sb.append(", cc: ").append(cc);
        sb.append(", bcc: ").append(bcc);
        sb.append(", subject: ").append(subject);
        sb.append(", body: ").append(body).append(")");
        return sb.toString();
    }
}
