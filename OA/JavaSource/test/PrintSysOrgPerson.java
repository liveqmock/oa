/*
 * Created on 2004-12-2
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import jxl.*;
import jxl.format.UnderlineStyle;
import jxl.write.*;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PrintSysOrgPerson {
	
	public static void main(String[] args) {
		
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		Statement stmt4 = null;
		Statement stmt5 = null;
		Statement stmt6 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		ResultSet rs5 = null;
		ResultSet rs6 = null;
		
		Connection conn = null;
		int columns = 0;
		
		try {
			conn = getConnection();
			
			String orguuid = "";
			String personuuid = "";
			
			try {
				stmt1 = conn.createStatement();
				
				String sql1 = "SELECT DISTINCT(ORGUUID) FROM SYS_ORGPERSON WHERE ORGUUID IN (SELECT ORGUUID FROM SYS_ORG WHERE ORGLEVEL='3')";
				rs1 = stmt1.executeQuery(sql1);
				System.out.println("1..............");
			    while(rs1.next()){ //�����ж����ű�
					List totallist = new ArrayList();
					String JorgName = "";
			    	orguuid = rs1.getString("ORGUUID");
			    	//�õ��˾ּ������ڴ����µ�personuuid
			    	String sql2 = "SELECT DISTINCT(PERSONUUID) FROM SYS_ORGPERSON WHERE ISBELONG='1' AND (ORGUUID='"+orguuid+"'"
			    	             +" OR (ORGUUID IN (SELECT ORGUUID FROM SYS_ORG WHERE PARENTORGUUID='"+orguuid+"')))";
					stmt2 = conn.createStatement();		    	             
			    	rs2 = stmt2.executeQuery(sql2);
					System.out.println("2..............");
			    	int rows = 0;
			    	while(rs2.next()){  //һ�ű������(����Ϊ����һ��)
						List onepersonlist = new ArrayList();
						personuuid = rs2.getString("PERSONUUID");
						
			    		//�ȵõ��ֵ�����
			    		String sql4 = "SELECT CNNAME FROM SYS_ORG WHERE ORGUUID='"+orguuid+"'";
						stmt4 = conn.createStatement();
			    		rs4 = stmt4.executeQuery(sql4);
						System.out.println("3..............");
						rs4.next();
			    		JorgName = rs4.getString("CNNAME");
						rs4.close();
						stmt4.close();

			    		//�õ��������ڵ���֯,�п���Ϊ����,���Ǵ˾���,���ڴ˾�һ��ĳһ����
			    		//String sql3 = "SELECT ORGUUID FROM SYS_ORGPERSON WHERE PERSONUUID='"+personuuid+"' AND (ORGUUID='"+orguuid+"' OR ORGUUID IN (SELECT ORGUUID FROM SYS_ORG WHERE PARENTORGUUID='"+orguuid+"'))";
						String sql3 = "SELECT ORGUUID FROM SYS_ORGPERSON WHERE PERSONUUID='"+personuuid+"' AND ISBELONG='1'";
						stmt3 = conn.createStatement();
			    		rs3 = stmt3.executeQuery(sql3);
//			    		int i = 0;
			    		System.out.println("rs3.............");
			    		rs3.next();
						String lineorguuid = rs3.getString("ORGUUID");
						rs3.close();
						stmt3.close();
						if(!lineorguuid.equals(orguuid)){
							onepersonlist.add("-");
							String sql5 = "SELECT CNNAME FROM SYS_ORG WHERE ORGUUID='"+lineorguuid+"'";
							stmt5 = conn.createStatement();
							rs5 = stmt5.executeQuery(sql5);
							System.out.println("4..............");
							rs5.next();
							String orgName = rs5.getString("CNNAME");
							onepersonlist.add(orgName);
							System.out.println("the orgname is:"+orgName);
							rs5.close();
							stmt5.close();
//							i++;
						}else{
							onepersonlist.add(JorgName);
							System.out.println("5..............");
							onepersonlist.add("-");
						}
						System.out.println("the Jorgname is:"+JorgName);
			    		//�ɵõ��������
//			    		columns = (i>columns)?i:columns;	
						columns = 3;				
			    		
			    		//д������
						String sql6 = "SELECT CNNAME FROM SYS_PERSON WHERE PERSONUUID='"+personuuid+"'";
						stmt6 = conn.createStatement();
						rs6 = stmt6.executeQuery(sql6);
						String personName = "";
						while(rs6.next()){
							personName = rs6.getString("CNNAME");
						}
						rs6.close();
						stmt6.close();
						if(personName.equals("")) personName="����";
						onepersonlist.add(personName);
						//����һ��
						totallist.add(onepersonlist);
						
						rows++;
						
			    	}//while lines
			    	rs2.close();
			    	stmt2.close();
			    	//��ӡһ�ű�:
					System.out.println("the cols is:"+columns+" and rows is:"+rows);
			        
			        if(totallist.size()!=0){
			        	List linelist = (ArrayList)totallist.get(0);
						String path = "c:/table/";
						File dirpath = new File(path);
						if(!dirpath.exists())   dirpath.mkdirs();
						String filename = path + JorgName + "������Ա��.xls";
						System.out.println("the filename is:"+filename);
						WritableWorkbook workbook;
						workbook = Workbook.createWorkbook(new File(filename));

						//����Excel������
						WritableSheet sheet = workbook.createSheet((String)linelist.get(0)+"��Ա_sheet", 0);
						CreateXSL(workbook,sheet,totallist,columns,JorgName);
					
						workbook.write();
						workbook.close();
					
					
						System.out.println("ִ�����һ����...��");
			        }
			    	
			    }//while sheets
				rs1.close();
				stmt1.close();
				System.out.println("ִ��������б�...��");
			    System.out.println("û�м�¼!");
		    
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("����statementʧ�ܣ�");
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("createSheet failed!");
		} finally {
			try{
				if(rs1!=null) rs1.close();
				if(rs2!=null) rs2.close();
				if(rs3!=null) rs3.close();
				if(rs4!=null) rs4.close();
				if(rs5!=null) rs5.close();
				if(rs6!=null) rs6.close();
			} catch (SQLException e) {
				System.out.println("�ر�resultsetʧ�ܣ�");
			}
		
			try {
				if(stmt1!=null) stmt1.close();
				if(stmt2!=null) stmt2.close();
				if(stmt3!=null) stmt3.close();
				if(stmt4!=null) stmt4.close();
				if(stmt5!=null) stmt5.close();
				if(stmt6!=null) stmt6.close();
			} catch (SQLException e1){
				System.out.println("�ر�statementʧ�ܣ�");
			}
		}
	}
	
	private static Connection getConnection(){
		Connection connection  = null;
		try {
			System.out.println("WWWWWWWWW");
			//����oracle����������
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			// �������Ͷ˿� 
//			String url = "jdbc:oracle:thin:@192.9.100.25:1521:roeee";
			String url = "jdbc:oracle:oci:@orcl_192.9.100.25";
			//��������
			connection = DriverManager.getConnection(url,"oabase","oabase");
			
			System.out.println("connect successfully!");
			
		} catch (SQLException e) {
			System.out.println("connect to the db oabase failed!");
		} catch (InstantiationException e) {
			System.out.println("connect ...........1");
		} catch (IllegalAccessException e) {
			System.out.println("connect ...........2");
		} catch (ClassNotFoundException e) {
			System.out.println("connect ..........3");
		}
		return connection;
	}
	
	public static void CreateXSL(WritableWorkbook workbook,WritableSheet sheet,List totallist,int columns,String orgname){

		try {
			
			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, true, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE); 
			WritableCellFormat headerFormat = new WritableCellFormat (headerFont); 
			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);  
			WritableCellFormat titleFormat = new WritableCellFormat (titleFont); 
			WritableFont dataFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK); 
			WritableCellFormat dataFormat = new WritableCellFormat (dataFont);
			
			ExcelHandler ehandler = new ExcelHandler();
			ehandler.setCellCenterAlign(headerFormat);
			ehandler.setCellCenterAlign(titleFormat);
			ehandler.setCellCenterAlign(dataFormat);
			
			//���ñ߿�
			headerFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			titleFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			dataFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			//��һ�п��
			sheet.setRowView(0,360);
			Label headerLabel = new Label(0,0,orgname+"��Ա���ݱ�",headerFormat);		
			sheet.addCell(headerLabel);	
            
			sheet.setRowView(1,320);
			//�ڶ��У������У�
			Label titlelabel0 = new Label(0,1,"��������",titleFormat);
			sheet.addCell(titlelabel0);
			for(int col=1;col<columns-1;col++){
				Label titlelabel = new Label(col,1,"��������"+col,titleFormat);
				sheet.addCell(titlelabel);
			}
			Label personlabel = new Label(columns-1,1,"����",titleFormat);
			sheet.addCell(personlabel);
            //������
            for(int rows=0;rows<totallist.size();rows++){
            	List onelist = (List)totallist.get(rows);
				for(int cols=0;cols<columns;cols++){
					if(orgname.equals("�칫��")){
						System.out.println(onelist.get(cols).toString());
					}
					Label namecell = new Label(cols,rows+2,onelist.get(cols).toString(),dataFormat);
					sheet.addCell(namecell); 
//					//��д������,
//					int length = onelist.size();
//					Label namecell = new Label(columns-1,rows+2,onelist.get(length-1).toString(),dataFormat);
//					sheet.addCell(namecell); 
//					//��д��֯����
//					for(int i=0;i<columns-1;i++){
//						String data = "";
//						Label datalabel= null;
//						int j = 0;
//						if(i==0&&onelist.get(0).toString().equals(orgname)){
//							datalabel = new Label(0,rows+2,orgname,dataFormat);
//							sheet.addCell(datalabel);
//							j++;
//						}else if(i==0){
//							datalabel = new Label(0,rows+2,"",dataFormat);
//							sheet.addCell(datalabel);
//							datalabel = new Label(1,rows+2,orgname,dataFormat);
//							sheet.addCell(datalabel);
//							j++;
//							i++;
//						}else if(j<length-1){
//							data = onelist.get(j).toString();
//							System.out.println("the data is:"+data);
//							datalabel=new Label(i, rows+2, data, dataFormat);
//							sheet.addCell(datalabel);
//							j++; 
//						}
//					}//for
				}
            }
			
			//�����еĿ��
			for(int w=0;w<columns;w++){
					sheet.setColumnView(w, 30); 			
			}
		} catch (WriteException e) {
			System.out.println("���ɱ�ʱ����!");
		} 
	}

}
