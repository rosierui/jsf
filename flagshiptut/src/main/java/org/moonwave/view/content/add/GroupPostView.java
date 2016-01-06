package org.moonwave.view.content.add;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.bo.TutorGroupBO;
import org.moonwave.jpa.model.GroupPost;
import org.moonwave.jpa.model.TutorGroup;
import org.moonwave.util.StringUtil;
import org.moonwave.view.BaseView;
import org.moonwave.view.file.FileUploadView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GroupPost View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class GroupPostView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(GroupPostView.class);

    private String[] selectedTutorGroups; 
    private List<TutorGroup> tutorGroups;

    GroupPost current;

    @ManagedProperty("#{fileUploadView}")
    private FileUploadView fileUploadView;

    @PostConstruct
    public void init() {
        tutorGroups = new TutorGroupBO().findAllGroups();
        String selectedId = super.getParameter("selectedId");
        if (selectedId != null) { // edit
            GenericBO<GroupPost> bo = new GenericBO<>(GroupPost.class);
            current = bo.findById(Integer.valueOf(selectedId));
            List<TutorGroup> tgs = current.getTutorGroups(); 
            if (tgs.size() > 0) {
                selectedTutorGroups = new String[tgs.size()];
                for (int i = 0; i < tgs.size(); i++) {
                    selectedTutorGroups[i] = String.valueOf(tgs.get(i).getId());
                }
            }
            fileUploadView.loadUploads4GroupPost(current.getUser().getId(), current.getId());
        } else {
            current = new GroupPost();
        }
    }

    public String[] getSelectedTutorGroups() {
        return selectedTutorGroups;
    }

    public void setSelectedTutorGroups(String[] selectedTutorGroups) {
        this.selectedTutorGroups = selectedTutorGroups;
    }

    public List<TutorGroup> getTutorGroups() {
        return tutorGroups;
    }

    public GroupPost getCurrent() {
        return current;
    }

    public void setCurrent(GroupPost current) {
        this.current = current;
    }

    public FileUploadView getFileUploadView() {
        return fileUploadView;
    }

    public void setFileUploadView(FileUploadView fileUploadView) {
        this.fileUploadView = fileUploadView;
    }

    // ========================================================== ActionListener

    public void cancel() throws IOException {
        redirectToListView();
    }

    public void redirectToListView() throws IOException {
        super.redirectTo("/content/add/grouppostList.xhtml");
    }

    public void save() {
        if (StringUtil.nullOrEmpty(current.getSubject())) {
            super.error("Subject is empty");
            return;
        }
        try {
            if (current.getId() == null) {
                current.setUser(super.getLoggedInUser());
                super.getBasebo().persist(current);

                this.fileUploadView.update(super.getLoggedInUser().getId(), null, null, current.getId());
                this.fileUploadView.save(true);
            } else {
                super.getBasebo().update(current);
                this.fileUploadView.update(super.getLoggedInUser().getId(), null, null, current.getId());
                this.fileUploadView.save(false);
            }
            // save many-to-many relationship
            current.setTutorGroups(new ArrayList<TutorGroup>());
            List<TutorGroup> tgs = current.getTutorGroups();
            for (String tgid : selectedTutorGroups) {
                Short id = Short.parseShort(tgid);
                TutorGroup tg = new TutorGroupBO().findById(id);
                List<GroupPost> gps = new ArrayList<GroupPost>();
                gps.add(current); // only one entry, but need List format
                tg.setGroupPosts(gps);
                tgs.add(tg);
            }
            super.getBasebo().update(current);

            // show successful message and reset fields
            super.info("Data save successful");
            this.clearFields();
            this.fileUploadView.clearFields();
            redirectToListView();

        } catch (Exception e) {
            super.error("Sorry, an error occurred, please contact your administrator");
            LOG.error("", e);
        }
    }

    // ========================================================= Private methods

    private void clearFields() {
        selectedTutorGroups = null; 
    }
}
