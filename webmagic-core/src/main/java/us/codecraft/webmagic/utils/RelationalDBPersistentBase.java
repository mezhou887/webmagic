package us.codecraft.webmagic.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 数据库存储的基类
 * @author Administrator
 *
 */
public class RelationalDBPersistentBase  implements Pipeline {
	
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Connection conn = null;
    private String query;
    
    public void initComponent(String driver, String connStr, String user, String password, String query) {
    	this.query = query;
    	try {
			Class.forName(driver);
			conn = DriverManager.getConnection(connStr, user, password);
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException: ", e);            
            throw new RuntimeException("ClassNotFoundException: ", e);
		} catch (SQLException e) {
			logger.error("SQLException: ", e);            
			throw new RuntimeException("SQLException: ", e);
		}
    }

	@Override
	public void process(ResultItems resultItems, Task task) {
		try {
			NamedParameterStatement pst = new NamedParameterStatement(conn, query);
			for(Map.Entry<String, Object> entry: resultItems.getAll().entrySet()) {
				pst.setObject(entry.getKey(), entry.getValue().toString());
			}
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
        	e.printStackTrace();
            logger.error("write to db error", e);
		}
	}
	
}
