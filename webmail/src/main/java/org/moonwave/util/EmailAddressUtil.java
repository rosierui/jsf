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

package org.moonwave.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.moonwave.jpa.model.pojo.EmailAddress;
import org.moonwave.util.comparator.EmailAddressComparator;

/**
 *
 * @author Jonathan Luo
 */
@SuppressWarnings("serial")
public class EmailAddressUtil implements java.io.Serializable {

    public static List<EmailAddress> getUniqueEmail(List<EmailAddress> list) {
        List<EmailAddress> retList = new ArrayList<EmailAddress>();
        Map<String, EmailAddress> map = new HashMap<String, EmailAddress>();
        for (EmailAddress ea : list) {
            if (!map.containsKey(ea.getEmail()))
                map.put(ea.getEmail(), ea);
        }
        Iterator<EmailAddress> it = map.values().iterator();
        while (it.hasNext()) {
            retList.add(it.next());
        }
//        Collections.sort(retList, new EmailAddressComparator());
        return retList;
    }
}
