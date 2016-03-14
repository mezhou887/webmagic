package com.mezhou887.quartz.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("listIndexed")
	public @ResponseBody List<String> listIndexed() throws CorruptIndexException, IOException {
		IndexSearcher indexSearcher = getSearcher();
		int size=indexWriter.maxDoc();
		Document doc=null;
		List<String> list=new ArrayList<String>();
		for(int i=0;i<size;i++){
			doc=indexSearcher.doc(i);
			String str = doc.get("test");
			list.add(str);
		}
		return list;
	}
	
	@RequestMapping("indexFiles")
	public @ResponseBody List<String> indexFiles() throws IOException{
		Directory d=indexWriter.getDirectory();
		String[] fs=d.listAll();
		return Arrays.asList(fs);
	}
	
	@RequestMapping("deleteIndexes")
	public @ResponseBody Boolean deleteIndexes(){
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
	
	
	@RequestMapping("search")
	public @ResponseBody List<ZhihuEntity> search(String text) throws IOException {
		List<ZhihuEntity> list=new ArrayList<ZhihuEntity>();
		IndexSearcher indexSearcher = getSearcher();
		
		return list;
	}
	
	private IndexSearcher getSearcher() throws IOException{
		return new IndexSearcher(DirectoryReader.open(indexWriter.getDirectory()));
	}

}
