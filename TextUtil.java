package com.song.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TextUtil {

	//Read general CSV file(comma separator)
	public static List<List<Object>> readCSV(String filePath) throws IOException{
		
		File file = new File(filePath);
		List<List<Object>> list = new LinkedList<List<Object>>();
		
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(file));
		String line ="";
		Object[] everyLine;
		while((line=br.readLine()) != null) {
			List<Object> linked = new LinkedList<Object>();
			everyLine = line.split(",");
			for(int i = 0; i < everyLine.length;i++) {
				linked.add(everyLine[i]);
				System.out.println(everyLine[i].toString());//test
			}
			System.out.println(linked);//test
			list.add(linked);
		}
		br.close();
		return list;
		
	}
}
