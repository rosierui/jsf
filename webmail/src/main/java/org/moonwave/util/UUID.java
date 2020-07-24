package org.moonwave.util;

import java.rmi.server.UID;

import org.apache.commons.lang.StringUtils;

/**
 * Generate unique uuid
 * 
 */
public class UUID {

    /**
     * Returns a uuid based on java.rmi.server.UID
     * @return
     */
    public static String newId() {
        UID uid = new UID();
        String uuid = uid.toString();
        uuid = StringUtils.replace(uuid, ":", "");
        uuid = StringUtils.replace(uuid, "-", "");
        return uuid;
    }

    /**
     * Creates uuidb based on java.util.UUID, with '-' as separator
     * Sample output: "8e62e364-4296-4b53-9dc3-a894fa5c86a2"
     * @return
     */
    public static String randomUUID() {
        java.util.UUID uid = java.util.UUID.randomUUID();
        String uuid = uid.toString();
        uuid = StringUtils.replace(uuid, ":", "");
        uuid = StringUtils.replace(uuid, "-", "");
        return uuid;
    }

    /**
     * Creates uuidb based on java.util.UUID, with '-' as separator removed
     *
     * Sample output: "8e62e36442964b539dc3a894fa5c86a2"
     * @return
     */
    public static String randomUUIDStripped() {
        java.util.UUID uid = java.util.UUID.randomUUID();
        String uuid = uid.toString();
        uuid = StringUtils.replace(uuid, ":", "");
        uuid = StringUtils.replace(uuid, "-", "");
        return uuid;
    }
}
