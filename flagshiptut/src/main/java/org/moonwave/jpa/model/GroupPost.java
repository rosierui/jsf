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

@NamedQueries({
    @NamedQuery(name="GroupPost.findAll", query="SELECT g FROM GroupPost g order by g.createTime DESC"),
    @NamedQuery(name="GroupPost.findById", query="SELECT g FROM GroupPost g WHERE g.id = :id"),
    @NamedQuery(name="GroupPost.findByUserId", query="SELECT g FROM GroupPost g WHERE g.user.id = :userId order by g.createTime DESC"),
    @NamedQuery(name="GroupPost.findByUserIdInDateRange", query="SELECT g FROM GroupPost g WHERE g.user.id = :userId order by g.createTime DESC")
})

public class GroupPost implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String subject;

    @Lob
    private String body;

    private Boolean published;

    //bi-directional one-to-one association to User
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="create_time")
    private Timestamp createTime;

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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getPublished() {
        return this.published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getBodyFirst60() {
        String bodyFirst60 = null;
        if (this.body != null) {
            bodyFirst60 = this.body.substring(0, Math.min(60, body.length()));
            if (this.body.length() > 60) {
                bodyFirst60 += "...";
            }
        }
        return bodyFirst60;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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