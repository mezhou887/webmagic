package us.codecraft.webmagic;

/**
 * 标示不同任务的接口
 * @author Administrator
 *
 */
public interface Task {

	/**
	 * 唯一性标示一项任务的
	 */
    public String getUUID();

    /**
     * 得到任务的网站 
     */
    public Site getSite();

}
