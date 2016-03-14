package com.mezhou887.quartz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mezhou887.entity.ZhihuEntity;
import com.mezhou887.quartz.dao.ZhihuMapper;

@Service("zhihuService")
public class ZhihuService {
	
	@Autowired
	private ZhihuMapper zhihuMapper;

	public List<ZhihuEntity> getAllQuestions() {
		return zhihuMapper.queryAll();
	}	

}
