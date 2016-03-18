package com.mezhou887.quartz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.mezhou887.entity.ZhihuEntity;

@Controller
@RequestMapping("/lucene")
public class LuceneController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	private IndexWriter indexWriter;

	@Autowired
	private IKAnalyzer analyzer;
	
	@RequestMapping("indexFiles")
	public @ResponseBody List<String> indexFiles() throws IOException {
		Directory d=indexWriter.getDirectory();
		String[] fs=d.listAll();
		return Arrays.asList(fs);
	}
	
	@RequestMapping("deleteIndexes")
	public @ResponseBody Boolean deleteIndexes() {
		try {
			indexWriter.deleteAll();
			indexWriter.commit();
		    return Boolean.TRUE;
		} catch (IOException e) {
			logger.info("É¾³ýË÷ÒýÊ§°Ü: " , e);
			try {
				indexWriter.rollback();
			} catch (IOException e1) {
				logger.info("»Ø¹öÉ¾³ýË÷ÒýÊ§°Ü: " , e1);
			}
			return Boolean.FALSE;
		}
	}
	
	
	@RequestMapping("searchZhihu")
	public @ResponseBody List<ZhihuEntity> searchZhihu(String text, @RequestParam(value="num", defaultValue="10") int num) throws IOException, ParseException {
		List<ZhihuEntity> list=new ArrayList<ZhihuEntity>();
		IndexSearcher indexSearcher = getSearcher();
		QueryParser parser = new MultiFieldQueryParser(new String[]{"question", "username", "answer"}, analyzer);
		Query query = parser.parse(text);
		TopDocs topdsocs = indexSearcher.search(query, num);
		ScoreDoc[] scoredocs = topdsocs.scoreDocs;
        Document doc;

        for(int i=0;i<scoredocs.length;i++) { 
        	ZhihuEntity entity = new ZhihuEntity();
        	int docId=scoredocs[i].doc;
        	doc=indexSearcher.doc(docId);        
        	entity.setUrl(doc.get("url"));
        	entity.setQuestion(doc.get("question"));
        	entity.setAnswer(doc.get("answer"));
        	entity.setUsername(doc.get("username"));
        	list.add(entity);
        }
		return list;
	}
	
	private IndexSearcher getSearcher() throws IOException{
		return new IndexSearcher(DirectoryReader.open(indexWriter.getDirectory()));
	}

}
