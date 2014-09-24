package FTPjar;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


public class FtpApplet extends Applet implements Runnable{

	private int port = 21;
	private FTPClient ftpClient = new  FTPClient();
	private Thread thread;
	public String state = "0";
		
	public void init(){   
		thread = new Thread(this);   
		thread.start();   
	}   

    public String connectServer(
    		String host, int port, String username, String password){
    	ftpClient=new FTPClient();
    	try {
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			return "1";
		} catch (SocketException e) {
			e.printStackTrace();
			return "01";
		} catch (IOException e) {
			e.printStackTrace();
			return "001";
		}
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
	
    public void run(){
    }
    
    
	public void paint(Graphics g){	
    	//ApacheFtp a = new ApacheFtp();
    	try {
			System.out.println(connectServer("172.16.143.70", 21, "sms", "sms"));
			System.out.println(getFiles("//SMS/da"));
			System.out.println(getFolders("/SMS/da"));
			g.drawString(getFiles("./da"), 5, 5);	
			g.drawString(getFolders("./da") , 5, 35);
			disconnectServer();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
