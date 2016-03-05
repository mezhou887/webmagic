package com.mezhou887.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mezhou887.entity.JobInfoEntity;

@Repository("quartzMapper") 
public interface QuartzMapper {
	
	public List<JobInfoEntity> selectAll();
	
}

