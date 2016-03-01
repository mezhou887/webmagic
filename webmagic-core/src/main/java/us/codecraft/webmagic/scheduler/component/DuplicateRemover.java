package us.codecraft.webmagic.scheduler.component;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;

/**
 * Remove duplicate requests.
 * @author code4crafer@gmail.com
 * @since 0.5.1
 */
public interface DuplicateRemover {

	/**
	 * 检查请求是否重复
	 * @param request
	 * @param task
	 * @return boolean
	 */
    public boolean isDuplicate(Request request, Task task);

    /**
     * Reset duplicate check.
     * @param task task
     */
    public void resetDuplicateCheck(Task task);

    /**
     * Get TotalRequestsCount for monitor.
     * @param task task
     * @return number of total request
     */
    public int getTotalRequestsCount(Task task);

}
