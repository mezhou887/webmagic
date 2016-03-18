package com.mezhou887.quartz.schedule;

import java.io.IOException;
import java.util.Date;
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
		IndexWriter writer = SpringContextHolder.getBean("indexWriter");
		try {
			deleteIndexes(writer);
			updateZhihuIndex(writer); //����֪��������
		} catch (IOException e) {
			logger.error("��������ʱ�����쳣: {}", e);
		}
	}
	
	//ɾ�����е�����
	private void deleteIndexes(IndexWriter writer) throws IOException {
		writer.deleteAll();	
		writer.commit();
	}
	
	private void updateZhihuIndex(IndexWriter writer) throws IOException {
		ZhihuService zhihuService = SpringContextHolder.getBean("zhihuService");
		List<ZhihuEntity> list = zhihuService.getAllQuestions();
		Document doc = null;
		logger.info("��ʼ����֪��������: {} ", new Date().toString());
		for(ZhihuEntity entity: list) {
				doc=new Document();  
				doc.add(new StringField("id", entity.getId(), Field.Store.NO));  
				doc.add(new StringField("url", entity.getUrl(), Field.Store.YES));  
				doc.add(new TextField("question", entity.getQuestion(), Field.Store.YES));  
				doc.add(new StringField("username", entity.getUsername(), Field.Store.YES));  
				doc.add(new TextField("answer", entity.getAnswer(), Field.Store.YES));
				Term term = new Term("id",entity.getId());
				writer.updateDocument(term, doc);				
		}
		logger.info("��������֪��������: {}, �������� {} ����", new Date().toString(), list.size());
		writer.commit();
	}

}
