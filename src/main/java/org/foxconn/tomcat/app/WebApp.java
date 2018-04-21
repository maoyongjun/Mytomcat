package org.foxconn.tomcat.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.foxconn.tomcat.util.FileUtil;
import org.foxconn.tomcat.util.ServletUtil;

public class WebApp {
	Logger logger = Logger.getLogger(WebApp.class);
	private List<URL> path ;
	private Servlet servlet;
	public WebApp(String appPath) throws Exception{
		logger.info("webapp的路径为："+Tomcat.path+appPath);
		path= new ArrayList<URL>();
		List<String> list1 =  FileUtil.getSubListPath(Tomcat.path+appPath+"\\WEB-INF\\classes");
		List<String> list2 =  FileUtil.getSubListPath(Tomcat.path+appPath+"\\WEB-INF\\lib");
		for(String str:list1){
			path.add(new URL("file:///"+str));
		}
		for(String str:list2){
			path.add(new URL("file:///"+str));
		}
	}
	public List<URL> getPath(){
		return path;
	}
	public void startServlet() throws ServletException {
		servlet.init(new ServletConfig() {
			
			@Override
			public String getServletName() {
				return null;
			}
			
			@Override
			public ServletContext getServletContext() {
				return null;
			}
			
			@Override
			public Enumeration getInitParameterNames() {
				return null;
			}
			
			@Override
			public String getInitParameter(String name) {
				return null;
			}
		});
		Socket socket  =null;
		try {
			socket =new Socket("127.0.0.1",8081);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ServletRequest req = ServletUtil.getServletRequest(socket);
		ServletResponse res = null;
		try {
			servlet.service(req, res);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void loadWar() {
		
		ClassLoader loader = new URLClassLoader(path.toArray(new URL[]{}));
		try {
			Class<?> cls = loader.loadClass("org.apache.axis2.transport.http.AxisServlet");
			servlet =  (Servlet) cls.newInstance();
			logger.info("servlet 创建成功");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			logger.error(e.getStackTrace().toString());
		}
		if (null==servlet){
			logger.info("servlet 创建失败");
			return;
		}
		
	}
	public void zipWar() {
		
		
	}
}
