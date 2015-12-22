package org.moonwave.view.content;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.model.Announcement;
import org.moonwave.view.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Announcement List View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class AnnouncementListView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(AnnouncementListView.class);

    private List<Announcement> data;
    private String selectedId;
    private Announcement current;

    @PostConstruct
    public void init() {

        // get a list of group posts
        GenericBO<Announcement> bo = new GenericBO<>(Announcement.class);
        data = bo.findAll();
    }

    public Announcement getCurrent() {
        return current;
    }

    public void setCurrent(Announcement current) {
        this.current = current;
    }

    public List<Announcement> getData() {
        return data;
    }

    public void setData(List<Announcement> data) {
        this.data = data;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
        if ((this.selectedId != null) && !this.selectedId.isEmpty()) {
            GenericBO<Announcement> bo = new GenericBO<>(Announcement.class);
            this.current = bo.findById(Integer.parseInt(selectedId));
        }
    }
}