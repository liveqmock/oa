package com.icss.oa.ftp;

import java.io.IOException;
import java.util.List;

public interface IFtp {
	
	//����ftp������
	public abstract void connect(String host,String username,String password) throws IOException;
	
	//�ر�ftp������
	public abstract void close() throws IOException;
	
	//ȡ��һ��ָ��Ŀ¼��Ŀ¼���б������������Ŀ¼���򷵻�Ϊ��
	public abstract List getSubFolders() throws IOException;
	
	//ȡ��һ��ָ��Ŀ¼���ļ��б����û���ļ��򷵻�Ϊ��
	public abstract List getFiles() throws IOException;
	
}
