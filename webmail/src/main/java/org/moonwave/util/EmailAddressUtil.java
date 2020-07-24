package org.moonwave.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.moonwave.jpa.model.pojo.EmailAddress;
import org.moonwave.util.comparator.EmailAddressComparator;

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
