package com.mezhou887.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mezhou887.bean.JobInfoBean;

@Repository("quartzDAO") 
public class QuartzDAO {
	private String DATE_PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private DataSource dataSource;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 查找所有的定时任务
	 * 
	 * @return
	 */
	public List<JobInfoBean> getAllQuartzJobs() {
		System.out.println(TASK_LIST_SQL);
		List<JobInfoBean> list = new ArrayList<JobInfoBean>();
		
		Connection con =  null;
		Statement smt = null;
		ResultSet rst = null;
			try {
				con =dataSource.getConnection();
				smt = con.createStatement();
				
				rst = smt.executeQuery(TASK_LIST_SQL);
				
				while(rst.next()){
					JobInfoBean bean = convertInfo(rst);
					list.add(bean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
	}
	
	private JobInfoBean convertInfo(ResultSet rst) throws SQLException{
		JobInfoBean bean = new JobInfoBean();
		bean.setTriggerGroup(rst.getString("trigger_group"));
		bean.setTriggerName(rst.getString("trigger_name"));
		bean.setJobGroup(rst.getString("job_group"));
		bean.setJobName(rst.getString("job_name"));
		bean.setJobClassName(rst.getString("job_class_name"));
		bean.setJobDescription(rst.getString("description"));
		bean.setCronExpression(rst.getString("cron_expression"));
		bean.setTriggerState(rst.getString("trigger_state"));
		
		bean.setNextFireTime(getTime(rst.getObject("next_fire_time")));
		bean.setPrevFireTime(getTime(rst.getObject("prev_fire_time")));
		bean.setStartTime(getTime(rst.getObject("start_time")));
		bean.setEndTime(getTime(rst.getObject("end_time")));
		return bean;
	}
	
	private String getTime(Object obj){
		if(obj == null)
			return "";
		String time = obj.toString();
		if("0".equals(time))
			return time;
		if(time.length() != 13)
			return time;
		
		return fmtLongTimeToString(time);
	}	
	
	private String fmtLongTimeToString(String times){
		SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN_FORMAT);
		Date date = new Date();
		date.setTime(Long.valueOf(times));
		return sf.format(date);
	}	
	

	private final static String TASK_LIST_SQL = 
			"SELECT triggers.trigger_name, " +
			"       triggers.trigger_group, " + 
			"       triggers.job_name, " + 
			"       triggers.job_group, " + 
			"       job_d.description, " + 
			"       job_d.job_class_name, " + 
			"       cron_triggers.cron_expression, " + 
			"		triggers.trigger_state,"+
			"       triggers.start_time, " + 
			"       triggers.end_time, " + 
			"       triggers.next_fire_time, " + 
			"       triggers.prev_fire_time " + 
			"  FROM qrtz_triggers triggers " + 
			" INNER join qrtz_cron_triggers cron_triggers " + 
			"    ON (cron_triggers.trigger_name = triggers.trigger_name AND " + 
			"       cron_triggers.trigger_group = triggers.trigger_group) " + 
			" INNER JOIN qrtz_job_details job_d " + 
			"    ON (job_d.job_name = triggers.job_name AND " + 
			"       job_d.job_group = triggers.job_group)";
}

