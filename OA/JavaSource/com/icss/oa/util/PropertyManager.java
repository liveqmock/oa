package com.icss.oa.util;

/**
 * PropertyManager.java
 * Copyright (C) 2003 All rights reserved.
 * =========================================================
 * 管理整个系统的属性文件。<p>
 * 为了方便系统里部分类的属性值的变更，而采用了将
 * 一些属性值存储到系统的一个配置文件的方法，从而方便
 * 今后的灵活变动而无须重新编译CLASS。<p>
 * 同时此属性文件里面也保存了类似系统版本号等的系统内部信息
 * 可以方便对多个版本的控制。
 * 今后可以采用XML格式的属性文件，这样可以更方便每一层的控制。
 */

import java.io.*;
import java.util.*;

public class PropertyManager {

	/**
	 * 系统的主版本号. i.e. 1.x.x
	 */
	private static final int MAJOR_VERSION = 1;

	/**
	 * 系统的辅版本号. i.e. x.1.x.
	 */
	private static final int MINOR_VERSION = 1;

	/**
	 * 系统的修正版本号. i.e. x.x.1.
	 */
	private static final int REVISION_VERSION = 3;

	private static PropertyManager manager = null;
	private static Object managerLock = new Object();
	private static String propsName = "/WEB-INF/filetransfer.properties";

	private Properties properties = null;
	private Object propertiesLock = new Object();
	private String resourceURI;

	/**
	 * Creates a new PropertyManager. Singleton access only.
	 */
	
	private PropertyManager(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	
	/**
	 * add by lxy 2008-4-7
	 * 
	 */
	public static void SetPropsName(String name){
		propsName = name;
	}
	
	protected void deleteProp(String name) {
		//Only one thread should be writing to the file system at once.
		synchronized (propertiesLock) {
			//Create the properties object if necessary.
			if (properties == null) {
				loadProps();
			}
			properties.remove(name);
			saveProps();
		}
	}
	/**
	 * Deletes a IMP property. If the property doesn't exist, the method
	 * does nothing.
	 *
	 * @param name the name of the property to delete.
	 */
	public static void deleteProperty(String name) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		manager.deleteProp(name);
	}
	/**
	 * Returns the version number of IMP as a String. i.e. -- major.minor.revision
	 */
	public static String getImpVersion() {
		return MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION;
	}
	/**
	 * Returns the major version number of IMP. i.e. -- 1.x.x
	 */
	public static int getImpVersionMajor() {
		return MAJOR_VERSION;
	}
	/**
	 * Returns the minor version number of IMP. i.e. -- x.1.x
	 */
	public static int getImpVersionMinor() {
		return MINOR_VERSION;
	}
	/**
	 * Returns the revision version number of IMP. i.e. -- x.x.1
	 */
	public static int getImpVersionRevision() {
		return REVISION_VERSION;
	}
	/**
	 * Gets a IMP property. IMP properties are stored in imp.properties.
	 * The properties file should be accesible from the classpath. Additionally,
	 * it should have a path field that gives the full path to where the
	 * file is located. Getting properties is a fast operation.
	 *
	 * @param name the name of the property to get.
	 * @return the property specified by name.
	 */
	protected String getProp(String name) {
		//If properties aren't loaded yet. We also need to make this thread
		//safe, so synchronize...
		if (properties == null) {
			synchronized (propertiesLock) {
				//Need an additional check
				if (properties == null) {
					loadProps();
				}
			}
		}
		String property = properties.getProperty(name);
		if (property == null) {
			return null;
		} else {
			return property.trim();
		}
	}
	/**
	 * Returns a IMP property.
	 *
	 * @param name the name of the property to return.
	 * @return the property value specified by name.
	 */
	public static String getProperty(String name) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
//		manager = new PropertyManager(propsName);
		return StringUtility.toChinese(manager.getProp(name));
	}
	/**
	 * Loads IMP properties from the disk.
	 */
	private void loadProps() {
		properties = new Properties();
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(resourceURI);
			properties.load(in);
		} catch (Exception e) {
			System.err.println(
				"不能读取属性文件 " + e); 
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
	}
	/**
	 * Returns true if the imp.properties file exists where the path property
	 * purports that it does.
	 */
	public static boolean propertyFileExists() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileExists();
	}
	/**
	 * Returns true if the properties are readable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public static boolean propertyFileIsReadable() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileIsReadable();
	}
	/**
	 * Returns true if the properties are writable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public static boolean propertyFileIsWritable() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propFileIsWritable();
	}
	/**
	 * Returns the names of the IMP properties.
	 *
	 * @return an Enumeration of the IMP property names.
	 */
	public static Enumeration propertyNames() {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		return manager.propNames();
	}
	/**
	 * Returns true if the imp.properties file exists where the path property
	 * purports that it does.
	 */
	public boolean propFileExists() {
		String path = getProp("path");
		File file = new File(path);
		if (file.isFile()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Returns true if the properties are readable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public boolean propFileIsReadable() {
		try {
			InputStream in = getClass().getResourceAsStream(resourceURI);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Returns true if the properties are writable. This method is mainly
	 * valuable at setup time to ensure that the properties file is setup
	 * correctly.
	 */
	public boolean propFileIsWritable() {
		String path = getProp("path");
		File file = new File(path);
		if (file.isFile()) {
			//See if we can write to the file
			if (file.canWrite()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	protected Enumeration propNames() {
		//If properties aren't loaded yet. We also need to make this thread
		//safe, so synchronize...
		if (properties == null) {
			synchronized (propertiesLock) {
				//Need an additional check
				if (properties == null) {
					loadProps();
				}
			}
		}
		return properties.propertyNames();
	}
	/**
	 * Saves IMP properties to disk.
	 */
	private void saveProps() {
		//Now, save the properties to disk. In order for this to work, the user
		//needs to have set the path field in the properties file. Trim
		//the String to make sure there are no extra spaces.
		String path = properties.getProperty("path").trim();
		OutputStream out = null;
		try {
			out = new FileOutputStream(path);
			properties.store(out, "imp.properties -- " + (new java.util.Date()));
		} catch (Exception ioe) {
			System.err.println(
				"There was an error writing imp.properties to "
					+ path
					+ ". "
					+ "Ensure that the path exists and that the IMP process has permission "
					+ "to write to it -- "
					+ ioe); 
			ioe.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
	/**
	 * Sets a IMP property. Because the properties must be saved to disk
	 * every time a property is set, property setting is relatively slow.
	 */
	protected void setProp(String name, String value) {
		//Only one thread should be writing to the file system at once.
		synchronized (propertiesLock) {
			//Create the properties object if necessary.
			if (properties == null) {
				loadProps();
			}
			properties.setProperty(name, value);
			saveProps();
		}
	}
	/**
	 * Sets a IMP property. If the property doesn't already exists, a new
	 * one will be created.
	 *
	 * @param name the name of the property being set.
	 * @param value the value of the property being set.
	 */
	public static void setProperty(String name, String value) {
		if (manager == null) {
			synchronized (managerLock) {
				if (manager == null) {
					manager = new PropertyManager(propsName);
				}
			}
		}
		manager.setProp(name, value);
	}
//
//	/**
//	 * @return
//	 */
//	public static String getPropsName() {
//		return propsName;
//	}
//
//	/**
//	 * @param string
//	 */
//	public static void setPropsName(String string) {
//		propsName = string;
//	}
	
	public static void main(String[] args){
//		PropertyManager.SetPropsName("/WEB-INF/ftp.properties");
//		String ftpip = PropertyManager.getProperty("ftpip");
//		String ftpuser = PropertyManager.getProperty("ftpuser");
//		String ftppass = PropertyManager.getProperty("ftppass");
//		System.out.println(ftpip);
//		System.out.println(ftpuser);
//		System.out.println(ftppass);
		
		
//		PropertyManager.SetPropsName("/WEB-INF/filetransfer.properties");
//		System.out.println(PropertyManager.getProperty("archivesip"));
	}

}
