/*
 * Created on 2004-3-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.util;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FileTypeUtil {
	private String xmlFile = "/WEB-INF/classes/fileType.xml";

	private static final String MAPPINGS = "mime-mapping";
	private static final String MIME_TYPE = "mime-type";
	private static final String FILE_TYPE = "extension";
	private Document document = null;
	//单例
	private static FileTypeUtil instance = null;

	private FileTypeUtil() {

	}
	public static synchronized FileTypeUtil getInstance() {
		if (instance == null) {
			instance = new FileTypeUtil();
		}

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

	//	取得文件类型及图片名
	public String getMimeType(String contenttype) {
		String mimetype = "";
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
				if (filetypeExist(contenttype, mapping)) {
					mimetype = mapping.elementText(MIME_TYPE);
					break;
				}
			}
		}
		return mimetype;
	}

	private boolean filetypeExist(String contenttype, Element mapping) {
		return contenttype.equals(mapping.elementText(FILE_TYPE));
	}
	/**
	 * 取得后缀
	 * @param fileName
	 * @return
	 */
	public String getFileExtension(String fileName) {
		String fileType = "";
		if (fileName != null) {
			int index = 0;
			index = fileName.lastIndexOf("\\");
			if (index != -1) {
				fileName = fileName.substring(index + 1);
			}

			//取得文件后缀名
			index = fileName.lastIndexOf(".");
			if (index != -1) {
				fileType = fileName.substring(index + 1);
			}
		}
		return fileType;
	}
}
