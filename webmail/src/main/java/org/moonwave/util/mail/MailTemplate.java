package org.moonwave.util.mail;

/**
 * Mail template
 *
 */
@SuppressWarnings("serial")
public class MailTemplate implements java.io.Serializable {

	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subjectPrefix;
	private String subjectSuffix;
	private String bodyPrefix;
	private String bodySuffix;
    private String bodyPs;

    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
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

    public String getSubjectPrefix() {
        return subjectPrefix;
    }
    public void setSubjectPrefix(String subjectPrefix) {
        this.subjectPrefix = subjectPrefix;
    }

    public String getSubjectSuffix() {
        return subjectSuffix;
    }
    public void setSubjectSuffix(String subjectSuffix) {
        this.subjectSuffix = subjectSuffix;
    }

    public String getBodyPrefix() {
        return bodyPrefix;
    }
    public void setBodyPrefix(String bodyPrefix) {
        this.bodyPrefix = bodyPrefix;
    }

    public String getBodySuffix() {
        return bodySuffix;
    }
    public void setBodySuffix(String bodySuffix) {
        this.bodySuffix = bodySuffix;
    }

    public String getBodyPs() {
        return bodyPs;
    }
    public void setBodyPs(String bodyPs) {
        this.bodyPs = bodyPs;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("(from = ").append(from);
        sb.append(", to = ").append(to);
        sb.append(", cc = ").append(cc);
        sb.append(", bcc = ").append(bcc);
        sb.append(", subjectPrefix = ").append(subjectPrefix);
        sb.append(", subjectSuffix = ").append(subjectSuffix);
        sb.append(", bodyPrefix = ").append(bodyPrefix);
        sb.append(", bodySuffix = ").append(bodySuffix);
        sb.append(", bodyPs = ").append(bodyPs).append(")");
        return sb.toString();
    }
}
