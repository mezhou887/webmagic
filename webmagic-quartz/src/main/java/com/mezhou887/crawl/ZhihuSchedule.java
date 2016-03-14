package com.mezhou887.crawl;

import java.util.Date;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.mezhou887.utils.UUIDGenerator;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.MysqlPipline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

public class ZhihuSchedule extends QuartzJobBean implements PageProcessor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");
    
	 private static final int voteNum = 50;
	    
	public void process(Page page) {
        List<String> relativeUrl = page.getHtml().xpath("//li[@class='item clearfix']/div/a/@href").all(); 
        page.addTargetRequests(relativeUrl);
        relativeUrl = page.getHtml().xpath("//div[@id='zh-question-related-questions']//a[@class='question_link']/@href").all(); 
        page.addTargetRequests(relativeUrl);
        
        List<String> answers =  page.getHtml().xpath("//div[@id='zh-question-answer-wrap']/div").all(); 
        boolean exist = false;
        for(String answer:answers){
            String vote = new Html(answer).xpath("//div[@class='zm-votebar']//span[@class='count']/text()").toString();
            if(Integer.valueOf(vote) >= voteNum){
                page.putField("vote",vote);
                page.putField("url", page.getRequest().getUrl());
                page.putField("question", page.getHtml().xpath("//div[@id='zh-question-title']//h2/text()").toString());
                page.putField("userid", new Html(answer).xpath("//a[@class='author-link']/@href"));
                page.putField("username", new Html(answer).xpath("//a[@class='author-link']/text()"));
                page.putField("answer", new Html(answer).xpath("//div[@class='zh-summary summary clearfix']/text()"));
                page.putField("id",UUIDGenerator.genDBUUID());
                exist = true;
            }
        }
        if(!exist){
            page.setSkip(true);
        }		
	}

    public Site getSite() {
        return site;
    }

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("start zhihu:" + new Date().toString());
		
		String query = "insert into data_zhihu(id, url, question, username, userid, vote, answer, dealdate) values(:id, :url, :question, :username, :userid, :vote, :answer, now())";
		String connStr = "jdbc:mysql://localhost:3306/quartz?useUnicode=true&characterEncoding=utf-8";
		String startUrl = "http://www.zhihu.com/search?type=question&q=Áµ°®";
		
		Spider.create(new ZhihuSchedule()).addUrl(startUrl)
		.setScheduler(new QueueScheduler())
		.addPipeline(new MysqlPipline(connStr, query)).thread(10).run();
		
	}

	public static void main(String[] args) throws JobExecutionException {
		new ZhihuSchedule().executeInternal(null);
	}

}
