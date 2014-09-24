package com.icss.oa.ftp;

import java.io.IOException;
import java.util.List;

public interface IFtp {
	
	//连接ftp服务器
	public abstract void connect(String host,String username,String password) throws IOException;
	
	//关闭ftp服务器
	public abstract void close() throws IOException;
	
	//取得一个指定目录子目录的列表，如果不存在子目录，则返回为空
	public abstract List getSubFolders() throws IOException;
	
	//取得一个指定目录下文件列表，如果没有文件则返回为空
	public abstract List getFiles() throws IOException;
	
}
