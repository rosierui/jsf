package org.moonwave.view.content.add;

import java.io.IOException;
import java.util.ArrayList;
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
import org.moonwave.util.StringUtil;
import org.moonwave.view.AccessController;
import org.moonwave.view.BaseView;
import org.moonwave.view.file.FileDownloadView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GroupPost List View
 *
 * Modified from GroupPostsView
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class GroupPostListView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(GroupPostListView.class);

    private List<GroupPost> data;
    private GroupPost current;
    private String selectedId;

    @ManagedProperty("#{accessController}")
    private AccessController accessController;

    @ManagedProperty("#{fileDownloadView}")
    private FileDownloadView fileDownloadView;

    @PostConstruct
    public void init() {
        data = loadAndFilterData();
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

    // ======================================================= Property Listener

    public String getSelectedId() {
        return selectedId;
    }

    /**
    *
    * @param selectedId selected GroupPost id
    */
    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
        if ((this.selectedId != null) && !this.selectedId.isEmpty()) {
            GenericBO<GroupPost> bo = new GenericBO<>(GroupPost.class);
            this.current = bo.findById(Integer.parseInt(selectedId));
            fileDownloadView.setDownloads(new UploadBO().findByUserGroupPost(current.getUser().getId(), current.getId()));
        }
    }

    // ========================================================== ActionListener

    public void newGroupPost() throws IOException {
        super.redirectTo("/content/add/grouppost.xhtml");
    }

    /**
    *
    * @param selectedId selected GroupPost id
    */
    public void togglePublish(String selectedId) {
        if (!StringUtil.nullOrEmpty(selectedId)) {
            GenericBO<GroupPost> bo = new GenericBO<>(GroupPost.class);
            this.current = bo.findById(Integer.parseInt(selectedId));
            this.current.setPublished(!this.current.getPublished());
            super.getBasebo().update(current);

            data = loadAndFilterData();
        }
    }

    public void edit(String selectedId) throws IOException {
        super.redirectTo("/content/add/grouppost.xhtml?selectedId=" + selectedId);
    }


    public void delete(String selectedId) throws IOException {
        // perform deletion
        super.redirectTo("/content/add/grouppostList.xhtml");
    }

    // ========================================================== Helper methods

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

    // ========================================================= Private methods

    /**
     * For tutor, teacher, only show their own posts
     * For supervisor, show all posts 
     *
     */
    private List <GroupPost> loadAndFilterData() {
        GenericBO<GroupPost> bo = new GenericBO<>(GroupPost.class);
        List <GroupPost> list = bo.findAllInDateRange(); 
        List <GroupPost> ret = new ArrayList<GroupPost>();
        boolean isSupervisor = accessController.isSupervisor();
        boolean isTutorOrTeacher = accessController.isTutor() || accessController.isTeacher();
        for (GroupPost a : list) {
            if (isSupervisor) {
                ret.add(a);
            } else if (isTutorOrTeacher) {
                if (accessController.isSelf(a.getUser())) {
                    ret.add(a);
                }
            }
        }
        return ret;
    }
}