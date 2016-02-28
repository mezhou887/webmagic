package us.codecraft.webmagic.pipeline;

import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * 将结果集保存到MongoDB数据库中
 * @author Administrator
 *
 */
public class MongoDBPipline implements Pipeline {
	
	private MongoClient mongoClient = null;
	private MongoDatabase db = null;
	private MongoCollection<Document> dbCollection = null;
	
    public void initComponent(String dbName, String collectionName) {
    	mongoClient = new MongoClient();
    	db = mongoClient.getDatabase(dbName);
    	dbCollection = db.getCollection(collectionName);
    }

	@Override
	public void process(ResultItems resultItems, Task task) {
		Document document = new Document();
		for(Map.Entry<String, Object> entry: resultItems.getAll().entrySet()) {
			document.append(entry.getKey(), entry.getValue());
		}
		dbCollection.insertOne(document);
	}

}
