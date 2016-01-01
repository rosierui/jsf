package org.moonwave.view.content.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.model.Announcement;
import org.moonwave.view.AccessController;
import org.moonwave.view.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Announcements View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class AnnouncementsView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(MyGroupsView.class);

    private List<Announcement> data;
    private Announcement current;
    private String selectedId;

    @ManagedProperty("#{accessController}")
    private AccessController accessController;

    @PostConstruct
    public void init() {
        data = loadAndFilterData();
    }

    public List<Announcement> getData() {
        return data;
    }

    public void setData(List<Announcement> data) {
        this.data = data;
    }

    public Announcement getCurrent() {
        return current;
    }

    public void setCurrent(Announcement current) {
        this.current = current;
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

    public AccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(AccessController accessController) {
        this.accessController = accessController;
    }

    // ========================================================= Private methods

    /**
     * only show published announcements
     */
    private List <Announcement> loadAndFilterData() {
        GenericBO<Announcement> bo = new GenericBO<>(Announcement.class);
        List <Announcement> list = bo.findAll(); 
        List <Announcement> ret = new ArrayList<Announcement>();
        for (Announcement a : list) {
            if (a.getPublished()) {
                ret.add(a);
            }
        }
        return ret;
    }
}