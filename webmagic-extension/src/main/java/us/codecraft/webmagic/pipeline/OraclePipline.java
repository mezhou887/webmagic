package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.utils.RelationalDBPersistentBase;

/**
 * 将结果集保存到Oracle数据库中
 * @author Administrator
 *
 */
public class OraclePipline extends RelationalDBPersistentBase {
	
    private static final String driver = "oracle.jdbc.driver.OracleDriver";
    public static String USER = "mezhou887";
    public static String PASSWORD ="mezhou887"; 

    static {
        String oracle_user = System.getProperties().getProperty("oracle.user");
        if (oracle_user != null) {
        	USER = oracle_user;
        }
        
        String oracle_pwd = System.getProperties().getProperty("oracle.password");
        if (oracle_pwd != null) {
        	PASSWORD = oracle_pwd;
        }
    }    

    public OraclePipline(String connStr, String sql) {
    	initComponent(driver, connStr, USER, PASSWORD, sql);
    }

}
