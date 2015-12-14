package org.moonwave.view.file;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class FileUploadView {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            try {
                file.write("/g01/srv/tomcat-latest/webapps/flagship-files/" + file.getFileName());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

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
}
