package org.moonwave.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

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

    public static boolean deleteFile(String filepath) {
        boolean ret = false;
        try {
            File f = new File(filepath);
            ret = f.delete();
        } catch (Exception e) {
            LOG.error(StackTrace.toString(e));
        }
        return ret;
    }
}
