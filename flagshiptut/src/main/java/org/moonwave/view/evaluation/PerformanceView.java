package org.moonwave.view.evaluation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.moonwave.jpa.bo.GenericBO;
import org.moonwave.jpa.model.EvaluationObjective;
import org.moonwave.jpa.model.EvaluationPerformance;
import org.moonwave.jpa.model.Semester;
import org.moonwave.jpa.model.Week;
import org.moonwave.view.BaseView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SelfEvaluation View
 *
 * @author moonwave
 *
 */
@ManagedBean
@ViewScoped
public class PerformanceView extends BaseView {

    private static final long serialVersionUID = 1L;
    static final Logger LOG = LoggerFactory.getLogger(PerformanceView.class);

    EvaluationPerformance evaluation;
    List<Semester> semesters;
    List<Week> weeks;

    @PostConstruct
    public void init() {
        evaluation =  new EvaluationPerformance();
        GenericBO<Semester> bo = new GenericBO<>(Semester.class);
        semesters = bo.findAll();

        GenericBO<Week> weekbo = new GenericBO<>(Week.class);
        weeks = weekbo.findAll();
    }

    public EvaluationPerformance getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(EvaluationPerformance evaluation) {
        this.evaluation = evaluation;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }

    public String save() {
        // validation
//        if (StringUtil.nullOrEmpty(subject)) {
//            super.error("Subject is empty");
//            return null;
//        }
        try {
            super.getBasebo().persist(evaluation);

        } catch (Exception e) {
            super.error("Sorry, an error occurred, please contact your administrator");
            LOG.error("", e);
        }
        return null;
    }

}
