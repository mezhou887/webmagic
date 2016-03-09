package com.mezhou887.utils;

import java.io.File;

public class FolderUtils {
	
	 //Ä¬ÈÏÂ·¾¶·Ö¸î·ûºÅ
	public static String PATH_SEPERATOR = "/";	
	
    public static void checkAndMakeDirecotry(String folderName) {
        int index = folderName.lastIndexOf(PATH_SEPERATOR);
        if (index > 0) {
            String path = folderName.substring(0, index);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public static String setPath(String path) {
        if (!path.endsWith(PATH_SEPERATOR)) {
            path += PATH_SEPERATOR;
        }
        return path;
    }

}
