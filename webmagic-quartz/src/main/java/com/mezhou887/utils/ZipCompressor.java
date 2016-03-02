package com.mezhou887.utils;

import java.io.BufferedInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.util.zip.CRC32;  
import java.util.zip.CheckedOutputStream;  

import org.apache.tools.zip.ZipEntry;  
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  

public class ZipCompressor {
	private Logger logger = LoggerFactory.getLogger(getClass());
	static final int BUFFER = 8192;  
    private File zipFile;  
    
    public ZipCompressor(String pathName) {  
        zipFile = new File(pathName);  
    }  
  
    public void compressExe(String srcPathName) {  
        File file = new File(srcPathName);  
        if (!file.exists()){
        	throw new RuntimeException(srcPathName + "�����ڣ�");  
        }
        try {  
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);  
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());  
            ZipOutputStream out = new ZipOutputStream(cos);  
            String basedir = "";  
            compressByType(file, out, basedir);  
            out.close();  
        } catch (Exception e) { 
        	e.printStackTrace();
        	logger.error("ִ��ѹ������ʱ�����쳣:"+e);
            throw new RuntimeException(e);  
        }  
    }  
  
    private void compressByType(File file, ZipOutputStream out, String basedir) {  
        /* �ж���Ŀ¼�����ļ� */  
        if (file.isDirectory()) {  
        	logger.info("ѹ����" + basedir + file.getName());  
            this.compressDirectory(file, out, basedir);  
        } else {  
        	logger.info("ѹ����" + basedir + file.getName());  
            this.compressFile(file, out, basedir);  
        }  
    }  
  
    private void compressDirectory(File dir, ZipOutputStream out, String basedir) {  
        if (!dir.exists()){
        	 return;  
        }
           
        File[] files = dir.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            /* �ݹ� */  
        	compressByType(files[i], out, basedir + dir.getName() + "/");  
        }  
    }  
  
    private void compressFile(File file, ZipOutputStream out, String basedir) {  
        if (!file.exists()) {  
            return;  
        }  
        try {  
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));  
            ZipEntry entry = new ZipEntry(basedir + file.getName());  
            out.putNextEntry(entry);  
            int count;  
            byte data[] = new byte[BUFFER];  
            while ((count = bis.read(data, 0, BUFFER)) != -1) {  
                out.write(data, 0, count);  
            }  
            bis.close();  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
}
