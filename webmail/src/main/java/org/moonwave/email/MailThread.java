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
                log.info("Done MailThread run " + timer.toString());
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
