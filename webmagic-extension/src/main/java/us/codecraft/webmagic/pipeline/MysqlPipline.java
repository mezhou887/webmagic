package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.utils.RelationalDBPersistentBase;

/**
 * 将结果集保存到Mysql数据库中
 * @author Administrator
 *
 */
public class MysqlPipline extends RelationalDBPersistentBase {

    private static final String driver = "com.mysql.jdbc.Driver";
    public static String USER = "mezhou887";
    public static String PASSWORD ="Admin1234#"; 

    static {
        String mysql_user = System.getProperties().getProperty("mysql.user");
        if (mysql_user != null) {
        	USER = mysql_user;
        }
        
        String mysql_pwd = System.getProperties().getProperty("mysql.password");
        if (mysql_pwd != null) {
        	PASSWORD = mysql_pwd;
        }
    }    
	
    public MysqlPipline(String connStr, String sql) {
    	initComponent(driver, connStr, USER, PASSWORD, sql);
    }	

}
