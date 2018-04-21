package org.foxconn.tomcat.server;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.foxconn.tomcat.app.Tomcat;
import org.foxconn.tomcat.app.WebApp;

public class TomcatStarter {
	Logger logger = Logger.getLogger(TomcatStarter.class);
	public static void main(String[] args) {
		TomcatStarter start = new TomcatStarter();
		start.run();
	}
	public void run(){
		logger.info("----服务开始启动---");
		//循环启动war包(使用多线程)
		Tomcat tom = new Tomcat();
		List<WebApp> apps =  tom.getApps();
		ExecutorService service = Executors.newFixedThreadPool(apps.size());
		for(WebApp app :apps){
			//加载war包 解压war包
			app.zipWar();
			//加载war包
			app.loadWar();
			//启动servlet
			try {
				app.startServlet();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
}
