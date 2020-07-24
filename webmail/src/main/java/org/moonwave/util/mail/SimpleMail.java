package org.moonwave.util.mail;

/**
 * A simple mail that does not have attachments
 *
 */
@SuppressWarnings("serial")
public class SimpleMail implements java.io.Serializable {

	protected String from;
	protected String replyTo;
	protected String to;
	protected String cc;
	protected String bcc;
	protected String subject;
	protected String body;
	protected String postscript;
    protected boolean htmlMail; // true for html mail, false text mail
    protected boolean appendPostscript; // true to apply postscript, false not apply postscript

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getReplyTo() {
        return replyTo;
    }
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public String getPostscript() {
        return postscript;
    }
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    public boolean isHtmlMail() {
        return htmlMail;
    }
    public void setHtmlMail(boolean htmlMail) {
        this.htmlMail = htmlMail;
    }

    public boolean isAppendPostscript() {
        return appendPostscript;
    }
    public void setAppendPostscript(boolean appendPostscript) {
        this.appendPostscript = appendPostscript;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(from: ").append(from);
        sb.append(", replyTo: ").append(replyTo);
        sb.append(", to: ").append(to);
        sb.append(", cc: ").append(cc);
        sb.append(", bcc: ").append(bcc);
        sb.append(", subject: ").append(subject);
        sb.append(", body: ").append(body);
        sb.append(", postscript: ").append(postscript);
        sb.append(", htmlMail: ").append(htmlMail).append(")");
        sb.append(", appendPostscript: ").append(appendPostscript).append(")");
        return sb.toString();
    }
}
