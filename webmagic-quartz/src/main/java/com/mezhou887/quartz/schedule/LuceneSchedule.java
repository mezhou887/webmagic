package com.mezhou887.quartz.schedule;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.entity.ZhihuEntity;
import com.mezhou887.quartz.service.ZhihuService;
import com.mezhou887.utils.SpringContextHolder;

public class LuceneSchedule extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(getClass());		
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			deleteIndexes();
			updateZhihuIndex();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteIndexes() throws IOException {
		IndexWriter writer = SpringContextHolder.getBean("indexWriter");
		writer.deleteAll();		
	}
	
	private void updateZhihuIndex() throws IOException {
		ZhihuService zhihuService = SpringContextHolder.getBean("zhihuService");
		IndexWriter writer = SpringContextHolder.getBean("indexWriter");
		List<ZhihuEntity> list = zhihuService.getAllQuestions();
		Document doc = null;
		int i =0;
		for(ZhihuEntity entity: list) {
				doc=new Document();  
				doc.add(new StringField("id", entity.getId(), Field.Store.YES));  
				doc.add(new StringField("url", entity.getUrl(), Field.Store.YES));  
				doc.add(new TextField("question", entity.getQuestion(), Field.Store.YES));  
				doc.add(new StringField("username", entity.getUsername(), Field.Store.YES));  
				doc.add(new TextField("answer", entity.getAnswer(), Field.Store.YES));
				Term term = new Term("id",entity.getId());
				writer.updateDocument(term, doc);				
				logger.info(i+","+list.size() +", "+entity.getUrl());
				i++;
		}
			
		writer.commit();
		
	}

}
