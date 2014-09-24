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
 * 配置文件管理器
 * @author YANGYANG
 * 2005-3-30 11:04:19
 */
public abstract class ConfigManager {
	
	//XML文件对应的JDOM对象
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
	 * 设置XML元素的CDATA内容
	 * @param element
	 * @param text
	 */
	public void setCDATA(Element element ,String text){
		//除去元素中所有CDATA对象
		List contents = element.getContent();
		for (int i = 0; i < contents.size(); i++) {
			CDATA cdata = (CDATA)contents.get(i);
			element.removeContent(cdata);			
		}
		
		//构造CDATA对象，并加入到元素中
		CDATA content = new CDATA(text);
		element.addContent(content);
	}
	
	/**
	 * 取得XML元素的CDATA中的文本内容
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
     * 解析xml文件到Document对象中
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
            System.out.println("JDOM解析出错：" + e.getMessage());
            throw new IOException("JDOM解析出错：" + e.getMessage());
        }
        return document;
    }

	
	
	/**
	 * 输出到XML文件
	 * @throws IOException
	 */
	public void output() throws IOException{
		this.output(getRoot().getDocument(), getXmlConfigFile());
	}


    /**
     * 把Document对象写入到XML文件中
     * @param document		jdom对象
     * @param filepath		XML文件路径
     */
    public void output(Document document, String filepath) throws IOException {
		output(document,new File(filepath));
    }
    
	/**
	 * 把Document对象写入到XML文件中
	 * @param document		jdom对象
	 * @param file			File对象
	 * @throws IOException
	 */  
	public void output(Document document, File file) throws IOException {
		output(document,new FileOutputStream(file));
	}
    
	/**
	 * 把Document对象写入到输出流中
	 * @param document		jdom对象
	 * @param outputStream	输出流对象
	 * @throws IOException
	 */   
	public void output(Document document, OutputStream outputStream) throws IOException {
		try {
			
			XMLOutputter outputter = new XMLOutputter();
			
			//设置是否缩进
			outputter.setIndent(true);
			//设置是否换行
			outputter.setNewlines(true);
			//fmt.setExpandEmptyElements(true);
			//设置文本格式化
			outputter.setTextNormalize(true);
			//设置编码
			outputter.setEncoding("UTF-8");
			
			//输出到输出流
			outputter.output(document, outputStream);
            
		} catch(IOException e) {
			System.out.println("IO操作出错：" + e.getMessage());
			throw e;
		}
		
	}
    
    
    
    
    
    
    
    //XML文件最后修改时间
    protected long lastModified = 0;
    //XML文件的File对象引用
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
     * 验证文件是否已经被修改
     * @throws IOException
     */
	public void checkModified() throws IOException{
		
		//如果文件已经被修改过了，则要重新载入XML文件中的内容
		long newModified = xmlConfigFile.lastModified();
		if(newModified>this.lastModified){
			
			Timestamp timeOld = new Timestamp(lastModified);
			Timestamp timeNew = new Timestamp(newModified);
			
			System.out.println(xmlConfigFile.getName() + "文件已修改 记录修改时间："+timeOld.toString().substring(0,19)+"，最新修改时间："+timeNew.toString().substring(0,19));
			try {
				//重新载入XML文件中的内容，子类应覆盖reload()方法
				reload();
			} catch (IOException e) {
				throw e;
			}
		}
		
	}
    
    
    /**
     * 回调方法
     * 重新载入XML文件的方法
     * 子类应该覆盖这个方法，并在覆盖方法的首行调用super.reload()方法
     * 
     * @throws IOException
     */
	protected void reload() throws IOException{
		
		//把文件中的内容读取到内存对象中
		this.document = this.parse(this.getXmlConfigFile().getCanonicalPath());
		this.root = document.getRootElement();
		
		//更新文件的最后修改时间
		this.lastModified = xmlConfigFile.lastModified();
		
		Timestamp time = new Timestamp(lastModified);
		System.out.println(xmlConfigFile.getName() + "文件已载入 载入时间：" + time.toString().substring(0,19) );
    }
   





    


}











