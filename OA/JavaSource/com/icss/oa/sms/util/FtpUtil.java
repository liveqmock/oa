package com.icss.oa.sms.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FtpUtil {

	/**
	 * @param host FTP地址
	 * @param port 端口号
	 * @param userName 用户名
	 * @param password 密码
	 * @param pathFrom 远端文件路径
	 * @param pathTo 下载后文件存放目录路径
	 * @param binary 传输文件类型
	 * @param isRemoveRemoteFile 下载后是否删除远端文件
	 * @throws IOException
	 */
	public static void downloadFile(String host, int port, String userName,
			String password, String pathFrom, String pathTo, boolean binary,
			boolean isRemoveRemoteFile) throws IOException {
		downloadFile(host, port, userName, password, new String[] { pathFrom },
				pathTo, binary, isRemoveRemoteFile);
	}

	/**
	 * 
	 * @param host FTP地址
	 * @param port 端口号
	 * @param userName 用户名
	 * @param password 密码
	 * @param pathFrom 远端文件路径
	 * @param pathTo 下载后文件存放目录路径
	 * @param binary 传输文件类型
	 * @param isRemoveRemoteFile 下载后是否删除远端文件
	 * @throws IOException
	 */
	public static void downloadFile(String host, int port, String userName,
			String password, String[] pathFrom, String pathTo, boolean binary,
			boolean isRemoveRemoteFile) throws IOException {

		FTPClient ftp = new FTPClient();
		ftp.connect(host, port);
		ftp.login(userName, password);
		downloadFile(ftp, pathFrom, pathTo, binary, isRemoveRemoteFile);
		ftp.disconnect();
		}

	/**
	 * 
	 * @param host FTP地址
	 * @param port 端口号
	 * @param userName 用户名
	 * @param password 密码
	 * @param pathFrom 远端文件路径
	 * @param pathTo 下载后文件存放目录路径
	 * @param binary 传输文件类型
	 * @param isRemoveRemoteFile 下载后是否删除远端文件
	 * @throws IOException
	 */
	public static void downloadFile(FTPClient ftp, String pathFrom,
			String pathTo, boolean binary, boolean isRemoveRemoteFile)
			throws IOException {
		downloadFile(ftp, new String[] { pathFrom }, pathTo, binary,
				isRemoveRemoteFile);
	}

	public static void downloadFile(FTPClient ftp, String[] pathFrom,
			String pathTo, boolean binary, boolean isRemoveRemoteFile)
			throws IOException {
		FileOutputStream fos = null;
		try {
			if (binary)
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
			else
				ftp.setFileType(FTP.ASCII_FILE_TYPE);

			File pathToDir = new File(pathTo);
			if (!pathToDir.exists())
				pathToDir.mkdirs();

			
			for (int i = 0; i < pathFrom.length; i++) {
				String fileName = pathFrom[i].substring(pathFrom[i]
						.lastIndexOf("/") + 1);
				String filePath = new StringBuffer().append(pathTo).append(
						File.separator).append(fileName).toString();
				fos = new FileOutputStream(filePath);

				ftp.retrieveFile(pathFrom[i], fos);
				if (isRemoveRemoteFile) {
					ftp.deleteFile(pathFrom[i]);
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}

	}

	private static void uploadfile(FTPClient f, File file, String path,
			boolean binary) throws Exception {
		BufferedInputStream buffIn = null;
		try {
			if (path != null) {
				f.cwd(path);
				}
			if (binary) {
				f.setFileType(FTP.BINARY_FILE_TYPE);
			} else {
				f.setFileType(FTP.ASCII_FILE_TYPE);
			}
			buffIn = new BufferedInputStream(new FileInputStream(file));
			f.storeFile(file.getName(), buffIn);
		} catch (Exception e) {
			throw e;
		} finally {
			if (buffIn != null) {
				buffIn.close();
			}

		}
	}

	private static void uploadfile(FTPClient f, String file, String path,
			boolean binary) throws Exception {
		uploadfile(f, new File(file), path, binary);
	}

	private static void uploadfiles(FTPClient f, List files, String path,
			boolean binary) throws Exception {
		if (files == null) {
			return;
		}
		for (int i = 0; i < files.size(); i++) {
			Object ob = files.get(i);
			if (ob instanceof String) {
				uploadfile(f, (String) ob, path, binary);
			}
			if (ob instanceof File) {
				uploadfile(f, (File) ob, path, binary);
			}

		}
	}

	public static void uploadfile(String ip, String port, String user,
			String pw, String file, String path, boolean binary)
			throws Exception {

		FTPClient f = new FTPClient();
		f.connect(ip, (new Integer(port)).intValue());
		f.login(user, pw);
		uploadfile(f, file, path, binary);
		f.logout();
		f.disconnect();
	}

	public static void uploadfile(String ip, String port, String user,
			String pw, File file, String path, boolean binary) throws Exception {
		FTPClient f = new FTPClient();
		f.connect(ip, (new Integer(port)).intValue());
		f.login(user, pw);
		uploadfile(f, file, path, binary);
		f.disconnect();
	}

	public static void uploadfiles(String ip, String port, String user,
			String pw, List files, String path, boolean binary)
			throws Exception {
		FTPClient f = new FTPClient();
		f.connect(ip, (new Integer(port)).intValue());
		f.login(user, pw);
		uploadfiles(f, files, path, binary);
		f.disconnect();
		
		
	}
	
	public static void newnullfile(String ip, String port, String user, String pw,
			String filename, String path) throws Exception {
		
		FTPClient f = new FTPClient();
		f.connect(ip, (new Integer(port)).intValue());
		f.login(user, pw);
		
		if (path != null) {
			f.cwd(path);
			}
		InputStream in = new ByteArrayInputStream("".getBytes());
		f.storeFile(filename,in);
		in.close();		
		f.disconnect();
		
}
	public static void isAlive(String ip, String port, String user, String pw) throws NumberFormatException, SocketException, IOException{
		FTPClient f = new FTPClient();
		f.connect(ip, (new Integer(port)).intValue());
		f.login(user, pw);
		f.logout();
		f.disconnect();
	}


// public static void main(String[] args) {
//		BufferedInputStream buffIn = null;
//		try {
//			System.out.println("Hello1");
//			FTPClient f = new FTPClient();
//			f.connect("172.16.143.70", 21);
//			f.login("sms", "sms");
//			
//			System.out.println("Hello");
//			
//			f.setFileType(FTP.BINARY_FILE_TYPE);
//			buffIn = new BufferedInputStream(new FileInputStream("c:/1.txt"));
//			
//			f.disconnect();
//		} catch (Exception e) {
//			if (buffIn != null)
//				try {
//					buffIn.close();
//				} catch (IOException e1) {
//					// TODO 自动生成 catch 块
//					e1.printStackTrace();
//				}
//			e.printStackTrace();
//		}
//	}
}
