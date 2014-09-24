package com.icss.regulation.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icss.common.zip.ZipEntry;
import com.icss.common.zip.ZipOutputStream;

public class ZipTools {

	/**
	 * 生成ZIP文件，把一系列的文件生成到一个ZIP里，使用无目录的形式。
	 * @param fileFullPathList 要压缩的文件列表
	 * @param outputFullFileName 压缩成的文件的输出路径  
	 * @author lintl
	 * @return
	 */
	public static boolean MakeZip(List<String> fileFullPathList,
			String outputFullFileName) {
		try {
			byte[] buf = new byte[1024];
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					outputFullFileName));
			for (Iterator<String> i = fileFullPathList.iterator(); i.hasNext();) {
				String nowFilePath = (String) i.next();
				File nowFile = new File(nowFilePath);
				FileInputStream in = new FileInputStream(nowFile);
				//out.putNextEntry(new ZipEntry(new String(nowFile.getName().getBytes("gb2312"),"ISO8859-1")));
				out.putNextEntry(new ZipEntry(nowFile.getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {

		List<String> flist = new ArrayList<String>();
		flist.add("D:\\中文1.doc");
		flist.add("D:\\中文2.doc");
		flist.add("D:\\中文3.doc");
		flist.add("D:\\新建文件夹\\123.txt");
		//flist.add("D:\\Tomcat5.doc");

		System.out.println(MakeZip(flist, "D:\\temp.zip"));

	}

}