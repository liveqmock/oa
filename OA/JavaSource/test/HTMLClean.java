package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class HTMLClean {

	public static void main(String args[]) {
		try {
			System.out.println("30322".indexOf("03"));
			System.out.println("zc109zc".toUpperCase().lastIndexOf("ZC")=="01109ZC".length()-2);
		String path = "D:\\dbbak\\10.html";
		StringBuffer sbStr = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(
				new File(path)));
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sbStr.append(temp);
			sbStr.append("\r\n");
		}
		reader.close();

		String result = sbStr.toString();
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode root;
		
	
			root = cleaner.clean(result);
			System.out.println(root);
		} catch (IOException e) {
		}
	}
}