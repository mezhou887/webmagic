package com.mezhou887.utils;

import java.io.File;  

import org.apache.tools.ant.Project;  
import org.apache.tools.ant.taskdefs.Zip;  
import org.apache.tools.ant.types.FileSet;  
  
public class ZipCompressorByAnt {  
  
    private File zipFile;  
  
    public ZipCompressorByAnt(String finalFile) {  
        zipFile = new File(finalFile);  
    }  
      
    public void compressExe(String srcPathName) {  
        File srcdir = new File(srcPathName);  
        if (!srcdir.exists()){
            throw new RuntimeException(srcPathName + "²»´æÔÚ£¡");  
        } 
          
        Project prj = new Project();  
        Zip zip = new Zip();  
        zip.setProject(prj);  
        zip.setDestFile(zipFile);  
        FileSet fileSet = new FileSet();  
        fileSet.setProject(prj);  
        fileSet.setDir(srcdir);  
        zip.addFileset(fileSet);  
        zip.execute();  
    }  
} 