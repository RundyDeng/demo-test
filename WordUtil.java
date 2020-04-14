package com.song.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

public class WordUtil {

	
	public void writeToWord(String fileName) throws IOException {
		
		InputStream in = FileLoadUtils.readFile("templates/"+fileName, this.getClass());
		Map<String, String> map = new IdentityHashMap();
		map.put("<companyname>", "松投投资咨询");
		map.put("<stockcode>", "66666");
		map.put("<numberOfApplication>", "1234");
		map.put("<applicationQty>", "2134");
		
		HWPFDocument wordDocument = replaceDoc(in,map);
		
		
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wordDocument.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath ="C:\\Users\\Wan\\Desktop\\smiler\\stock\\src\\main\\resources\\templates\\"+fileName;
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		wordDocument.write(byteArrayOutputStream);
		OutputStream outputStream = new FileOutputStream(filePath);
		outputStream.write(byteArrayOutputStream.toByteArray());
		in.close();
		outputStream.close();
		
	}
	
	private static HWPFDocument replaceDoc(InputStream in, Map<String,String> map) {
		
		try {
			HWPFDocument doc = new HWPFDocument(in);
			Range bodyRange = doc.getRange();
			for(Map.Entry<String, String> entry : map.entrySet()) {
				bodyRange.replaceText(entry.getKey(),entry.getValue());
			}
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	/**
	 * word 模板文件导出
	 * @param tmpFile
	 * @param contentMap
	 * @param exportFile
	 * @throws Exception
	 */
	private void build(File tmpFile, Map<String, String> contentMap, String exportFile) throws Exception {
		FileInputStream tempFileInputStream = new FileInputStream(tmpFile);  // 获取文件流
		HWPFDocument document = new HWPFDocument(tempFileInputStream);	// 创建文档对象
		// 读取文本内容	
		Range bodyRange = document.getRange();
		// 替换内容
		for (Map.Entry<String, String> entry : contentMap.entrySet()) {
			//    替换模板中的字符为新的值
			bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
		}
		// 导出到文件
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		document.write(byteArrayOutputStream);
		OutputStream outputStream = new FileOutputStream(exportFile);
		outputStream.write(byteArrayOutputStream.toByteArray());
		outputStream.close();
	}
	
	/**
	 * word 模板文件保存的具体路径
	 * @throws Exception
	 */
//	@Test
	public void testExportWord() throws Exception {
	    String tmpFile = "D:/template.doc";
	    String expFile = "D:/result.doc";
	    Map<String, String> datas = new HashMap<String, String>();
	    datas.put("title", "标题部份");	// key 与标题对应
	    datas.put("content", "这里是内容，测试使用POI导出到Word的内容！");
	    datas.put("author", "阿飞");	
	    datas.put("url", "www.baidu.com");
 
	    build(new File(tmpFile), datas, expFile);
	}
	
	/**
	 * 在项目路径下，ResourceUtils工具类的getFile方法即可读取classpath中的文件
	 * @throws Exception
	 */
//	@Test
	public void testExportWord2() throws Exception {
	    String tmpFile = "classpath:template.doc";
	    String expFile = "D:/result.doc";
	    Map<String, String> datas = new HashMap<String, String>();
	    datas.put("title", "标题部份");
	    datas.put("content", "这里是内容，测试使用POI导出到Word的内容！");
	    datas.put("author", "阿飞");	
	    datas.put("url", "www.baidu.com");
 
//	    build(FileLoadUtils.getFile(tmpFile,this.getClass()), datas, expFile);
	}
}
