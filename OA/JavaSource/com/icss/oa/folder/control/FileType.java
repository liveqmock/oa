/*
 * Created on 2004-3-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import com.icss.oa.folder.vo.FileTypeVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FileType {
	private String xmlFile = "fileType.xml";

	private final static String MAPPINGS = "mappings";
	private final static String MIME_TYPE = "mime-type";
	private final static String FILE_TYPE = "file-type";
	private final static String FILE_PIC = "file-pic";
	private final static String CONTENT_TYPE = "content-type";
	private Document document = null;
	//单例
	private static FileType instance = null;

	private FileType() {

	}
	public static synchronized FileType getInstance() {
		if (instance == null)
			instance = new FileType();
		return instance;
	}

	// 从文件读取XML，输入文件名，返回XML文档

	public void read(InputStream in)
		throws MalformedURLException, DocumentException {
		SAXReader reader = new SAXReader();
		document = reader.read(in);
	}

	//得到Root节点 
	public Element getRootElement() {

		return document.getRootElement();

	}

	//取得文件类型及图片名
	public FileTypeVO getXMLData(String mime) {
		String fileType = "";
		String filePic = "";
		FileTypeVO vo = new FileTypeVO();
		Element rootE = null;
		try {

			InputStream fin =
				getClass().getClassLoader().getResourceAsStream(xmlFile);
			read(fin);
			fin.close();
			rootE = getRootElement(); //取得根元素
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (rootE != null) {
			List mappingList = rootE.elements(MAPPINGS);
			//取得MAPPING元素
			Iterator it = mappingList.iterator();
			while (it.hasNext()) {
				Element mapping = (Element) it.next();
				String _mime = mapping.elementText(MIME_TYPE);
				if (_mime.equals(mime)) {
					fileType = mapping.elementText(FILE_TYPE);
					filePic = mapping.elementText(FILE_PIC);
					vo.setFileType(fileType);
					vo.setFilePic(filePic);
				}
			}

		}
		return vo;

	}
	//	取得文件类型及图片名
	public String getMimeType(String content_type) {
		String mime_type = "";
		Element rootE = null;
		try {

			InputStream fin =
				getClass().getClassLoader().getResourceAsStream(xmlFile);
			read(fin);
			fin.close();
			rootE = getRootElement(); //取得根元素
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (rootE != null) {
			List mappingList = rootE.elements(MAPPINGS);
			//取得MAPPING元素
			Iterator it = mappingList.iterator();
			while (it.hasNext()) {
				Element mapping = (Element) it.next();
				String _content = mapping.elementText(CONTENT_TYPE);
				if (_content.equals(content_type)) {
					mime_type = mapping.elementText(MIME_TYPE);

				}
			}

		}
		return mime_type;
	}
}
