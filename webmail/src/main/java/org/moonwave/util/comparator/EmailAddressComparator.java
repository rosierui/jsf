package org.moonwave.util.comparator;

import java.util.Comparator;

import org.moonwave.jpa.model.pojo.EmailAddress;

/**
 * EmailAddress Comparator
 *
 * Sort list of EmailAddress by first name, last name and email
 *
 */
public class EmailAddressComparator implements Comparator<EmailAddress> {

    public int compare(EmailAddress obj1, EmailAddress obj2) {
        int ret = obj1.getFirstName().compareTo(obj2.getFirstName());
        if (ret == 0) {
            ret = obj1.getLastName().compareTo(obj2.getLastName());
            if (ret == 0)
                ret = obj1.getEmail().compareTo(obj2.getEmail());
        }
        return ret;
    }
}
