package org.moonwave.view.content.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.model.GroupPost;
import org.moonwave.view.BaseView;
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
    private String selectedId;
    private GroupPost current;

    @PostConstruct
    public void init() {

        // get a list of group posts
        GenericBO<GroupPost> bo = new GenericBO<>(GroupPost.class);
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
        }
    }
}