package org.moonwave.view.content.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.bo.UploadBO;
import org.moonwave.jpa.model.GroupPost;
import org.moonwave.jpa.model.TutorGroup;
import org.moonwave.jpa.model.Upload;
import org.moonwave.view.AccessController;
import org.moonwave.view.BaseView;
import org.moonwave.view.file.FileDownloadView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GroupPosts View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class GroupPostsView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(GroupPostsView.class);

    private List<GroupPost> data;
    private GroupPost current;
    private String selectedId;

    @ManagedProperty("#{accessController}")
    private AccessController accessController;

    @ManagedProperty("#{fileDownloadView}")
    private FileDownloadView fileDownloadView;

    @PostConstruct
    public void init() {

        // get a list of group posts
        GenericBO<GroupPost> bo = new GenericBO<>(GroupPost.class);
        // find published group pots and those belong to the user's group
        List<TutorGroup> userGroups = super.getLoggedInUser().getTutorGroups();
        // create GroupPostBO for veried query
        data = bo.findAll();
    }

    public GroupPost getCurrent() {
        return current;
    }

    public void setCurrent(GroupPost current) {
        this.current = current;
    }

    public List<GroupPost> getData() {
        return data;
    }

    public void setGroupposts(List<GroupPost> data) {
        this.data = data;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedPostId) {
        this.selectedId = selectedPostId;
        if ((this.selectedId != null) && !this.selectedId.isEmpty()) {
            GenericBO<GroupPost> bo = new GenericBO<>(GroupPost.class);
            this.current = bo.findById(Integer.parseInt(selectedPostId));
            fileDownloadView.setDownloads(new UploadBO().findByUserGroupPost(current.getUser().getId(), current.getId()));
        }
    }

    public AccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(AccessController accessController) {
        this.accessController = accessController;
    }

    public FileDownloadView getFileDownloadView() {
        return fileDownloadView;
    }

    public void setFileDownloadView(FileDownloadView fileDownloadView) {
        this.fileDownloadView = fileDownloadView;
    }

    public String hasAttachments(GroupPost a) {
        List<Upload> list = new UploadBO().findByUserGroupPost(a.getUser().getId(), a.getId());
        return list.isEmpty() ? "No" : "Yes";
    }

    public String visibleTo() {
        StringBuilder sb = new StringBuilder();
        if (current != null) {
            List<TutorGroup> tgs = current.getTutorGroups(); 
            if (tgs != null) {
                for (int i = 0; i < tgs.size(); i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(tgs.get(i).getAlias());
                }
            }
        }
        return sb.toString();
    }
}