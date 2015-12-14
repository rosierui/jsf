package org.moonwave.util;

public class FileUtil {

    /**
     * Get filename from a full path
     * @param path
     * @return
     */
    public static String getFilename(String path) {
        if (path == null) {
            return null;
        }
        String strPath = path.substring(path.lastIndexOf("/") + 1, path.length());
        return strPath;
    }

}
