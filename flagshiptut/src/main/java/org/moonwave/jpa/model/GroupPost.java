package org.moonwave.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the group_post database table.
 * 
 */
@Entity
@Table(name="group_post")
@NamedQuery(name="GroupPost.findAll", query="SELECT g FROM GroupPost g")
public class GroupPost implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Lob
    private String body;

    @Column(name="create_time")
    private Timestamp createTime;

    private Boolean published;

    private String subject;

    @Column(name="update_time")
    private Timestamp updateTime;

    //bi-directional many-to-many association to TutorGroup
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="group_post_to_group"
        , joinColumns={
            @JoinColumn(name="group_post_id")
            }
        , inverseJoinColumns={
            @JoinColumn(name="tutor_group_id")
            }
        )
    private List<TutorGroup> tutorGroups;

    public GroupPost() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<TutorGroup> getTutorGroups() {
        return this.tutorGroups;
    }

    public void setTutorGroups(List<TutorGroup> tutorGroups) {
        this.tutorGroups = tutorGroups;
    }

    @Override
    public int hashCode() {
        return ((Integer)id).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || !(o instanceof GroupPost)) {
            return false;
        }
        GroupPost other = (GroupPost) o;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id= ").append(id);
        sb.append(",subject= ").append(subject);
        sb.append(",published= ").append(published);
        return sb.toString();
    }
}