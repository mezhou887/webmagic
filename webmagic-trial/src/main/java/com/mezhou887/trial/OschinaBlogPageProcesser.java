package com.mezhou887.trial;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class OschinaBlogPageProcesser implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
	    List<String> links = page.getHtml().links().regex("http://my\\.oschina\\.net/flashsword/blog/\\d+").all();
	    page.addTargetRequests(links);
	    page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1/text()").toString());
	    page.putField("content", page.getHtml().xpath("//div[@class='BlogContent']/tidyText()").toString());
	    page.putField("tags",page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());		
	}
	
	public static void main(String[] args) {
		Spider.create(new OschinaBlogPageProcesser()).addUrl("http://my.oschina.net/flashsword/blog/175349").thread(5).run();;
	}
	

}
