
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.moonwave.util.MessageUtil;
import org.moonwave.util.Timer;
import org.moonwave.util.mail.PostMan;
import org.moonwave.util.mail.SimpleMail;

/**
 *
 * @author Jonathan Luo
 */
public class MailThread extends Thread {

    protected static final Log log = LogFactory.getLog(MailThread.class);
    boolean bGroupMail = false;
    List<SimpleMail> mails;
    SimpleMail mail;
    List<String> attachments;
    List<File> fileList;

    public MailThread() {
        setDaemon(true);
    }

    public MailThread(boolean isDaemon) {
        setDaemon(isDaemon);
    }

    @Override
    public void run() {
        log.info("Mail thread starts");
        Timer timer = new Timer();
        try {
            if (bGroupMail)
                new PostMan().send(mails, attachments);
            else
                new PostMan().send(mail, attachments);
            // remove files
            for (File file : fileList) {
                file.delete();
            }
        } catch (Exception e) {
            log.error(e, e);
        } finally {
            if (log.isInfoEnabled())
                log.info(MessageUtil.info("Done MailThread run " + timer.toString()));
        }
    }

    public void send() {
        start();
    }

    /**
     * Sets group mail info
     *
     * @param mails
     * @param attachments
     */
    public void setGroupMailInfo(List<SimpleMail> mails, List<String> attachments, List<File> fileList) {
        bGroupMail = true;
        this.mails = mails;
        this.attachments = (attachments != null) ? attachments : new ArrayList<String>();
        this.fileList = (fileList != null) ? fileList : new ArrayList<File>();
    }

    /**
     * Sets individual mail info
     *
     * @param mail
     * @param attachments
     */
    public void setMailInfo(SimpleMail mail, List<String> attachments, List<File> fileList) {
        bGroupMail = false;
        this.mail = mail;
        this.attachments = (attachments != null) ? attachments : new ArrayList<String>();
        this.fileList = (fileList != null) ? fileList : new ArrayList<File>();
    }
}
