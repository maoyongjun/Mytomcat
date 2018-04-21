package org.foxconn.tomcat.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class FileUtil {
	static Logger logger = Logger.getLogger(FileUtil.class);
	public static List<String> getSubListPath(String string) throws Exception {
		List<String> list = new ArrayList<String>();
		listSubFile(string,list);
		return list;
		
	}
	@Test
	public void test() throws Exception{
		List<String> list = new ArrayList<String>();
		listSubFile("D:/SeagateWebserviceOld/target/webservice/WEB-INF/lib",list);
		for(String path:list){
			logger.info("path:"+path);
		}
	}
	private static  void listSubFile(String dir ,List<String> fileList) throws Exception{
		File file = new File(dir);
//		logger.info("path:"+dir);
		String childPath ="";
		if(file.exists()&&file.isDirectory()){
			File[] childFiles = file.listFiles();
			for(File childFile:childFiles){
				childPath = childFile.getAbsolutePath();
				if(childFile.isFile()){
					String filePath = childFile.getPath();
					fileList.add(filePath);
				}else if(childFile.exists()&&childFile.isDirectory()){
					listSubFile(childPath,fileList);
				}
			}
		}
		
	}
}
