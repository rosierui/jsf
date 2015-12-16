package org.moonwave.view.file;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.moonwave.jpa.model.Upload;
import org.moonwave.util.AppProperties;
import org.moonwave.view.BaseView;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "fileUploadView")
@ViewScoped
public class FileUploadView extends BaseView {

    private static final long serialVersionUID = 267212860965791357L;

    static final Logger LOG = LoggerFactory.getLogger(FileUploadView.class);

    private String description;
    private UploadedFile file;
    private List<Upload> uploads = new ArrayList<>();

    private boolean readyToSave;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    /**
     * Handle basic single file upload
     * 
     */
    public void upload() {
        if(file != null) {
            String uploadFolder = AppProperties.getInstance().getProperty(AppProperties.KEY_upload_folder);
            try {
                String filepath = uploadFolder + "/" + file.getFileName(); // + user-login-id + generated-mda5-userid
                file.write(filepath);
                super.info("Succesful", file.getFileName() + " is uploaded.");

                Upload upload = new Upload();
                upload.setDescription(description);
                upload.setFilepath(filepath);
                this.description = null;
                uploads.add(upload);
                // update ui and ready for save in db
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Handle advanced multiple files upload
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        UploadedFile file = event.getFile();
        try {
            file.write("/g01/srv/tomcat-latest/webapps/flagship-files/" + file.getFileName());
        } catch (Exception e) {
            System.out.println(e);
        }
//        RequestContext.getCurrentInstance().execute("PF('uploadJs').start()");
        System.out.println(event.getFile().getFileName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Update upload objects with additional info prior to save
     *
     * @param userId
     * @param tutorGroupId
     * @param announcementId
     * @param groupPostId
     */
    public void update(Integer userId, Short tutorGroupId, Integer announcementId, Integer groupPostId) {
        for (Upload upload: uploads) {
            upload.setUserId(userId);
            upload.setTutorGroupId(tutorGroupId);
            upload.setAnnouncementId(announcementId);
            upload.setGroupPostId(groupPostId);
        }
        readyToSave = true;
    }

    /**
     * Saves uploads to database
     *
     * Note: container view such as AnnouncementView must call update method
     * before calling this save method 
     */
    public void save() {
        if (!readyToSave) {
            super.error("Upload objects are not ready to save");
            LOG.error("Upload objects are not ready to save, container view failed to call update method");
        }
        super.getBasebo().persist(uploads);
        readyToSave = false;
    }
}
