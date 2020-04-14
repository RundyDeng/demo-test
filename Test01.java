package com.song.stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test01 {

	public static void main(String[] args) throws Exception {
//		URL getUrl =new URL("http://hq.sinajs.cn/list=sz300468"); 
		URL getUrl =new URL("https://v.vuevideo.net/share/post/-4363369045835519015"); 
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
		connection.connect(); 
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gb18030"));
		StringBuffer sb = new StringBuffer();
		String lines = null;
		while ((lines = reader.readLine()) != null) {
			
			sb = sb.append(lines + "\n"); 
			} 
		reader.close();
		connection.disconnect(); 
		System.out.println("result: "+sb.toString());
	}
	}

