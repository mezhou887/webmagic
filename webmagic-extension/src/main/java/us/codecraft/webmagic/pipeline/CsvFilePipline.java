package us.codecraft.webmagic.pipeline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;
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

        try {
			CSVWriter csvWriter = new CSVWriter(new FileWriter(new File(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".csv")), ',');
			Object[] objects = resultItems.getAll().values().toArray();
			String[] strs=new String[objects.length];
	        for(int i =0; i<objects.length; i++) {
	        	strs[i] = objects[i].toString();
	        }
			csvWriter.writeNext(strs);  
			csvWriter.close();
		} catch (IOException e) {
        	e.printStackTrace();
            logger.error("write csv error", e);
		}
        
	}

}
