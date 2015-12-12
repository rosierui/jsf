package org.moonwave.view.schedule;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.moonwave.jpa.bo.ScheduleBO;
import org.moonwave.jpa.bo.UserBO;
import org.moonwave.jpa.model.Schedule;
import org.moonwave.jpa.model.User;
import org.moonwave.util.StringUtil;
import org.moonwave.view.BaseView;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class ScheduleView extends BaseView {

    private static final long serialVersionUID = -8417089268399596277L;
    static final Logger LOG = LoggerFactory.getLogger(ScheduleView.class);

    private ScheduleModel eventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();

    private List<User> students;
    private List<User> tutors;
    private String tutorId;
    private String studentId;
    private boolean tutorSetup = false;

    @PostConstruct
    public void init() {
        Map<String, String> rqm = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (rqm.get("tutorSetup") != null && rqm.get("tutorSetup").equals("true")) {
            tutorSetup = true;
        }

        tutors = new UserBO().findAllTutors();
        students = new UserBO().findAllStudents();

        eventModel = new DefaultScheduleModel();
        
        List<Schedule> schedules = new ScheduleBO().getAllRoles();
        for (Schedule s : schedules) {
            eventModel.addEvent(scheduleToEvent(s));
        }
//        eventModel.addEvent(new DefaultScheduleEvent("Champions - today", previousDay8Pm(), previousDay11Pm(), "fc-foday"));
//        eventModel.addEvent(new DefaultScheduleEvent("Birthday - filled", today1Pm(), today6Pm(), "filled"));
//        eventModel.addEvent(new DefaultScheduleEvent("Breakfast - available", nextDay9Am(), nextDay11Am(), "available"));
//        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));

    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutor) {
        this.tutorId = tutor;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String student) {
        this.studentId = student;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public List<User> getTutors() {
        return tutors;
    }

    public void setTutors(List<User> tutors) {
        this.tutors = tutors;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public boolean isTutorSetup() {
        return tutorSetup;
    }

    public void setTutorSetup(boolean tutorSetup) {
        this.tutorSetup = tutorSetup;
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Select an existing event
     *
     * @param selectEvent
     */
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    /**
     * Select a date that does not have an event associated with it.
     * This allows a user to create a new event
     * 
     * @param selectEvent
     */
    public void onDateSelect(SelectEvent selectEvent) {
        Date date = (Date) selectEvent.getObject();
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onViewChange(SelectEvent selectEvent) {
        Object obj = selectEvent.getObject();
    }

    public void onTodaySelect(SelectEvent selectEvent) {
        Object obj = selectEvent.getObject();
        Object tt = obj;
    }

    /**
     * Add a new event or update existing event
     *
     * @param actionEvent
     */
    public void saveEvent(ActionEvent actionEvent) {
        if (!validate(event)) {
            return;
        }
        String sid = event.getId();
        if (event.getId() == null) {
            eventModel.addEvent(event);
            Schedule s = this.packageData(event);
            super.getBasebo().persist(s);
        }
        else
            eventModel.updateEvent(event);

        event = new DefaultScheduleEvent();
    }

    private boolean validate(ScheduleEvent event) {
        boolean ret = true;
        if (StringUtil.nullOrEmpty(tutorId)) {
            super.error("Tutor is empty");
            ret = false;
        }
        return ret;
    }

    private Schedule packageData(ScheduleEvent event) {
        Schedule s = new Schedule();
        s.setTutorId(Integer.parseInt(tutorId));
        s.setUserId(Integer.parseInt(studentId));
        s.setEvent(event.getTitle());
        s.setAllDayEvent(event.isAllDay());
        s.setStartTime(event.getStartDate());
        s.setEndTime(event.getEndDate());
        s.setCreateTime(super.getSqlTimestamp());
        return s;
    }

    private ScheduleEvent scheduleToEvent(Schedule s) {
        DefaultScheduleEvent e = new DefaultScheduleEvent();
        e.setAllDay(s.getAllDayEvent());
        e.setEditable(false);
        e.setTitle(s.getEvent());
        e.setStartDate(s.getStartTime());
        e.setEndDate(s.getEndTime());
        e.setStyleClass("filled");
        return e;
    }
}
