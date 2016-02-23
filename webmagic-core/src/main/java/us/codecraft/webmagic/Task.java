package us.codecraft.webmagic;

/**
 * 定义一项任务的接口 
 */
public interface Task {

	/**
	 * 唯一性标示一项任务
	 * @return
	 */
    public String getUUID();

    /**
     * 得到Site
     * @return
     */
    public Site getSite();

}
