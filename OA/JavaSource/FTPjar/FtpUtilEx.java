package FTPjar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Properties;
import java.util.TreeSet;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;

/**
 * @author panyu
 */
public class FtpUtilEx {
//    private static Stringss username;
//    private static String password;
//    private static String ip;
//    private static int port;
    private static Properties property=null;//����
    private static String configFile;//�����ļ���·����
    
    private static FTPClient ftpClient=null;
//    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
    
//    private static final String [] FILE_TYPES={"�ļ�","Ŀ¼","��������","δ֪����"};
    
    public static void main(String[] args) {
    	try {
			connectServer("172.16.143.70", 21, "sms", "sms");
			FTPFile[] name = ftpClient.listFiles();
			for(int i =0;i<name.length;i++)
			{
				System.out.println(iso8859togbk(name[i].getName()));
			}
			disconnectServer();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
//        setConfigFile("woxingwosu.properties");//���������ļ�·��
//        connectServer();
//        listAllRemoteFiles();//�г������ļ���Ŀ¼
//        changeWorkingDirectory("webroot");//�����ļ���webroot
//        listRemoteFiles("*.jsp");//�г�webrootĿ¼������jsp�ļ�
//        setFileType(FTP.BINARY_FILE_TYPE);//���ô���������ļ�
//        uploadFile("woxingwosu.xml","myfile.xml");//�ϴ��ļ�woxingwosu.xml����������Ϊmyfile.xml
//        renameFile("viewDetail.jsp", "newName.jsp");//���ļ�viewDetail.jsp����ΪnewName.jsp
//        deleteFile("UpdateData.class");//ɾ���ļ�UpdateData.class
//        loadFile("UpdateData.java","loadFile.java");//�����ļ�UpdateData.java��������������ΪloadFile.java
//        closeConnect();//�ر�����
    }
    
    /**
     * �ϴ��ļ�
     * @param localFilePath--�����ļ�·��
     * @param newFileName--�µ��ļ���
     */
    public static void uploadFile(String localFilePath,String newFileName){
//        connectServer();
        //�ϴ��ļ�
        BufferedInputStream buffIn=null;
        try{
            buffIn=new BufferedInputStream(new FileInputStream(localFilePath));
            ftpClient.storeFile(newFileName, buffIn);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(buffIn!=null)
                    buffIn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * �����ļ�
     * @param remoteFileName --�������ϵ��ļ���
     * @param localFileName--�����ļ���
     */
    public static void loadFile(String remoteFileName,String localFileName){
//        connectServer();
        //�����ļ�
        BufferedOutputStream buffOut=null;
        try{
            buffOut=new BufferedOutputStream(new FileOutputStream(localFileName));
            ftpClient.retrieveFile(remoteFileName, buffOut);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(buffOut!=null)
                    buffOut.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * �г��������������ļ���Ŀ¼
     */
    public static void listAllRemoteFiles(){
        listRemoteFiles("*");
    }
    
    /**
     * �г����������ļ���Ŀ¼
     * @param regStr --ƥ���������ʽ
     */
    public static void listRemoteFiles(String regStr){
//        connectServer();
        try{
            FTPFile[] files=ftpClient.listFiles(regStr);
            if(files==null||files.length==0)
                System.out.println("There has not any file!");
            else{
                TreeSet fileTree=new TreeSet(
                        new Comparator(){
                            //�Ȱ����ļ�����������(����)��Ȼ���ļ���˳������
                            public int compare(Object objFile1,Object objFile2){
                                if(objFile1==null)
                                    return -1;
                                else if(objFile2==null)
                                    return 1;
                                else{
                                    FTPFile file1=(FTPFile)objFile1;
                                    FTPFile file2=(FTPFile)objFile2;
                                    if(file1.getType()!=file2.getType())
                                        return file2.getType()-file1.getType();
                                    else
                                        return file1.getName().compareTo(file2.getName());
                                }
                            }
                        }
                );
                for (int i=0; i<files.length; i++)
                	fileTree.add(files[i]);
//                for(FTPFile files)
//                    fileTree.add(file);
//                System.out.printf("%-35s%-10s%15s%15s\n","����","����","�޸�����","��С");
//                for(FTPFile fileTree){
//                    System.out.printf("%-35s%-10s%15s%15s\n",iso8859togbk(file.getName()),FILE_TYPES[file.getType()]
//                            ,dateFormat.format(file.getTimestamp().getTime()),FileUtils.byteCountToDisplaySize(file.getSize()));
//                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * �ر�����
     */
    public static void closeConnect(){
        try{
            if(ftpClient!=null){
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * ���������ļ�
     * @param configFile
     */
    public static void setConfigFile(String configFile) {
        FtpUtilEx.configFile = configFile;
    }
    
    /**
     * ���ô����ļ�������[�ı��ļ����߶������ļ�]
     * @param fileType--BINARY_FILE_TYPE��ASCII_FILE_TYPE 
     */
    public static void setFileType(int fileType){
        try{
//            connectServer();
            ftpClient.setFileType(fileType);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * ��չʹ��
     * @return
     */
    protected static FTPClient getFtpClient(){
//        connectServer();
        return ftpClient;
    }

    /**
     * ���ò���
     * @param configFile --�����������ļ�
     */
//    private static void setArg(String configFile){
//        property=new Properties();
//        BufferedInputStream inBuff=null;
//        try{
//            inBuff=new BufferedInputStream(new FileInputStream(configFile));
//            property.load(inBuff);
//            username=property.getProperty("username");
//            password=property.getProperty("password");
//            ip=property.getProperty("ip");
//            port=Integer.parseInt(property.getProperty("port"));
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if(inBuff!=null)
//                    inBuff.close();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
    
    public static void connectServer(
    		String host, int port, String username, String password)
    		throws SocketException, IOException {
    	
    	ftpClient=new FTPClient();
    	ftpClient.connect(host, port);
    	ftpClient.login(username, password);
    	
    }
    
    public static void disconnectServer() throws IOException {
    	ftpClient.disconnect();
    }
    
    /**
     * ���ӵ�������
     */
//    public static void connectServer() {
//        if (ftpClient == null) {
//            int reply;
//            try {
//                setArg(configFile);
//                ftpClient=new FTPClient();
//                ftpClient.setDefaultPort(port);
//                ftpClient.configure(getFtpConfig());
//                ftpClient.connect(ip);
//                ftpClient.login(username, password);
//                ftpClient.setDefaultPort(port);
//                System.out.print(ftpClient.getReplyString());
//                reply = ftpClient.getReplyCode();
//
//                if (!FTPReply.isPositiveCompletion(reply)) {
//                    ftpClient.disconnect();
//                    System.err.println("FTP server refused connection.");
//                }
//            } catch (Exception e) {
//                System.err.println("��¼ftp��������"+ip+"��ʧ��");
//                e.printStackTrace();
//            }
//        }
//    }
    
    /**
     * ���뵽��������ĳ��Ŀ¼��
     * @param directory
     */
    public static void changeWorkingDirectory(String directory){
        try{
//            connectServer();
            ftpClient.changeWorkingDirectory(directory);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    /**
     * ���ص���һ��Ŀ¼
     */
    public static void changeToParentDirectory(){
        try{
//            connectServer();
            ftpClient.changeToParentDirectory();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    /**
     * ɾ���ļ�
     */
    public static void deleteFile(String filename){
        try{
//            connectServer();
            ftpClient.deleteFile(filename);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    /**
     * �������ļ� 
     * @param oldFileName --ԭ�ļ���
     * @param newFileName --���ļ���
     */
    public static void renameFile(String oldFileName,String newFileName){
        try{
//            connectServer();
            ftpClient.rename(oldFileName, newFileName);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    /**
     * ����FTP�ͷ��˵�����--һ����Բ�����
     * @return
     */
    private static FTPClientConfig getFtpConfig(){
        FTPClientConfig ftpConfig=new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
        return ftpConfig;
    }
    
    /**
     * ת��[ISO-8859-1 ->  GBK]
     *��ͬ��ƽ̨��Ҫ��ͬ��ת��
     * @param obj
     * @return
     */
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

}
