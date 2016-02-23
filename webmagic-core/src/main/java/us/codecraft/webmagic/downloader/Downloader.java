package us.codecraft.webmagic.downloader;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * Downloader is the part that downloads web pages and store in Page object. <br>
 * Downloader has {@link #setThread(int)} method because downloader is always the bottleneck of a crawler,
 * there are always some mechanisms such as pooling in downloader, and pool size is related to thread numbers.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */

/**
 * # 页面下载器
 * @author Administrator
 *
 */
public interface Downloader {

	/**
	 * 根据Request请求去下载一个页面，然后将页面保存在Page对象中
	 * @param request
	 * @param task
	 * @return page
	 */
    public Page download(Request request, Task task);

    /**
     * Tell the downloader how many threads the spider used.
     * @param threadNum number of threads
     */
    /**
     * 设置能给爬虫用于下载的线程数量
     * @param threadNum
     */
    public void setThread(int threadNum);
}
