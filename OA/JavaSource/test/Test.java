/*
 * Created on 2004-11-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test;

import java.io.*;
import java.text.DecimalFormat;

import com.icss.j2ee.util.FileUtil;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Test {

	public static void main(String[] args) {
//		String[] to = {};
//		String addressnum = "wwwwwwww";
//		to = addressnum.split(",");
//		System.out.println(to.length);
		
//		StringTokenizer toList = new StringTokenizer("wwwwwwww,ffffffff,",",");
//		int x = 0;
//		while(toList.hasMoreTokens()){
//			System.out.println(toList.nextToken());
//			to[x] = toList.nextToken();
//			x++;
//			System.out.println(x);
//		}
		
//		String oldpath = "C:/R1logging.log";
//		String newpath = "C:\\TMP\\新建 文本文档new.txt";
//		File oldfile = new File(oldpath);
//		if(!oldfile.exists()){
//			try {
//				oldfile.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		File newfile = new File(newpath);
//		
//		if(oldfile.renameTo(newfile)){
//			System.out.println("OK");
//		}
//		int pathindex = path.lastIndexOf("\\");
//		String privatedir = path.substring(0,pathindex);
//		File attachfile = new File(path);
//		attachfile.delete();
//		File dirfile = new File(privatedir);
//		dirfile.delete();
//		System.out.println("OK");
//		if(USETEXTLINKS){
//			String temp = "<a href=\"javascript:_reloadMainFrame('"+this.link+"')\">"+this.desc+"</a>";
//			System.out.println("temp = " + temp);
//			d.write("<a href=\"javascript:_reloadMainFrame('"+this.link+"')\">"+this.desc+"</a>");
//		}
		
//		InputStream inputStream;
//		try {
//			inputStream = new FileInputStream(oldfile);
//			
//			long fileSize = 0;
//			byte[] bufbyte = new byte[2048]; 
//			int templength = 0;
//			double xx = 800000;
//			long yy = new Double(xx).longValue();
//			try {
//				while((templength = inputStream.read(bufbyte,0,2048))!=-1){
//					fileSize = fileSize + new Long(templength).longValue();
//					System.out.println(templength);
//				}
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			
//			System.out.println("the length is:"+fileSize);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
        long a = 40000;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String c = decimalFormat.format((new Long(a).doubleValue()/500000));
        System.out.println("result:"+a+"...."+c);
	}
}
