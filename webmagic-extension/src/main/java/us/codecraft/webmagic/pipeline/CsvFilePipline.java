package us.codecraft.webmagic.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.utils.FilePersistentBase;

/**
 * 将结果集以csv的形式保存到文件系统中
 * @author Administrator
 *
 */
public class CsvFilePipline extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public CsvFilePipline() {
        setPath(DEFAULT_STORE_PATH);
    }

    public CsvFilePipline(String path) {
        setPath(path);
    }
    
	@Override
	public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;		
	}

}
