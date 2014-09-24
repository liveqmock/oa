package com.icss.oa.ftp;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eprobiti.trs.TRSConnection;
import com.eprobiti.trs.TRSConstant;
import com.eprobiti.trs.TRSException;
import com.eprobiti.trs.TRSResultSet;
import com.icss.oa.util.FtpPropertyManager;
import com.icss.oa.util.TimeUtil;

public class FtpTrs {

	public List SearchNext(List readlist,String keyword,int lastrecord) throws TRSException ,IOException{
		List filelist = new ArrayList();
		List result = new ArrayList();
		String trsip = FtpPropertyManager.getProperty("trsip");
		String trsport = FtpPropertyManager.getProperty("trsport");
		String trsuser = FtpPropertyManager.getProperty("trsuser");
		String trspassword = FtpPropertyManager.getProperty("trspassword");
		String searchtable = FtpPropertyManager.getProperty("searchtable");
		StringBuffer wherebuf = new StringBuffer();
		wherebuf.append("FILENAME = '%"+ keyword +"%' OR FOLDER = '%"+ keyword +"%'");	
		TRSConnection trsconn = new TRSConnection();
		trsconn.connect(trsip.trim(),trsport.trim(),trsuser.trim(),trspassword.trim());
		TRSResultSet trsrs = new TRSResultSet();
		trsrs.setConnection(trsconn);
		trsrs.executeSelect(searchtable.trim(), wherebuf.toString(), "-CREATETIME","", "*", 0, TRSConstant.TCE_OFFSET, false);
		Long total=new Long(trsrs.getRecordCount());
		trsrs.moveTo(0, lastrecord);
		int pagecount = 5;
		int count=lastrecord;
		int count2=0;
		while(count<total.intValue() && count2<pagecount){
			String path = trsrs.getString("DOWNLOADPATH").substring(0,(trsrs.getString("DOWNLOADPATH")).lastIndexOf("/"));
			if(path!=null && readlist.contains(path.trim())){
				SearchResultVO srvo = new SearchResultVO();
				srvo.setFilename(parseHtml(trsrs.getString("FILENAME"),keyword));
				String content = trsrs.getString("FOLDER");
				int sit = content.indexOf(keyword);
				String contenthtml = "";
				if(sit == 0){
					if(content.length()>sit+25){
						content = content.substring(sit,sit+25);
						contenthtml = parseHtml(content,keyword);
						contenthtml = contenthtml + "......";
					}else{
						content = content.substring(sit,content.length());
						contenthtml = parseHtml(content,keyword);
					}
				}if(sit>0){
					if(content.length()>sit+25){
						content = content.substring(sit,sit+25);
						contenthtml = parseHtml(content,keyword);
						contenthtml = contenthtml + "......";
					}else{
						content = content.substring(sit,content.length());
						contenthtml = parseHtml(content,keyword);
					}
					contenthtml =  "......" + contenthtml ;
				}
				srvo.setContent(contenthtml);
				srvo.setCreatetime(TimeUtil.getDate(new Date(Long.parseLong(trsrs.getString("CREATETIME")))));
				srvo.setDownload(trsrs.getString("DOWNLOADPATH"));
				srvo.setDownloadhtml(parseHtml(trsrs.getString("DOWNLOADPATH"),keyword));
				filelist.add(srvo);
				count2 = count2 + 1;
			}
			count++;
			trsrs.moveNext();
			
		}
		//1，结果集
		result.add(filelist);
		//2, 当前读取到第几条记录
		result.add(String.valueOf(count));
		//3, 当页从第几条记录开始
		result.add(String.valueOf(lastrecord));
		//4,所有记录数
		result.add(String.valueOf(total));
		return result;
	}
	
	
	
	public List SearchPrevious(List readlist,String keyword,int lastrecord) throws TRSException ,IOException{
		List filelist = new ArrayList();
		List result = new ArrayList();
		String trsip = FtpPropertyManager.getProperty("trsip");
		String trsport = FtpPropertyManager.getProperty("trsport");
		String trsuser = FtpPropertyManager.getProperty("trsuser");
		String trspassword = FtpPropertyManager.getProperty("trspassword");
		String searchtable = FtpPropertyManager.getProperty("searchtable");
		StringBuffer wherebuf = new StringBuffer();
		wherebuf.append("FILENAME = '%"+ keyword +"%' OR FOLDER = '%"+ keyword +"%'");
		TRSConnection trsconn = new TRSConnection();
		trsconn.connect(trsip.trim(),trsport.trim(),trsuser.trim(),trspassword.trim());
		TRSResultSet trsrs = new TRSResultSet();
		trsrs.setConnection(trsconn);
		trsrs.executeSelect(searchtable.trim(), wherebuf.toString(), "-CREATETIME","", "*", 0, TRSConstant.TCE_OFFSET, false);
		Long total=new Long(trsrs.getRecordCount());
		trsrs.moveTo(0, lastrecord);
		int pagecount = 5;
		int count=lastrecord;
		int count2=0;
		while(count>=0 && count2<pagecount){
			String path = trsrs.getString("DOWNLOADPATH").substring(0,(trsrs.getString("DOWNLOADPATH")).lastIndexOf("/"));
			if(path!=null && readlist.contains(path.trim())){
				SearchResultVO srvo = new SearchResultVO();
				srvo.setFilename(parseHtml(trsrs.getString("FILENAME"),keyword));
				String content = trsrs.getString("FOLDER");
				int sit = content.indexOf(keyword);
				String contenthtml = "";
				if(sit == 0){
					if(content.length()>sit+25){
						content = content.substring(sit,sit+25);
						contenthtml = parseHtml(content,keyword);
						contenthtml = contenthtml + "......";
					}else{
						content = content.substring(sit,content.length());
						contenthtml = parseHtml(content,keyword);
					}
				}if(sit>0){
					if(content.length()>sit+25){
						content = content.substring(sit,sit+25);
						contenthtml = parseHtml(content,keyword);
						contenthtml = contenthtml + "......";
					}else{
						content = content.substring(sit,content.length());
						contenthtml = parseHtml(content,keyword);
					}
					contenthtml =  "......" + contenthtml ;
				}
				srvo.setContent(contenthtml);
				srvo.setCreatetime(TimeUtil.getDate(new Date(Long.parseLong(trsrs.getString("CREATETIME")))));
				srvo.setDownload(trsrs.getString("DOWNLOADPATH"));
				srvo.setDownloadhtml(parseHtml(trsrs.getString("DOWNLOADPATH"),keyword));
				filelist.add(srvo);
				count2 = count2 + 1;
			}
			count--;
			trsrs.movePrevious();
			
		}
		//1，结果集
		result.add(filelist);
		//2, 当页读取到第几条记录
		result.add(String.valueOf(lastrecord));
		//3, 当页从第几条记录开始
		result.add(String.valueOf(count));
		//4,所有记录数
		result.add(String.valueOf(total));
		return result;
	}
	
	
	public String parseHtml(String content,String keyword) throws IOException{
		String keyhtml = "<font color=\"red\">"+keyword+"</font>";
		content = content.replaceAll(keyword,keyhtml);
		return content;
	}
}
