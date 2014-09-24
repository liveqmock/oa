package FTPjar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sun.net.ftp.FtpClient;



public class SunFtpImpl implements IFtp {
	
	private FtpClient client;
	private int port = 21;
	private static SunFtpImpl instance;
	private String path = "";
	
	private void SunFtpClient(){}
	
	public static SunFtpImpl getInstance(){
		if(instance == null){
			instance = new SunFtpImpl();
		}
		return instance;
	}
	
	public void close() throws IOException {
		if(client != null){
			client.closeServer();
			client = null;
		}			
	}

	public void connect(String host,String username,String password) throws IOException {
		if(client == null){
			client = new FtpClient(host,port);
			client.login(username, password);
			client.binary();
		}
	}

	public void setRir(String dir) throws IOException{
		client.noop();
		client.cd(dir);
		path = dir; 
	}
	
	public List getFiles() throws IOException{
		BufferedReader dr = new BufferedReader(new InputStreamReader(client.list()));
		ArrayList al = new ArrayList();
		String name = "";
		while ( (name = dr.readLine()) != null) {
			if(!".".equals((String)((List)parseLine(name)).get(8)) && !"..".equals((String)((List)parseLine(name)).get(8))){
				if(isFile(parseLine(name)) == true){
					al.add(path + "/" +(((List)parseLine(name)).get(8)));
				}	
			}
		}
		return al;

	}


	public List getSubFolders() throws IOException{
		BufferedReader dr = new BufferedReader(new InputStreamReader(client.list()));
		ArrayList al = new ArrayList();
		String name = "";
		while ( (name = dr.readLine()) != null) {
			if(!".".equals((String)(parseLine(name)).get(8)) && !"..".equals((String)(parseLine(name)).get(8))){
				if(isFile(parseLine(name)) == false){
					al.add(path + "/" +(((List)parseLine(name)).get(8)));
				}
			}
			
		}
		return al;
	}

	public boolean isFile(List list){
		String temp = (String)list.get(0);
		if(temp.toCharArray()[0] == 'd'){
			return false;
		}else{
			return true;
		}	
	}
	
	public List parseLine(String line){
		List l = new ArrayList();
		StringTokenizer st = new StringTokenizer(line, " ");
		while (st.hasMoreTokens()) {
			l.add(st.nextToken());
		}
		return l;
	}
	
	public static void main(String[] args){
		SunFtpImpl fsi = SunFtpImpl.getInstance();
		try {
			fsi.connect("172.16.143.70","sms","sms");
			fsi.setRir(".");
			List l = (List)fsi.getFiles();	
			for(int i=0;i<l.size();i++){
				System.out.println((String)l.get(i));
			}
			System.out.println("==============================");
			List m = (List)fsi.getSubFolders();
			for(int i=0;i<m.size();i++){
				System.out.println((String)m.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
