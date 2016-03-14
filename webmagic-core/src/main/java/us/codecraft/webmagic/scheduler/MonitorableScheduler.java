package us.codecraft.webmagic.scheduler;

import us.codecraft.webmagic.Task;

/**
 * 监控爬虫请求
 * @author Administrator
 *
 */
public interface MonitorableScheduler extends Scheduler {

    public int getLeftRequestsCount(Task task);

    public int getTotalRequestsCount(Task task);

}