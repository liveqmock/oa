/*
 * �������� 2005-12-28
 */
package com.icss.oa.filetransfer.util;

/**
 * �ļ����ݵĺ�̨�߳�
 * ��������շ��ʼ�ʱ��������ʱ�ļ����г�ʱ���ļ���
 * 2005-12-28 12:37:37
 */
public class FiletransferDaemonThread extends Thread{
	
	//�ػ��߳�˯��ʱ��
	private long sleeptime;
	//�Ƿ�������еı�־λ
	private boolean running = false;
	
	public FiletransferDaemonThread(long sleeptime){
		//����Ϊ�ػ��̣߳�ʹ���߳��˳�ʱ�ػ��߳�Ҳ�˳�
		this.setDaemon(true);
		//�����ػ��̵߳�˯��ʱ��
		this.sleeptime = sleeptime;
	}
	
	public void run(){
		System.out.println("FiletransferDaemonThread.run()");
		while(running){
			//ִ�������ʱ�ļ��Ĳ���
			FiletransferUtil.clearFiletransferTempDir();
			try {
				//�ػ��߳�˯��ָ����ʱ��
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("FiletransferDaemonThread stopped.");
	}
	
	/**
	 * �ػ��߳̿�ʼ����
	 */
	public void startRunning(){
		//�������б�־λ
		this.running = true;
		//�����߳�
		this.start();
	}
	
	/**
	 * �ػ��߳̽�������
	 */
	public void stopRunning(){
		//�������б�־λ
		this.running = false;
	}
	
	
}







