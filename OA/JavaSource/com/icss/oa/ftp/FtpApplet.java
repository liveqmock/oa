package com.icss.oa.ftp;

import java.applet.Applet;
import java.awt.Graphics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpApplet extends Applet implements Runnable {

	// �˿ں�
	private int port = 21;
	// �ͻ��˶���
	private FTPClient ftpClient;
	// �̶߳���
	private Thread thread;
	// Ӧ�÷�������ڵ�ַ����������Ӧ�÷���
	private String server;
	// FTP��������ַ
	private String host;
	// FTP�������û���
	private String username;
	// FTP����������
	private String password;

	/**
	 * ��ʼ�������߳�
	 */
	public void init() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * ����FTP������
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @return
	 */
	public String connectServer(String host, int port, String username,
			String password) {
		this.ftpClient = new FTPClient();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		try {
			ftpClient.connect(host, port);
			ftpClient.login(username, password);
			ftpClient.noop();
			return "1";
		} catch (SocketException e) {
			e.printStackTrace();
			return "01";
		} catch (IOException e) {
			e.printStackTrace();
			return "001";
		}
	}

	/**
	 * �õ�һ��FTP������Ŀ¼�µ������ļ�
	 * 
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public String getFiles(String dir) throws IOException {
		String files = "";
		try {
			ftpClient.changeWorkingDirectory(gbktoiso8859(dir));
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isFile()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					files = files + "," + name;
				}
			}
			return files;
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			ftpClient.changeWorkingDirectory(gbktoiso8859(dir));
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isFile()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					files = files + "," + name;
				}
			}
			return files;
		}

	}

	/**
	 * �õ�һ��FTP������Ŀ¼�µ��ļ���
	 * 
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public String getFolders(String dir) throws IOException {
		String folders = "";
		try {
			ftpClient.changeWorkingDirectory(gbktoiso8859(dir));
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isDirectory()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					folders = folders + showFilter(name, "bak");
				}
			}
			return folders;
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			ftpClient.changeWorkingDirectory(gbktoiso8859(dir));
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isDirectory()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					folders = folders + showFilter(name, "bak");
				}
			}
			return folders;
		}
	}

	/**
	 * �õ�һ��FTP������Ŀ¼�µ������ļ���(���������ļ���)
	 * 
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public String getAllFolders(String dir) throws IOException {
		String folders = "";
		try {
			ftpClient.changeWorkingDirectory(gbktoiso8859(dir));
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isDirectory()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					folders = folders + "," + name;
				}
			}
			return folders;
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			ftpClient.changeWorkingDirectory(gbktoiso8859(dir));
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isDirectory()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					folders = folders + "," + name;
				}
			}
			return folders;
		}
	}

	/**
	 * ȡ�õ�ǰ���������ļ����б�
	 * 
	 * @return
	 * @throws IOException
	 */
	public List getFolders() throws IOException {
		List folders = new ArrayList();
		try {
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isDirectory()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					folders.add(name);
				}
			}
			return folders;
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isDirectory()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					folders.add(name);
				}
			}
			return folders;
		}
	}

	/**
	 * ȡ�õ�ǰ���������ļ��б�
	 * 
	 * @return
	 * @throws IOException
	 */
	public List getFiles() throws IOException {
		List files = new ArrayList();
		try {
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isFile()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					files.add(name);
				}
			}
			return files;
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			FTPFile[] remoteFiles = ftpClient.listFiles();
			if (remoteFiles == null || remoteFiles.length == 0) {
				System.out.println("There has not any file!");
			}
			for (int i = 0; i < remoteFiles.length; i++) {
				if (remoteFiles[i].isFile()) {
					String name = iso8859togbk(remoteFiles[i].getName());
					files.add(name);
				}
			}
			return files;
		}
	}

	/**
	 * �ϴ�һ���ļ���FTP�����������ض����ִ洢
	 * 
	 * @param localFilePath
	 * @param newFileName
	 */
	public void uploadFile(String localFilePath, String newFileName)
			throws IOException {
		BufferedInputStream buffIn = null;
		try {
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			buffIn = new BufferedInputStream(new FileInputStream(localFilePath));
			ftpClient.storeFile(newFileName, buffIn);
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			buffIn = new BufferedInputStream(new FileInputStream(localFilePath));
			ftpClient.storeFile(newFileName, buffIn);
		} finally {
			try {
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ϴ�һ���ļ���FTP�����������ض����ִ洢�����洢�ļ���¼
	 * 
	 * @param localFilePath
	 * @param newFileName
	 */
	public void uploadFileRecord(String localFilePath, String newFileName)
			throws IOException {
		BufferedInputStream buffIn = null;
		try {
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			buffIn = new BufferedInputStream(new FileInputStream(localFilePath));
			ftpClient.storeFile(newFileName, buffIn);
			setFileRecord(iso8859togbk(newFileName), iso8859togbk(ftpClient
					.printWorkingDirectory()
					+ "/" + newFileName));
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
			buffIn = new BufferedInputStream(new FileInputStream(localFilePath));
			ftpClient.storeFile(newFileName, buffIn);
			setFileRecord(iso8859togbk(newFileName), iso8859togbk(ftpClient
					.printWorkingDirectory()
					+ "/" + newFileName));
		} finally {
			try {
				if (buffIn != null)
					buffIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ���������ļ��������ݿ⣬Ϊ�˼���
	 * 
	 * @param name
	 * @param folder
	 */
	public void setFileRecord(String name, String folder) {
		InputStream in = null;
		BufferedReader br = null;
		HttpURLConnection con = null;
		try {
			String serverurl = "http://"
					+ server
					+ "/oabase/servlet/CreateFileServlet?"
					+ "resUUID=62346234-11946f9b20f-523cb976288fcca3e68af2044e689921&"
					+ "result=login&"
					+ "LTPAToken="
					+ "bGl6aGlhbmcqNGJhZjBiYzEtMTA1OWRiNmM5MDItZTYwMGQ3ZDYwYjUyMjkwNThmYjk3ZjQyNTUzMDgyZDYqMjMqU3RyaW5n";
			System.out.println(serverurl);
			URL url = new URL(serverurl);
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			String a = Base64.encode(name.getBytes());
			String b = Base64.encode(folder.getBytes());
			a = Base64.replace(a, "/", "-");
			a = Base64.replace(a, "+", "|");
			b = Base64.replace(b, "/", "-");
			b = Base64.replace(b, "+", "|");
			String pro = "name=" + a + "&folder=" + b + "&createtime="
					+ (new Date()).getTime();
			System.out.println(pro);
			con.getOutputStream().write(pro.getBytes());
			con.getOutputStream().flush();
			con.getOutputStream().close();
			in = con.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (con != null)
				con.disconnect();
		}
	}

	/**
	 * �ϴ��ļ���ftp������
	 * 
	 * @param localFilePath
	 */
	public void uploadFile(String localFilePath) throws IOException {
		uploadFileRecord(localFilePath,
				gbktoiso8859(getFileName(localFilePath)));
		fileBackUp(localFilePath);
	}

	/**
	 * �����ϴ��ļ���FTP������
	 * 
	 * @param files
	 */
	public void uploadBatch(String files) throws IOException {
		StringTokenizer stk = new StringTokenizer(files, ",");
		while (stk.hasMoreElements()) {
			uploadFile(stk.nextToken());
		}
	}

	/**
	 * ������FTP�����������ļ�
	 * 
	 * @param files
	 * @param localFileName
	 */
	public void downloadBatch(String files, String localFileName)
			throws IOException {
		StringTokenizer stk = new StringTokenizer(files, ",");
		while (stk.hasMoreElements()) {
			downloadFile(stk.nextToken(), localFileName);
		}
	}

	/**
	 * ��FTP����������һ���ļ�
	 * 
	 * @param remoteFileName
	 * @param localFileName
	 */
	public void downloadFile(String remoteFileName, String localFileName)
			throws IOException {
		localFileName = localFileName + "\\" + remoteFileName;
		BufferedOutputStream buffOut = null;
		try {
			buffOut = new BufferedOutputStream(new FileOutputStream(
					localFileName));
			ftpClient.retrieveFile(this.gbktoiso8859(remoteFileName), buffOut);
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			buffOut = new BufferedOutputStream(new FileOutputStream(
					localFileName));
			ftpClient.retrieveFile(this.gbktoiso8859(remoteFileName), buffOut);
		} finally {
			try {
				if (buffOut != null)
					buffOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �жϱ����ļ����Ƿ����
	 * 
	 * @param path
	 * @return
	 */
	public boolean isBackUpFolder(String name) {
		try {
			List folders = getFolders();
			for (int i = 0; i < folders.size(); i++) {
				if ((name + ".bak").equals((String) folders.get(i))) {
					return true;
				}
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * �ļ����ݣ����ļ����ݵ�.bak�ļ���
	 * 
	 * @param filepath
	 */
	public void fileBackUp(String filepath) {
		try {
			String cur = ftpClient.printWorkingDirectory();
			ftpClient.changeToParentDirectory();
			String dirname = getFtpFolderName(cur);
			if (isBackUpFolder(dirname)) {
				ftpClient.changeWorkingDirectory(ftpClient
						.printWorkingDirectory()
						+ "/" + dirname + ".bak");
				uploadFile(filepath,
						gbktoiso8859(getBackUpName(getFileName(filepath))));
			} else {
				ftpClient.makeDirectory(dirname + ".bak");
				ftpClient.changeWorkingDirectory(ftpClient
						.printWorkingDirectory()
						+ "/" + dirname + ".bak");
				uploadFile(filepath,
						gbktoiso8859(getBackUpName(getFileName(filepath))));
			}
			ftpClient.changeWorkingDirectory(cur);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ڵ�ǰ��������ɾ���ļ���
	 * 
	 * @param name
	 */
	public String deleteFolder(String name) throws IOException {
		try {
			String cur = ftpClient.printWorkingDirectory();
			ftpClient.changeWorkingDirectory(cur + "/" + name);
			List curfolder = getFolders();
			List curfile = getFiles();
			if (curfolder.size() == 2) {
				for (int i = 0; i < curfile.size(); i++) {
					this.deleteFile((String) curfile.get(i));
				}
				ftpClient.changeToParentDirectory();
				ftpClient.removeDirectory(gbktoiso8859(name));
				return "";
			} else {
				ftpClient.changeToParentDirectory();
				return name;
			}
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			String cur = ftpClient.printWorkingDirectory();
			ftpClient.changeWorkingDirectory(cur + "/" + name);
			List curfolder = getFolders();
			List curfile = getFiles();
			if (curfolder.size() == 2) {
				for (int i = 0; i < curfile.size(); i++) {
					this.deleteFile((String) curfile.get(i));
				}
				ftpClient.changeToParentDirectory();
				ftpClient.removeDirectory(gbktoiso8859(name));
				return "";
			} else {
				ftpClient.changeToParentDirectory();
				return name;
			}
		}
	}

	/**
	 * �ڵ�ǰ��������ɾ���ļ�
	 * 
	 * @param name
	 */
	public void deleteFile(String name) {
		try {
			ftpClient.deleteFile(gbktoiso8859(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ɾ���ļ����ļ���
	 * 
	 * @param folders
	 * @param files
	 */
	public String deleteBatch(String folders, String files) throws IOException {
		List delstatus = new ArrayList();
		String delstr = "";
		if (!"".equals(folders) || folders != null) {
			StringTokenizer stk = new StringTokenizer(folders, ",");
			while (stk.hasMoreElements()) {
				String temp = deleteFolder(stk.nextToken());
				if (!"".equals(temp)) {
					delstatus.add(temp);
				}
			}
			for (int i = 0; i < delstatus.size(); i++) {
				delstr = delstr + "," + delstatus.get(i);
			}
		}
		if (!"".equals(files) || files != null) {
			StringTokenizer stk = new StringTokenizer(files, ",");
			while (stk.hasMoreElements()) {
				this.deleteFile(stk.nextToken());
			}
		}
		if (delstatus.size() != 0) {
			return delstr;
		} else {
			return "-1";
		}
	}

	/**
	 * ��ñ����ļ����ļ���
	 * 
	 * @param name
	 * @return
	 */
	public String getBackUpName(String name) {
		int index = name.lastIndexOf(".");
		String time = new SimpleDateFormat("yyyy��MM��dd�� hhʱmm��ss��")
				.format(new Date());
		if (index >= 0) {
			String mainname = name.substring(0, index);
			String extname = name.substring(index + 1, name.length());
			mainname = mainname + "(" + time + ")";
			return mainname + "." + extname;
		} else {
			return name + "(" + time + ")";
		}

	}

	/**
	 * �ж�һ���ļ��������Ƿ���ftp�������ϴ���
	 * 
	 * @param name
	 * @return
	 */
	public boolean isExist(String name) throws IOException {
		try {
			FTPFile[] remoteFiles = ftpClient.listFiles();
			for (int i = 0; i < remoteFiles.length; i++) {
				if (iso8859togbk(remoteFiles[i].getName()).equals(name)
						&& !remoteFiles[i].isDirectory()) {
					return true;
				}
			}
			return false;
		} catch (SocketException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			connectServer(host, port, username, password);
			FTPFile[] remoteFiles = ftpClient.listFiles();
			for (int i = 0; i < remoteFiles.length; i++) {
				if (iso8859togbk(remoteFiles[i].getName()).equals(name)
						&& !remoteFiles[i].isDirectory()) {
					return true;
				}
			}
			return false;
		}

	}

	/**
	 * �������֣�����key�����ֹ��˵�
	 * 
	 * @param name
	 * @param key
	 * @return
	 */
	private String showFilter(String name, String key) {
		int index = name.lastIndexOf(".");
		String extend = name.substring(index + 1, name.length());
		if (extend.equals(key)) {
			return "";
		} else {
			return "," + name;
		}
	}

	/**
	 * ͨ��������·�������ط������ļ�����
	 * 
	 * @param path
	 * @return
	 */
	public String getFtpFolderName(String path) {
		return path.substring(path.lastIndexOf("/") + 1, path.length());
	}

	/**
	 * ͨ���ļ�·�������ļ���
	 * 
	 * @param path
	 * @return
	 */
	public String getFileName(String path) {
		return path.substring(path.lastIndexOf("\\") + 1, path.length());
	}

	/**
	 * �ж�һ��Ŀ¼�Ƿ�Ϊ��Ŀ¼
	 * 
	 * @param path
	 * @return
	 */
	public boolean checkRoot(String path) {
		path = path.substring(1, path.length());
		if (path.indexOf("/") == path.lastIndexOf("/")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ַ�ת�룬��ISO-8859-1ת����GBK
	 * 
	 * @param obj
	 * @return
	 */
	private String iso8859togbk(Object obj) {
		try {
			if (obj == null)
				return "";
			else
				return new String(obj.toString().getBytes("iso-8859-1"), "GBK");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * �ַ�ת�룬��GBKת����ISO-8859-1
	 * 
	 * @param obj
	 * @return
	 */
	private String gbktoiso8859(Object obj) {
		try {
			if (obj == null)
				return "";
			else
				return new String(obj.toString().getBytes("GBK"), "iso-8859-1");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * ��FTP�������Ͽ�����
	 * 
	 * @throws IOException
	 */
	public void disconnectServer() throws IOException {
		ftpClient.disconnect();
	}

	/**
	 * ��������
	 */
	public void run() {

		// URL url = new
		// URL("http://10.102.1.40/oabase/servlet/CreateFileServlet?resUUID=62346234-11946f9b20f-523cb976288fcca3e68af2044e689921&result=login&LTPAToken=bGl6aGlhbmcqNGJhZjBiYzEtMTA1OWRiNmM5MDItZTYwMGQ3ZDYwYjUyMjkwNThmYjk3ZjQyNTUzMDgyZDYqMjMqU3RyaW5n");
		// HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// con.setDoOutput(true);
		// con.setRequestMethod("POST");
		// String pro = "name=123&folder=456";
		// con.getOutputStream().write(pro.getBytes());
		// con.getOutputStream().flush();
		// con.getOutputStream().close();
		// InputStream in = con.getInputStream();
		// BufferedReader br = new BufferedReader(new InputStreamReader(in));
		// String line = "";
		// while((line = br.readLine())!=null){
		// System.out.println(line);
		// }

		try {
			this.connectServer("10.102.1.40", 21, "filemanager", "filemanager");
			// this.setServer("10.102.1.37");
			this.getAllFolders("/�ܱ��ҹ���Ŀ¼/Ŀ¼��");
			// this.uploadFile("E:\\��\\��.txt");
			System.out.print(ftpClient.printWorkingDirectory());
			downloadFile("��1.doc", "E:\\");
			// this.downloadFile("��������1.txt", "c:\\");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
}
