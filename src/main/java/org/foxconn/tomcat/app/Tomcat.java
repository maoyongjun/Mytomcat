package org.foxconn.tomcat.app;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Tomcat {
	public   static final String path ="D:\\SeagateWebserviceOld\\target\\";
	private List<WebApp> apps;
	public Tomcat(){
		apps = new ArrayList<WebApp>();
		try {
			WebApp app = new WebApp("webservice");
			apps.add(app);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<WebApp> getApps(){
		return apps;
	}
	
}
