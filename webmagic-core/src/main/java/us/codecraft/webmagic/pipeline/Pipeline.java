package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

/**
 * Pipeline是抽取结束后，进行处理的部分，它主要用于抽取结果的保存
 * @author Administrator
 *
 */
public interface Pipeline {

	/**
	 * 抽取结果的处理
	 * @param resultItems
	 * @param task
	 */
    public void process(ResultItems resultItems, Task task);
}
