/*
 * �������� 2005-12-28
 */
package com.icss.oa.filetransfer.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.icss.j2ee.util.Globals;

/**
 * �ļ�����·������
 * 2005-12-28 11:37:37
 */
public class FiletransferUtil {

	//�ػ��߳�˯��ʱ�䡪��3Сʱ
	public static long DAEMONTHREAD_SLEEPTIME = 1000 * 60 * 60 * 3;
	//private static long DAEMONTHREAD_SLEEPTIME = 1000 * 10;

	//�ļ������ػ��߳�
	private static FiletransferDaemonThread daemonThread;
	static {
		//�����ػ��߳�
		daemonThread = new FiletransferDaemonThread(DAEMONTHREAD_SLEEPTIME);
		//�����ػ��߳�
		daemonThread.startRunning();
		//����ػ��߳���������Ϣ
		System.out.println("FiletransferDaemonThread started.");
	}

	/**
	 * ȡ���ʼ�����������·��
	 * ÿ�촴��һ����ʱ�ļ������ڱ����ʼ��еĸ���
	 * 
	 * @return	���ص�·�������ǣ�
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-26/
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-27/
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-28/
	 * 			/ResourceOneHome/filetransfer/downloadfile/2005-12-29/
	 */
	public static String getDownloadFilePath() {
		//�����ڹ�����־�ļ�����
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//�Ե�ǰ���ڹ�����ʱ�ļ�����·��
		String downloadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "downloadfile" + File.separator + today + File.separator;
		//��������·����File����
		File downloadfileDir = new File(downloadfilePath);
		//�����·�������ڣ���Ҫ������Ӧ���ļ���
		if (!downloadfileDir.exists()) {
			downloadfileDir.mkdirs();
		}
		//���ض�Ӧ�ڵ�ǰ���ڵ��ʼ���������·��
		return downloadfilePath;
	}

	/**
	 * ȡ���ʼ��������ϴ�·��
	 * ÿ�촴��һ����ʱ�ļ������ڱ����ʼ��еĸ���
	 * 
	 * @return	���ص�·�������ǣ�
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-26/
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-27/
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-28/
	 * 			/ResourceOneHome/filetransfer/uploadfile/2005-12-29/
	 */
	public static String getUploadFilePath() {
		//ȡ�õ�ǰ����
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//��ȡ��ǰ���ڶ�Ӧ���ϴ���ʱ�ļ��е�·������
		String uploadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "uploadfile" + File.separator + today + File.separator;
		//�������ϴ�·����File����
		File uploadfileDir = new File(uploadfilePath);
		//�����·�������ڣ���Ҫ������Ӧ���ļ���
		if (!uploadfileDir.exists()) {
			uploadfileDir.mkdirs();
		}
		//���ض�Ӧ�ڵ�ǰ���ڵ��ʼ������ϴ�·��
		return uploadfilePath;
	}

	/**
	 * ����ļ����ݵ���ʱ�ļ���
	 * ֻ�����ǰ������ǰ��������ʱ�ļ���
	 */
	public static void clearFiletransferTempDir() {
		System.out.println("FiletransferUtil.clearFiletransferTempDir() started.");
		//���������ʱ�ļ���
		clearDownloadTempDir();
		//����ϴ���ʱ�ļ���
		clearUploadTempDir();
		System.out.println("FiletransferUtil.clearFiletransferTempDir() finished.");
	}

	/**
	 * ���������ʱ�ļ���
	 * 
	 * ���磬���򾭹�һ��ʱ������к󱣴�������ʱ�ļ����ļ��п��������������
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-26/
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-27/
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-28/
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-29/
	 * 
	 * �����ǰ������2005��12��28�գ���ô�˷���ִ�к�Ӧ��ɾ��������֮ǰ���ļ��У�ɾ��֮��Ľ��Ϊ��
	 * /ResourceOneHome/filetransfer/downloadfile/2005-12-29/
	 */
	private static void clearDownloadTempDir() {

		//������ʱ�ļ��У����磺/ResourceOneHome/filetransfer/downloadfile/
		String downloadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "downloadfile";
		//������ļ��е�File����
		File downloadfileDir = new File(downloadfilePath);

		//������File�������ļ���
		if (downloadfileDir.isDirectory()) {

			//���ڸ�ʽ����
			DateFormat dateFormat = DateFormat.getDateInstance();

			//ȡ�õ�ǰ����
			Date today = new Date();
			//��ǰ���ڵ��ַ���
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(today);

			//ȡ������ļ��е��������ļ���
			File[] tempdirs = downloadfileDir.listFiles();
			//����nullʱ�Ĵ���
			tempdirs = tempdirs == null ? new File[0] : tempdirs;

			//�������е����ļ��У�ÿ���ļ����������������������Ǹ������г������ɵ���ʱ�ļ�
			for (int i = 0; i < tempdirs.length; i++) {

				File file = tempdirs[i];
				//ȡ�����ļ��е����ƣ�ӦΪ���ڵĸ�ʽ�����磺��2005-12-28��
				String fileName = file.getName();
				//ȡ���ļ�������·������
				String fileFullName = file.getAbsolutePath();

				try {
					//���ļ������ƽ���Ϊ����Date����
					Date dirDate = dateFormat.parse(fileName);
					//�Ƚ��ļ��ж�Ӧ�������뵱ǰ����
					if ( (!fileName.equals(todayStr)) && dirDate.before(today)) {
						//System.out.println("dirDate = " + dirDate);
						//System.out.println("today = " + today);						
						System.out.println("delete " + fileFullName);
						//����ļ��е������ǵ�ǰ����֮ǰ�����ڣ���ɾ������ļ���
						deleteDirectory(file);
					}

				} catch (ParseException e) {
					System.out.println("�ļ�������ʱ�ļ����������ļ�����Ϊ��" + fileFullName);
					System.err.println("�ļ�������ʱ�ļ����������ļ�����Ϊ��" + fileFullName);
					e.printStackTrace();

				} catch (Exception e) {
					System.out.println("�ļ�������ʱ�ļ�ɾ�������ļ�����Ϊ��" + fileFullName);
					System.err.println("�ļ�������ʱ�ļ�ɾ�������ļ�����Ϊ��" + fileFullName);
					e.printStackTrace();

				}

			}

		}

	}

	/**
	 * ����ϴ���ʱ�ļ���
	 * 
	 * ���磬���򾭹�һ��ʱ������к󱣴�������ʱ�ļ����ļ��п��������������
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-26/
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-27/
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-28/
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-29/
	 * 
	 * �����ǰ������2005��12��28�գ���ô�˷���ִ�к�Ӧ��ɾ��������֮ǰ���ļ��У�ɾ��֮��Ľ��Ϊ��
	 * /ResourceOneHome/filetransfer/uploadfile/2005-12-29/
	 */
	private static void clearUploadTempDir() {

		//�ϴ���ʱ�ļ��У����磺/ResourceOneHome/filetransfer/uploadfile/
		String uploadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "uploadfile";
		//������ļ��е�File����
		File uploadfileDir = new File(uploadfilePath);

		//������File�������ļ���
		if (uploadfileDir.isDirectory()) {

			//���ڸ�ʽ����
			DateFormat dateFormat = DateFormat.getDateInstance();

			//ȡ�õ�ǰ����
			Date today = new Date();
			//��ǰ���ڵ��ַ���
			String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(today);
			
			//ȡ������ļ��е��������ļ���
			File[] tempdirs = uploadfileDir.listFiles();
			//����nullʱ�Ĵ���
			tempdirs = tempdirs == null ? new File[0] : tempdirs;

			//�������е����ļ��У�ÿ���ļ����������������������Ǹ������г������ɵ���ʱ�ļ�
			for (int i = 0; i < tempdirs.length; i++) {

				File file = tempdirs[i];
				//ȡ�����ļ��е����ƣ�ӦΪ���ڵĸ�ʽ�����磺��2005-12-28��
				String fileName = file.getName();
				//ȡ���ļ�������·������
				String fileFullName = file.getAbsolutePath();

				try {
					//���ļ������ƽ���Ϊ����Date����
					Date dirDate = dateFormat.parse(fileName);
					//�Ƚ��ļ��ж�Ӧ�������뵱ǰ����
					if ( (!fileName.equals(todayStr)) && dirDate.before(today)) {
						//System.out.println("dirDate = " + dirDate);
						//System.out.println("today = " + today);
						System.out.println("delete " + fileFullName);
						//����ļ��е������ǵ�ǰ����֮ǰ�����ڣ���ɾ������ļ���
						deleteDirectory(file);
					}

				} catch (ParseException e) {
					System.out.println("�ļ�������ʱ�ļ����������ļ�����Ϊ��" + fileFullName);
					System.err.println("�ļ�������ʱ�ļ����������ļ�����Ϊ��" + fileFullName);
					e.printStackTrace();

				} catch (Exception e) {
					System.out.println("�ļ�������ʱ�ļ�ɾ�������ļ�����Ϊ��" + fileFullName);
					System.err.println("�ļ�������ʱ�ļ�ɾ�������ļ�����Ϊ��" + fileFullName);
					e.printStackTrace();

				}

			}

		}

	}

	/**
	 * ɾ��ָ����File����
	 * @param file
	 */
	public static void deleteDirectory(File file) {
		//ȡ���ļ�������·������
		String fileFullName = file.getAbsolutePath();

		if (file.isDirectory()) {
			//���File�������ļ��У����ȡ����ļ����е������ļ����������
			File[] subFiles = file.listFiles();
			//�������ļ�����
			for (int i = 0; i < subFiles.length; i++) {
				//ȡ�õ�ǰһ�����ļ�����
				File subfile = subFiles[i];
				//�ݹ�ִ��ɾ��
				deleteDirectory(subfile);
			}

		}

		try {
			//���File�������ļ�����ֱ��ɾ���ļ�
			file.delete();
		} catch (Exception e) {
			System.out.println("�ļ�������ʱ�ļ�ɾ�������ļ�����Ϊ��" + fileFullName);
			System.err.println("�ļ�������ʱ�ļ�ɾ�������ļ�����Ϊ��" + fileFullName);
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

		//������ʱ�ļ��У�������Linux����ϵͳ��Ϊ��/ResourceOneHome/filetransfer/downloadfile/
		String downloadfilePath = Globals.resourceOneHome + File.separator + "filetransfer" + File.separator + "downloadfile";
		//������ʱ�ļ��У�������Linux����ϵͳ��Ϊ��/ResourceOneHome/filetransfer/uploadfile/
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
					//����������ʱ�ļ���
					downloadDir.mkdirs();
				}
				File uploadDir = new File(uploadfilePath + File.separator + dates[i]);
				if (!uploadDir.exists()) {
					//�����ϴ���ʱ�ļ���
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
