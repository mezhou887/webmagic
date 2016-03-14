package com.mezhou887.quartz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mezhou887.entity.ZhihuEntity;

@Repository("zhihuMapper") 
public interface ZhihuMapper {
	
	public List<ZhihuEntity> queryAll();
	
}

