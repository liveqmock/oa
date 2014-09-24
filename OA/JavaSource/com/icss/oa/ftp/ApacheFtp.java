package com.icss.oa.ftp;

import java.io.IOException;
import java.net.SocketException;

import java.io.IOException;    
import java.io.OutputStream;    
 
import org.apache.commons.net.ftp.FTPClient;    
import org.apache.commons.net.ftp.FTPFile; 

public class ApacheFtp {

	private int port = 21;
	private FTPClient ftpClient = new  FTPClient();
	public String state = "0";
		
    public void connectServer(
    		String host, int port, String username, String password)
    		throws SocketException, IOException {
    	ftpClient=new FTPClient();
    	ftpClient.connect(host, port);
    	ftpClient.login(username, password);
    }
    
	public  String getFiles(String dir) throws IOException{
		 String files = "";
		 ftpClient.changeWorkingDirectory(dir);
		 FTPFile[] remoteFiles = ftpClient.listFiles();    
         if(remoteFiles==null||remoteFiles.length==0){
             System.out.println("There has not any file!");
         }
         for(int i=0;i<remoteFiles.length;i++){
        	 if(remoteFiles[i].isFile()){
                 String name = iso8859togbk(remoteFiles[i].getName());
                 files = files + "," + name;
        	 }
          }    
         return files;
	}
	
	public  String getFolders(String dir) throws IOException{
		 String folders = "";
		 ftpClient.changeWorkingDirectory(dir);
		 FTPFile[] remoteFiles = ftpClient.listFiles();    
        if(remoteFiles==null||remoteFiles.length==0){
            System.out.println("There has not any file!");
        }

       for(int i=0;i<remoteFiles.length;i++){
    	   if(remoteFiles[i].isDirectory()){
             String name = iso8859togbk(remoteFiles[i].getName());
             folders = folders + "," + name;
          }
       }    
        
        return folders;
	}

	

	
    private static String iso8859togbk(Object obj){
        try{
            if(obj==null)
                return "";
            else
                return new String(obj.toString().getBytes("iso-8859-1"),"GBK");
        }catch(Exception e){
            return "";
        }
    }
    
    public  void disconnectServer() throws IOException {
    	ftpClient.disconnect();
    }
    
    public static void main(String[] args) {
    	
    	ApacheFtp a = new ApacheFtp();
    	try {
			a.connectServer("172.16.143.70", 21, "sms", "sms");
			System.out.println(a.getFiles("./da"));
			System.out.println(a.getFolders("./da"));
			a.disconnectServer();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
