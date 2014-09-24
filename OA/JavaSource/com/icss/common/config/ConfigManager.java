/*
 * Created on 2005-3-30
 */
package com.icss.common.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

/**
 * �����ļ�������
 * @author YANGYANG
 * 2005-3-30 11:04:19
 */
public abstract class ConfigManager {
	
	//XML�ļ���Ӧ��JDOM����
	protected Document document;
	protected Element root;
	
	/**
	 * @return
	 */
	public Document getDocument() {
		return document;
	}
	/**
	 * @return
	 */
	public Element getRoot() {
		return root;
	}

	/**
	 * @param document
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
	/**
	 * @param element
	 */
	public void setRoot(Element element) {
		root = element;
	}
	
	/**
	 * ����XMLԪ�ص�CDATA����
	 * @param element
	 * @param text
	 */
	public void setCDATA(Element element ,String text){
		//��ȥԪ��������CDATA����
		List contents = element.getContent();
		for (int i = 0; i < contents.size(); i++) {
			CDATA cdata = (CDATA)contents.get(i);
			element.removeContent(cdata);			
		}
		
		//����CDATA���󣬲����뵽Ԫ����
		CDATA content = new CDATA(text);
		element.addContent(content);
	}
	
	/**
	 * ȡ��XMLԪ�ص�CDATA�е��ı�����
	 * @param element
	 * @return
	 */
	public String getCDATA(Element element){
		String text = "";
		List contents = element.getContent();
		if(contents.size()>0){
			text = ((CDATA)contents.get(0)).getText();
		}
		return text;
	}
	
	
    /**
     * ����xml�ļ���Document������
     * @param fileAddr
     * @return
     */
    public Document parse(String file) throws IOException {
        Document document = null;
        SAXBuilder builder = new SAXBuilder(false);
        try {
            document = builder.build(file);
            
        } catch (JDOMException e) {
            e.printStackTrace();
            System.out.println("JDOM��������" + e.getMessage());
            throw new IOException("JDOM��������" + e.getMessage());
        }
        return document;
    }

	
	
	/**
	 * �����XML�ļ�
	 * @throws IOException
	 */
	public void output() throws IOException{
		this.output(getRoot().getDocument(), getXmlConfigFile());
	}


    /**
     * ��Document����д�뵽XML�ļ���
     * @param document		jdom����
     * @param filepath		XML�ļ�·��
     */
    public void output(Document document, String filepath) throws IOException {
		output(document,new File(filepath));
    }
    
	/**
	 * ��Document����д�뵽XML�ļ���
	 * @param document		jdom����
	 * @param file			File����
	 * @throws IOException
	 */  
	public void output(Document document, File file) throws IOException {
		output(document,new FileOutputStream(file));
	}
    
	/**
	 * ��Document����д�뵽�������
	 * @param document		jdom����
	 * @param outputStream	���������
	 * @throws IOException
	 */   
	public void output(Document document, OutputStream outputStream) throws IOException {
		try {
			
			XMLOutputter outputter = new XMLOutputter();
			
			//�����Ƿ�����
			outputter.setIndent(true);
			//�����Ƿ���
			outputter.setNewlines(true);
			//fmt.setExpandEmptyElements(true);
			//�����ı���ʽ��
			outputter.setTextNormalize(true);
			//���ñ���
			outputter.setEncoding("UTF-8");
			
			//����������
			outputter.output(document, outputStream);
            
		} catch(IOException e) {
			System.out.println("IO��������" + e.getMessage());
			throw e;
		}
		
	}
    
    
    
    
    
    
    
    //XML�ļ�����޸�ʱ��
    protected long lastModified = 0;
    //XML�ļ���File��������
    protected File xmlConfigFile = null;
    
    public void setXmlConfigFile(File xmlConfigFile){
    	this.xmlConfigFile = xmlConfigFile;
    }
    public File getXmlConfigFile(){
    	return this.xmlConfigFile; 
    }
    
    public void setLastModified(long lastModified){
    	this.lastModified = lastModified;
    }
    public long getLastModified(){
    	return this.lastModified;
    }
    
    
    /**
     * ��֤�ļ��Ƿ��Ѿ����޸�
     * @throws IOException
     */
	public void checkModified() throws IOException{
		
		//����ļ��Ѿ����޸Ĺ��ˣ���Ҫ��������XML�ļ��е�����
		long newModified = xmlConfigFile.lastModified();
		if(newModified>this.lastModified){
			
			Timestamp timeOld = new Timestamp(lastModified);
			Timestamp timeNew = new Timestamp(newModified);
			
			System.out.println(xmlConfigFile.getName() + "�ļ����޸� ��¼�޸�ʱ�䣺"+timeOld.toString().substring(0,19)+"�������޸�ʱ�䣺"+timeNew.toString().substring(0,19));
			try {
				//��������XML�ļ��е����ݣ�����Ӧ����reload()����
				reload();
			} catch (IOException e) {
				throw e;
			}
		}
		
	}
    
    
    /**
     * �ص�����
     * ��������XML�ļ��ķ���
     * ����Ӧ�ø���������������ڸ��Ƿ��������е���super.reload()����
     * 
     * @throws IOException
     */
	protected void reload() throws IOException{
		
		//���ļ��е����ݶ�ȡ���ڴ������
		this.document = this.parse(this.getXmlConfigFile().getCanonicalPath());
		this.root = document.getRootElement();
		
		//�����ļ�������޸�ʱ��
		this.lastModified = xmlConfigFile.lastModified();
		
		Timestamp time = new Timestamp(lastModified);
		System.out.println(xmlConfigFile.getName() + "�ļ������� ����ʱ�䣺" + time.toString().substring(0,19) );
    }
   





    


}











