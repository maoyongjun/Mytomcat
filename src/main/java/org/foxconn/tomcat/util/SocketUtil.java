package org.foxconn.tomcat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.junit.Test;

public class SocketUtil {
	Logger logger = Logger.getLogger(SocketUtil.class);

	@Test
	public void test() throws IOException, InterruptedException {
		Socket socket = null;
		ServerSocket server = new ServerSocket(8081);
//		socket = new Socket("127.0.0.1", 8081);
		while (true) {
			socket = server.accept();
			 getMsg(socket);
		}

	}

	private void getMsg(Socket socket) {
		try {
			InputStream input = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(input, "UTF-8");
			BufferedReader bufferReader = new BufferedReader(reader);
			String msg;
			while (null != (msg = bufferReader.readLine())) {
				logger.info(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
