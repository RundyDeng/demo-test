package com.song.util;

import java.io.InputStream;

public class FileLoadUtils {

	public static InputStream readFile(String filePath, Class<?> clazz) {
		return clazz.getClassLoader().getResourceAsStream(filePath);
	}
}
