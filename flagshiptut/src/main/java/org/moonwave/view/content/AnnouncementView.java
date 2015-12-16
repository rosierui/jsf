package org.moonwave.view.content;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.model.Announcement;
import org.moonwave.util.StringUtil;
import org.moonwave.view.BaseView;
import org.moonwave.view.file.FileUploadView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Announcement View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class AnnouncementView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(AnnouncementView.class);

    private String subject;
    private String body;
    private Boolean published;

    @ManagedProperty("#{fileUploadView}")
    private FileUploadView fileUploadView;

    @PostConstruct
    public void init() {
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

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public FileUploadView getFileUploadView() {
        return fileUploadView;
    }

    public void setFileUploadView(FileUploadView fileUploadView) {
        this.fileUploadView = fileUploadView;
    }

    public String save() {
        if (StringUtil.nullOrEmpty(subject)) {
            super.error("Subject is empty");
            return null;
        }
        try {
            Announcement a = new Announcement();
            a.setSubject(subject);
            a.setBody(body);
            a.setPublished(published);
            super.getBasebo().persist(a);

        } catch (Exception e) {
            super.error("Sorry, an error occurred, please contact your administrator");
            LOG.error("", e);
        }
        return null;
    }
}
