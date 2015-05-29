package org.moonwave.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.moonwave.email.EmailModel;
import org.moonwave.email.MailThread;
import org.moonwave.model.EmailAddress;
import org.moonwave.util.AppProperties;
import org.moonwave.util.FileUtil;
import org.moonwave.util.MailProperties;
import org.moonwave.util.mail.SimpleMail;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class EmailView {

    /*
     * file upload see single.xhtml, multiple.xhtml
     * 
     * FileUploadView.java
     * use input stream as attachment
     * 
     * 
     */
    EmailModel emailModel;
    private UploadedFile file;

    // application properties
    String webServerHome;
    String userId;
    File uploadFolder;
    File outgoingFolder;
    boolean normalAttachments = false;

    @PostConstruct
    public void init() {
        MailProperties mp = MailProperties.getInstance();
        emailModel = new EmailModel();
        emailModel.setFrom(mp.getFrom());
        emailModel.setTo(mp.getTo());
        emailModel.setCc(mp.getCc());
        emailModel.setBcc(mp.getBcc());
        emailModel.setHtmlMail(true);

        AppProperties ap = AppProperties.getInstance();
        webServerHome = ap.getProperty(AppProperties.KEY_WEB_SERVER_HOME);
        uploadFolder = FileUtil.createDirectory(ap.getProperty(AppProperties.KEY_UPLOAD_FOLDER));
        outgoingFolder = FileUtil.createDirectory(webServerHome + "/webapps/" + ap.getProperty(AppProperties.KEY_OUTGOING_FOLDER));
        normalAttachments = ap.getProperty(AppProperties.KEY_NORMAL_ATTACHMENTS).equals("true") ? true : false;
    }

    // ================================================================= Actions
    public String send2() throws IOException {
        if (true)
            return null;
        return "List";
    }

    public void send(ActionEvent actionEvent) {
        performSendMailAction(emailModel);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Message sent"));
        return;
    }


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

//    public void upload() {
//        if(file != null) {
//            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        file = event.getFile();
        try {
                InputStream initialStream = file.getInputstream();
                String filename = file.getFileName();
                String fileType = file.getContentType();

                File targetFile = new File(getUploadFolder().getPath() + "/" + filename);
                FileUtils.copyInputStreamToFile(initialStream, targetFile);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ================================================= Public property methods
    public EmailModel getEmailModel() {
        return emailModel;
    }

    public void setEmailModel(EmailModel emailModel) {
        this.emailModel = emailModel;
    }

    // ========================================================= Private methods
    private void performSendMailAction(EmailModel model) {
        // send attachments
        List<String> attachments = new ArrayList<String>(); // list of full path name

        List<File> fileList = Arrays.asList(getUploadFolder().listFiles());
//        long id = loggedInUser.getId();
//        IschoolUser sender = loggedInUser;
        String attachementLink = null;
        for (File file : fileList) {
            if (normalAttachments) // send as normal attachments
                attachments.add(file.getPath());
            else { // send attachments inside message body as downloadable link
                try {
                    String fromFile = file.getPath();
                    String filename = new String(file.getName());
                    filename = filename.replaceAll(" ", "_"); // replace spaces with '_'
                    String toFile = getOutgoingFolder().toString() + "/" + filename;
                    FileUtil.copyfile(fromFile, toFile);
                    attachementLink = webServerHome + "/webapps/" + getOutgoingFolder().getName() + "/" + userId + "/" + filename;
                    StringBuilder sb = new StringBuilder(100);
                    sb.append("<a href=\"").append(attachementLink);
                    sb.append("\">").append(filename).append("</a>");
                    attachments.add(sb.toString());
                } catch (Exception e) {
//                    log.error(e, e);
                }
            }
        }

        // TODO - append to after group mail address
        if (model.getFrom() != null)
            model.setFrom(model.getFrom().replaceAll(";", ","));
        if (model.getTo() != null)
            model.setTo(model.getTo().replaceAll(";", ","));
        if (model.getCc() != null)
            model.setCc(model.getCc().replaceAll(";", ","));
        if (model.getBcc() != null)
            model.setBcc(model.getBcc().replaceAll(";", ","));
        if (model.isAppendPostscript())
            model.setBody(model.getBody() + "\n\n" + model.getPostscript());
        // add inline attachments
        if (!normalAttachments) {
            if (attachments.size() > 0)
                model.setBody(model.getBody() + "\n\n" + "\n\n" + "<b>Attachments:</b>");
            for (String attachment : attachments) {
                model.setBody(model.getBody() + "\n" + attachment);
            }
            attachments.clear();
        }
        if (model.isHtmlMail()) { // replace \n to <br>
            model.setBody(model.getBody().replaceAll("\n", "<br>"));
            model.setBody(model.getBody().replaceAll("\r\n", "<br>"));
        }

        if (model.getSendTo() != null) { // handle group mails
//            String sendTo = model.getSendTo();
//            List<EmailAddress> list = getGroupEmail(sendTo);
//            list = EmailAddressUtil.getUniqueEmail(list);
//            model.setEmailAddressList(list);
//            sendGroupMails(model, attachments, fileList); // send group mails w/ attachments
        } else
            sendMail(model, attachments, fileList); // send individual mail w/ attachments
        // log email activity
//        logEmailActivity(model);
//        setResponsePage(new StatusPage("<h1>Your email has been sent, thank you!</h1>"));
    }

    /**
     * Sends individual mail with attachments if any
     *
     * @param target
     * @param model
     */
    private void sendMail(SimpleMail mail,
                          List<String> attachments,
                          List<File> fileList) {
        MailThread mailThread = new MailThread();
        mailThread.setMailInfo(mail, attachments, fileList);
        mailThread.send();
    }

    /**
     * Check whether the file already exists, and if so, try to delete it.
     *
     * @param newFile
     *            the file to check
     */
    private void checkFileExists(File newFile)
    {
        if (newFile.exists()) {
            // Try to delete the file
            if (!newFile.delete()) {
                throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
            }
        }
    }

    private File getUploadFolder() {
        if (uploadFolder == null) {
//            Folder parentFolder = ((IschoolApp)Application.get()).getUploadRootFolder();
//            String subFolder = loggedInUser.getId().toString();
//            uploadFolder = new Folder(parentFolder.getAbsolutePath(), subFolder);
            uploadFolder.mkdirs();
        }
        return uploadFolder;
    }

    private File getOutgoingFolder() {
        if (outgoingFolder == null) {
//            Folder parentFolder = ((IschoolApp)Application.get()).getOutgoingRootFolder();
//            String subFolder = loggedInUser.getId().toString();
//            outgoingFolder = new Folder(parentFolder.getAbsolutePath(), subFolder);
            outgoingFolder.mkdirs();
        }
        return outgoingFolder;
    }

//    private String getOutgoingSubfolder() {
//        if (outgoingSubfolder == null) {
//            outgoingSubfolder = loggedInUser.getId().toString();
//            outgoingSubfolder = "outgoing/" + outgoingSubfolder;
//        }
//        return outgoingSubfolder;
//    }

    private long calculateTotalUploadFileSize() {
        long totalSizeKB = 0;
        File[] files = getUploadFolder().listFiles();
        for (File file: files) {
            totalSizeKB += file.length(); // number of bytes
        }
        totalSizeKB /= 1024;
        return totalSizeKB;
    }

}