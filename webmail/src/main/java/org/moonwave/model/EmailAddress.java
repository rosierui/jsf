
/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * Copyright (C) 2015 Jonathan Luo
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 */

package org.moonwave.model;

/**
 *
 * @author Jonathan Luo
 */
public class EmailAddress implements java.io.Serializable {

    String firstName;
    String lastName;
    String email;

    public EmailAddress() {
    }

    public EmailAddress(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getName() {
        return firstName + " " + lastName;
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
        StringBuilder sb = new StringBuilder(30);
        sb.append("first name: ").append(firstName);
        sb.append(", last name: ").append(lastName);
        sb.append(", email: ").append(email);
        return sb.toString();
    }
}
