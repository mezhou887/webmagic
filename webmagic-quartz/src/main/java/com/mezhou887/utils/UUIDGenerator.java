package com.mezhou887.utils;

import java.net.InetAddress;
import java.util.Date;

public class UUIDGenerator {
	
	private static String address = null;
	private static Long workerId = -1L;
	
	private static String getAddress() {
		if(address == null) {
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
				address = addr.getHostName().toString();
			} catch (Exception e) {
				address="unKnow";
			}
		}
		return address;
	}
	
	private static String getWorkId() {
		if(workerId.longValue()  == -1L) {
			char[] chars = getAddress().toCharArray();
			for(char c: chars) {
				workerId = workerId+ c;
			}
			workerId= workerId%15;
		}
		return address;
	}	
	
	public static String genUUID() {
		return getAddress()+new Date().getTime();
	}
	
	public static String genDBUUID() {
		return getWorkId() + new IdWorker(workerId).nextId();
		
	}

}
