package us.codecraft.webmagic.utils;

import java.io.File;

/**
 * 文件存储的基类
 * @author Administrator
 *
 */
public class FilePersistentBase {

    protected String path;

    public static String PATH_SEPERATOR = "/"; //默认路径分割符号
    
    public static String DEFAULT_STORE_PATH = "/data/webmagic"; //默认存储路径
    
    public static String DEFAULT_CHARSET="UTF-8"; //默认字符编码格式

    static {
        String property = System.getProperties().getProperty("file.separator");
        if (property != null) {
            PATH_SEPERATOR = property;
        }
        
        String store_path = System.getProperties().getProperty("store.path");
        if (store_path != null) {
        	DEFAULT_STORE_PATH = store_path;
        }
       
        String charset = System.getProperties().getProperty("charset");
        if (charset != null) {
        	DEFAULT_CHARSET = charset;
        }        
    }
    
    /**
     * 给路径后面自动加上分隔符
     * @param path
     */
    public void setPath(String path) {
        if (!path.endsWith(PATH_SEPERATOR)) {
            path += PATH_SEPERATOR;
        }
        this.path = path;
    }

    public File getFile(String fullName) {
        checkAndMakeParentDirecotry(fullName);
        return new File(fullName);
    }

    /**
     * 文件目录不存在的话，自动创建目录
     * @param fullName
     */
    public void checkAndMakeParentDirecotry(String fullName) {
        int index = fullName.lastIndexOf(PATH_SEPERATOR);
        if (index > 0) {
            String path = fullName.substring(0, index);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public String getPath() {
        return path;
    }
}
