/*
 * 创建日期 2005-12-28
 */
package com.icss.oa.filetransfer.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.icss.j2ee.util.Globals;

/**
 * 文件传递路径工具
 * 2005-12-28 11:37:37
 */
public class FiletransferUtil {

	//守护线程睡眠时间——3小时
	public static long DAEMONTHREAD_SLEEPTIME = 1000 * 60 * 60 * 3;
	//private static long DAEMONTHREAD_SLEEPTIME = 1000 * 10;

	//文件传递守护线程
	private static FiletransferDaemonThread daemonThread;
	static {
		//创建守护线程
		daemonThread = new FiletransferDaemonThread(DAEMONTHREAD_SLEEPTIME);
		//启动守护线程
		daemonThread.startRunning();
		//输出守护线程启动的信息
		System.out.println("FiletransferDaemonThread started.");
	}

	/**
	 * 取得邮件附件的下载路径
	 * 每天创建一个临时文件夹用于保存邮件中的附件
	 * 
	 * @return	返回的路径可能是：
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-26/
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-27/
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-28/
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-29/
	 */
	public static String getDownloadFilePath() {
		//以日期构造日志文件对象
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//以当前日期构造临时文件下载路径
		String downloadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "downloadfile" + File.separator + today + File.separator;
		//构造下载路径的File对象
		File downloadfileDir = new File(downloadfilePath);
		//如果此路径不存在，则要创建对应的文件夹
		if (!downloadfileDir.exists()) {
			downloadfileDir.mkdirs();
		}
		//返回对应于当前日期的邮件附件下载路径
		return downloadfilePath;
	}

	/**
	 * 取得邮件附件的上传路径
	 * 每天创建一个临时文件夹用于保存邮件中的附件
	 * 
	 * @return	返回的路径可能是：
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-26/
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-27/
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-28/
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-29/
	 */
	public static String getUploadFilePath() {
		//取得当前日期
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//获取当前日期对应的上传临时文件夹的路径名称
		String uploadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "uploadfile" + File.separator + today + File.separator;
		//创建此上传路径的File对象
		File uploadfileDir = new File(uploadfilePath);
		//如果此路径不存在，则要创建对应的文件夹
		if (!uploadfileDir.exists()) {
			uploadfileDir.mkdirs();
		}
		//返回对应于当前日期的邮件附件上传路径
		return uploadfilePath;
	}

	/**
	 * 清除文件传递的临时文件夹
	 * 只清除当前日期以前创建的临时文件夹
	 */
	public static void clearFiletransferTempDir() {
		System.out.println("FiletransferUtil.clearFiletransferTempDir() started.");
		//清除下载临时文件夹
		clearDownloadTempDir();
		//清除上传临时文件夹
		clearUploadTempDir();
		System.out.println("FiletransferUtil.clearFiletransferTempDir() finished.");
	}

	/**
	 * 清除下载临时文件夹
	 * 
	 * 例如，程序经过一段时间的运行后保存下载临时文件的文件夹可能是如下情况：
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-26/
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-27/
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-28/
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-29/
	 * 
	 * 如果当前日期是2005年12月28日，那么此方法执行后应该删除此日期之前的文件夹，删除之后的结果为：
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-29/
	 */
	private static void clearDownloadTempDir() {

		//下载临时文件夹，例如：/ResourceOneHome/filetransfer/downloadfile/
		String downloadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "downloadfile";
		//构造此文件夹的File对象
		File downloadfileDir = new File(downloadfilePath);

		//如果这个File对象是文件夹
		if (downloadfileDir.isDirectory()) {

			//日期格式化类
			DateFormat dateFormat = DateFormat.getDateInstance();

			//取得当前日期
			Date today = new Date();
			//当前日期的字符串
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(today);

			//取得这个文件中的所有子文件夹
			File[] tempdirs = downloadfileDir.listFiles();
			//返回null时的处理
			tempdirs = tempdirs == null ? new File[0] : tempdirs;

			//遍历所有的子文件夹，每个文件夹以日期命名，保存了那个日期中程序生成的临时文件
			for (int i = 0; i < tempdirs.length; i++) {

				File file = tempdirs[i];
				//取得子文件夹的名称，应为日期的格式，例如：“2005-12-28”
				String fileName = file.getName();
				//取得文件的完整路径名称
				String fileFullName = file.getAbsolutePath();

				try {
					//把文件夹名称解析为日期Date对象
					Date dirDate = dateFormat.parse(fileName);
					//比较文件夹对应的日期与当前日期
					if ( (!fileName.equals(todayStr)) && dirDate.before(today)) {
						//System.out.println("dirDate = " + dirDate);
						//System.out.println("today = " + today);						
						System.out.println("delete " + fileFullName);
						//如果文件夹的日期是当前日期之前的日期，则删除这个文件夹
						deleteDirectory(file);
					}

				} catch (ParseException e) {
					System.out.println("文件传递临时文件解析错误！文件名称为：" + fileFullName);
					System.err.println("文件传递临时文件解析错误！文件名称为：" + fileFullName);
					e.printStackTrace();

				} catch (Exception e) {
					System.out.println("文件传递临时文件删除错误！文件名称为：" + fileFullName);
					System.err.println("文件传递临时文件删除错误！文件名称为：" + fileFullName);
					e.printStackTrace();

				}

			}

		}

	}

	/**
	 * 清除上传临时文件夹
	 * 
	 * 例如，程序经过一段时间的运行后保存下载临时文件的文件夹可能是如下情况：
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-26/
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-27/
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-28/
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-29/
	 * 
	 * 如果当前日期是2005年12月28日，那么此方法执行后应该删除此日期之前的文件夹，删除之后的结果为：
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-29/
	 */
	private static void clearUploadTempDir() {

		//上传临时文件夹，例如：/ResourceOneHome/filetransfer/uploadfile/
		String uploadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "uploadfile";
		//构造此文件夹的File对象
		File uploadfileDir = new File(uploadfilePath);

		//如果这个File对象是文件夹
		if (uploadfileDir.isDirectory()) {

			//日期格式化类
			DateFormat dateFormat = DateFormat.getDateInstance();

			//取得当前日期
			Date today = new Date();
			//当前日期的字符串
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(today);
			
			//取得这个文件中的所有子文件夹
			File[] tempdirs = uploadfileDir.listFiles();
			//返回null时的处理
			tempdirs = tempdirs == null ? new File[0] : tempdirs;

			//遍历所有的子文件夹，每个文件夹以日期命名，保存了那个日期中程序生成的临时文件
			for (int i = 0; i < tempdirs.length; i++) {

				File file = tempdirs[i];
				//取得子文件夹的名称，应为日期的格式，例如：“2005-12-28”
				String fileName = file.getName();
				//取得文件的完整路径名称
				String fileFullName = file.getAbsolutePath();

				try {
					//把文件夹名称解析为日期Date对象
					Date dirDate = dateFormat.parse(fileName);
					//比较文件夹对应的日期与当前日期
					if ( (!fileName.equals(todayStr)) && dirDate.before(today)) {
						//System.out.println("dirDate = " + dirDate);
						//System.out.println("today = " + today);
						System.out.println("delete " + fileFullName);
						//如果文件夹的日期是当前日期之前的日期，则删除这个文件夹
						deleteDirectory(file);
					}

				} catch (ParseException e) {
					System.out.println("文件传递临时文件解析错误！文件名称为：" + fileFullName);
					System.err.println("文件传递临时文件解析错误！文件名称为：" + fileFullName);
					e.printStackTrace();

				} catch (Exception e) {
					System.out.println("文件传递临时文件删除错误！文件名称为：" + fileFullName);
					System.err.println("文件传递临时文件删除错误！文件名称为：" + fileFullName);
					e.printStackTrace();

				}

			}

		}

	}

	/**
	 * 删除指定的File对象
	 * @param file
	 */
	public static void deleteDirectory(File file) {
		//取得文件的完整路径名称
		String fileFullName = file.getAbsolutePath();

		if (file.isDirectory()) {
			//如果File对象是文件夹，则获取这个文件夹中的所有文件对象的数组
			File[] subFiles = file.listFiles();
			//遍历子文件数组
			for (int i = 0; i < subFiles.length; i++) {
				//取得当前一个子文件对象
				File subfile = subFiles[i];
				//递归执行删除
				deleteDirectory(subfile);
			}

		}

		try {
			//如果File对象是文件，则直接删除文件
			file.delete();
		} catch (Exception e) {
			System.out.println("文件传递临时文件删除错误！文件名称为：" + fileFullName);
			System.err.println("文件传递临时文件删除错误！文件名称为：" + fileFullName);
			e.printStackTrace();

		}

	}
	
	public static void main(String[] args) {
		Globals.resourceOneHome = "C:\\ResourceOneHome";
		System.out.println("FiletransferUtil.getDownloadFilePath() = " + FiletransferUtil.getDownloadFilePath());
		System.out.println("FiletransferUtil.getUploadFilePath() = " + FiletransferUtil.getUploadFilePath());
	}
	
	public static void main1(String[] args) {

		Globals.resourceOneHome = "C:\\ResourceOneHome";

		//下载临时文件夹，例如在Linux操作系统中为：/ResourceOneHome/filetransfer/downloadfile/
		String downloadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "downloadfile";
		//下载临时文件夹，例如在Linux操作系统中为：/ResourceOneHome/filetransfer/uploadfile/
		String uploadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "uploadfile";

		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		System.out.println("today = " + today);

		try {
			DateFormat dateFormat = DateFormat.getDateInstance();
			Date todayDate = dateFormat.parse(today);
			System.out.println("todayDate = " + todayDate);

			String[] dates = new String[31];
			for (int i = 0; i < dates.length; i++) {
				dates[i] = "2005-12-" + (i + 1);
				File downloadDir = new File(downloadfilePath + File.separator + dates[i]);
				if (!downloadDir.exists()) {
					//创建下载临时文件夹
					downloadDir.mkdirs();
				}
				File uploadDir = new File(uploadfilePath + File.separator + dates[i]);
				if (!uploadDir.exists()) {
					//创建上传临时文件夹
					uploadDir.mkdirs();
				}
			}

			for (int i = 0; i < dates.length; i++) {
				String day = dates[i];
				Date dayDate = dateFormat.parse(day);
				System.out.println(dayDate.compareTo(todayDate) + " " + dayDate.before(todayDate));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(1000 * 60);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		daemonThread.stopRunning();
		try {
			Thread.sleep(1000 * 30);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

}
