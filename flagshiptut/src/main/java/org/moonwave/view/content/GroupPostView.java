package org.moonwave.view.content;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.TutorGroupBO;
import org.moonwave.jpa.model.GroupPost;
import org.moonwave.jpa.model.TutorGroup;
import org.moonwave.util.StringUtil;
import org.moonwave.view.BaseView;
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

    private String subject;
    private String body;
    private Boolean published;

    @PostConstruct
    public void init() {
        tutorGroups = new TutorGroupBO().getAllGroups();
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

    public void save() {
        if (StringUtil.nullOrEmpty(subject)) {
            super.error("Subject is empty");
            return;
        }
        try {
            GroupPost gp = new GroupPost();
            gp.setSubject(subject);
            gp.setBody(body);
            gp.setPublished(published);
            super.getBasebo().persist(gp);

            // save many-to-many relationship
            gp.setTutorGroups(new ArrayList<TutorGroup>());
            List<TutorGroup> tgs = gp.getTutorGroups();
            for (String tgid : selectedTutorGroups) {
                Short id = Short.parseShort(tgid);
                TutorGroup tg = new TutorGroupBO().findById(id);
                List<GroupPost> gps = new ArrayList<GroupPost>();
                gps.add(gp);
                tg.setGroupPosts(gps);
                tgs.add(tg);
            }
            super.getBasebo().update(gp);

        } catch (Exception e) {
            super.error("Sorry, an error occurred, please contact your administrator");
            LOG.error("", e);
        }
    }
}
