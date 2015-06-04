package org.moonwave.jpa.model.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * A POJO used as pseudo entity to retrieve result from native query
 * This entity does not map to any physical table
 *
 * Minimum annotations are @Enity and @Id
 * 
 * @Id can be used on any field of your choice, @Id field can have duplicate keys but not null
 * 
 * Optional @Column annotation can be used to map queried column names to field names
 * @Column(name="column_name") where column_name is case in-sensitive
 * 
 */

@Entity
public class EmailAddress {

    @Id
    protected Long id;

    protected String firstName;
    protected String lastName;
    protected String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress)) return false;

        final EmailAddress obj = (EmailAddress) o;

        if ((email == null) || (obj.email == null))
            return false;
        return (email.equals(obj.email));
    }

    @Override
    public int hashCode() {
        return (email != null) ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id);
        sb.append(", firstName: ").append(firstName);
        sb.append(", lastName: ").append(lastName);
        sb.append(", email: ").append(email);

        return sb.toString();
    }
}
