package com.github.ipcam.utils;

import java.io.File;

/**
 * FileUtils
 *
 * @author echils
 */
public class FileUtils {


    /**
     * Create parent directory of the path
     *
     * @param path the local path
     */
    public static void createParentDirectory(String path) {
        File file = new File(path);
        createParentDirectory(file);
    }


    /**
     * Create parent directory of the file
     *
     * @param file the local file
     */
    public static void createParentDirectory(File file) {
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }


    /**
     * Get the suffix of the picture
     *
     * @param path the path of the picture
     * @return the suffix of the picture
     */
    public static String getFileSuffix(String path) {
        if (path != null && !path.equals("") && path.contains(".")) {
            return path.substring((path.lastIndexOf(".")));
        }
        return null;
    }

}
