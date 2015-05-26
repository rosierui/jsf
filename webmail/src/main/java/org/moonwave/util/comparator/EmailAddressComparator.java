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
package org.moonwave.util.comparator;

import java.util.Comparator;
import org.moonwave.model.EmailAddress;

/**
 * EmailAddress Comparator
 *
 * Sort list of EmailAddress by first name, last name and email
 *
 * @author Jonathan Luo
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
