package com.mezhou887.trial;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.MysqlPipline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

public class ZhihuPageProcessor implements PageProcessor {
    
    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");
    
	 private static final int voteNum = 100;
	    
	@Override
	public void process(Page page) {
        List<String> relativeUrl = page.getHtml().xpath("//li[@class='item clearfix']/div/a/@href").all(); //得到所有问题的链接
        page.addTargetRequests(relativeUrl);
        relativeUrl = page.getHtml().xpath("//div[@id='zh-question-related-questions']//a[@class='question_link']/@href").all(); //得到所有问题的链接
        page.addTargetRequests(relativeUrl);
        
        List<String> answers =  page.getHtml().xpath("//div[@id='zh-question-answer-wrap']/div").all(); // 得到答案
        boolean exist = false;
        for(String answer:answers){
            String vote = new Html(answer).xpath("//div[@class='zm-votebar']//span[@class='count']/text()").toString();
            if(Integer.valueOf(vote) >= voteNum){
                page.putField("vote",vote);
                page.putField("url", page.getRequest().getUrl());
                page.putField("question", page.getHtml().xpath("//div[@id='zh-question-title']//h2/text()").toString());
                page.putField("userid", new Html(answer).xpath("//a[@class='author-link']/@href"));
                page.putField("username", new Html(answer).xpath("//a[@class='author-link']/text()"));
                exist = true;
            }
        }
        if(!exist){
            page.setSkip(true);
        }		
	}

    @Override
    public Site getSite() {
        return site;
    }
    
    public static void main(String[] args) throws Exception { 
    	String query = "insert into questions(url,question,username,userid,vote) values(:url, :question, :username, :userid, :vote)";
    	String connStr = "jdbc:mysql://localhost/zhihu";
    	
        Spider.create(new ZhihuPageProcessor()).addUrl("http://www.zhihu.com/search?type=question&q=爬虫")
        .setScheduler(new QueueScheduler())
        .addPipeline(new MysqlPipline(connStr, query)).thread(5).run();
    }

}
