package org.moonwave.view.evaluation;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.EvaluationPerformanceBO;
import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.model.EvaluationPerformance;
import org.moonwave.view.AccessController;
import org.moonwave.view.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Performance List View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class PerformanceListView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(PerformanceListView.class);

    private List<EvaluationPerformance> data;
    private EvaluationPerformance current;
    private String selectedId;

    @ManagedProperty("#{accessController}")
    private AccessController accessController;

    @PostConstruct
    public void init() {
        data = new EvaluationPerformanceBO().findByUserId(super.getLoggedInUser().getId());
    }

    public List<EvaluationPerformance> getData() {
        return data;
    }

    public void setData(List<EvaluationPerformance> data) {
        this.data = data;
    }

    public EvaluationPerformance getCurrent() {
        return current;
    }

    public void setCurrent(EvaluationPerformance current) {
        this.current = current;
    }

    public String getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
        if ((this.selectedId != null) && !this.selectedId.isEmpty()) {
            GenericBO<EvaluationPerformance> bo = new GenericBO<>(EvaluationPerformance.class);
            this.current = bo.findById(Integer.parseInt(selectedId));
        }
    }

    public AccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(AccessController accessController) {
        this.accessController = accessController;
    }

    // ========================================================== ActionListener

    public void newPerformance() throws IOException {
        super.redirectTo("/evaluation/performance.xhtml");
    }

    // ========================================================= Private methods
}