/*
 * Created on 2004-8-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.netoffice.onduty.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.netoffice.onduty.handler.DutyHandler;
import com.icss.oa.netoffice.onduty.handler.PrintDutyBean;
import com.icss.oa.netoffice.onduty.vo.OfficeDutyVO;
import com.icss.oa.util.CommUtil;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PrintDutyServlet extends ServletBase{

	protected void performTask(HttpServletRequest request, HttpServletResponse response)
	       throws ServletException, IOException {
	       	
			Connection conn = null;
			List periodList = new ArrayList();
			List periodStrList = new ArrayList();
			List periodDutyList = new ArrayList();
		
//			String orgid = request.getParameter("orgUUid");
//			String personUUid = "";
//			String orgUUid = "";
			try {
			    //��session��ȡ
				HttpSession session = request.getSession();
				PrintDutyBean printBean = PrintDutyBean.getInstanceFromSession(session);
				
				String orgid = printBean.getOrgUUid();

				Context ctx = null;
				ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
			
				String personUUid = user.getPersonUuid();
				String orgUUid = user.getPrimaryOrguuid();  //��¼��������֯
			
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("PrintDutyServlet");
				DutyHandler dHandler = new DutyHandler(conn);
			
				boolean isPermission = true;
				if(orgid!=null&&!(orgUUid.equals(orgid))){  //��¼�û���������ѡ����֯
					orgUUid = orgid;   //��ʾ��ѡ�����֯
				}
				
				long weekStart = printBean.getWeekStart();
				int year = printBean.getYear();
				int month = printBean.getMonth();
				int week = printBean.getWeek();
			
//				//�õ�����֯�µı�����ֵ�����Ա����Ϣ
//				long nowtime = System.currentTimeMillis();
//			
//				//�õ���ǰʱ���������ڵĿ�ʼʱ��
//				long weekStart = dHandler.getWeekStart(nowtime);
			
				//��ǰ�µ����һ���Ǳ��µĵڼ�������
//				Calendar cal = Calendar.getInstance();
//				cal.setTime(new Date(nowtime));
//				int year = cal.get(Calendar.YEAR);
//				int month = cal.get(Calendar.MONTH)+2;  //����
//				int week = cal.get(Calendar.WEEK_OF_MONTH);
				long nextMonthStart = 0;
				if(month<10){
					nextMonthStart = CommUtil.getLongByTime(year+"-0"+(month)+"-01");
				}else{
					nextMonthStart = CommUtil.getLongByTime(year+"-"+(month)+"-01");
				}
//				cal.setTime(new Date(nextMonthStart-86400000));
//				long weekth = new Integer(cal.get(Calendar.WEEK_OF_MONTH)).longValue();

				//�õ�ֵ�����
				String dutyName = dHandler.getDutyName(orgUUid,weekStart);
				if("".equals(dutyName)){
					dutyName = dHandler.GetOrgName(orgUUid)+"ֵ���";
				}
				//�õ���һ�ܵ�ֵ���ʱ���
				periodList = printBean.getPeriodList();
				periodStrList = printBean.getperiodStrList();
				periodDutyList = printBean.getperiodDutyList();
//				periodList = dHandler.getPeriodList(orgUUid,weekStart);
//			
//				for(int i=0;i<periodList.size();i++){  //ÿ��ѭ��һ��ʱ���
//					OfficeDutyVO odpVO = (OfficeDutyVO)periodList.get(i);
//					//�õ�ʱ��δ�
//					StringBuffer periodBuf = new StringBuffer();
//					//����һ��ʱ��ֻ��Ϊ�õ�һ��������LONGʱ��
//					String pStart = CommUtil.getTime(odpVO.getStarttime().longValue()).trim();
//					periodBuf.append(pStart.substring(pStart.indexOf(" ")));
//					periodBuf.append("~");
//					String pEnd = CommUtil.getTime(odpVO.getEndtime().longValue()).trim();
//					periodBuf.append(pEnd.substring(pEnd.indexOf(" ")));
//
//					periodStrList.add(periodBuf.toString());
//					
//					long firstStart = weekStart;
//					for(int j=0;j<7;j++){  //����
//						List onePeriodList = new ArrayList();
//						onePeriodList = dHandler.getOnedayList(
//							firstStart,
//							odpVO.getStarttime().longValue(),
//							odpVO.getEndtime().longValue(),
//							orgUUid
//							);  //һ������ж����ֵ��
//						periodDutyList.add(onePeriodList);
//						firstStart = firstStart + 86400000;
//					}
//				}
				
				String path=this.getServletContext().getRealPath("/dutyprint");
				File file=new File(path);
                if(!file.exists()){
	                 file.mkdirs();
                }
					  
				File tempFile=new File(path+"/"+dutyName+"("+year+"-"+month+" ��"+week+"��).xls");
				if(!tempFile.exists()){
					tempFile.createNewFile();
				}
				
				WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
				WritableSheet sheet = workbook.createSheet("dutysheet", 0);
				Label l=null; 
				
				WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE); 
				WritableCellFormat headerFormat = new WritableCellFormat (headerFont); 
					
				WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);  
				WritableCellFormat titleFormat = new WritableCellFormat (titleFont);
				titleFormat.setWrap(true);
				titleFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
				titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
				titleFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
					
				WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK); 
				WritableCellFormat detFormat = new WritableCellFormat (detFont); 
				detFormat.setWrap(true);
				detFormat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
				detFormat.setAlignment(jxl.format.Alignment.LEFT);
				detFormat.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);
				
				l=new Label(3, 0, dHandler.GetOrgName(orgUUid)+"ֵ���("+dutyName+")", headerFormat);
				sheet.addCell(l);
					
				l=new Label(0, 1, "ʱ���\\����", titleFormat);
				sheet.addCell(l);

				String MondayStr = com.icss.oa.util.CommUtil.getTime(weekStart,1).trim().substring(5,10);
				String TuesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*1,1).trim().substring(5,10);
				String WednesdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*2,1).trim().substring(5,10);
				String ThursdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*3,1).trim().substring(5,10);
				String FridayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*4,1).trim().substring(5,10);
				String SaturdayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*5,1).trim().substring(5,10);
				String SundayStr = com.icss.oa.util.CommUtil.getTime(weekStart+86400000*6,1).trim().substring(5,10);
								
				l=new Label(1, 1, "����һ"+"("+MondayStr+")", titleFormat);
				sheet.addCell(l);
				l=new Label(2, 1, "���ڶ�"+"("+TuesdayStr+")", titleFormat);
				sheet.addCell(l);
				l=new Label(3, 1, "������"+"("+WednesdayStr+")", titleFormat);
				sheet.addCell(l);
				l=new Label(4, 1, "������"+"("+ThursdayStr+")", titleFormat);
				sheet.addCell(l);
				l=new Label(5, 1, "������"+"("+FridayStr+")", titleFormat);
				sheet.addCell(l);
				l=new Label(6, 1, "������"+"("+SaturdayStr+")", titleFormat);
				sheet.addCell(l);
				l=new Label(7, 1, "������"+"("+SundayStr+")", titleFormat);
				sheet.addCell(l);
				
				if(periodList.size()>0){
					int p=0;
					for(int m=0;m<periodList.size();m++){
						l=new Label(0,m+2,periodStrList.get(m).toString(), titleFormat);
						sheet.addCell(l);
						for(int n=0;n<7;n++){
							List onedayList = (List)periodDutyList.get(p);
							p++;  //periodDutyList�����
							StringBuffer oneCrossStr = new StringBuffer();
							if(onedayList.size()>0){
								for(int k=0;k<onedayList.size();k++){   //һ�����ֵ��ΰ�
									if(k!=0){
										oneCrossStr.append(",");
									}
									OfficeDutyVO dVO = (OfficeDutyVO)onedayList.get(k);
									String personUUid2 = dVO.getPersonuuid();
									oneCrossStr.append(dHandler.getUserName(personUUid2));
								}
								l=new Label(n+1,m+2,oneCrossStr.toString(), detFormat);
								sheet.addCell(l);
							}else{
								l=new Label(n+1,m+2,"", detFormat);
								sheet.addCell(l);
							}
						}
					}
				}
				
				sheet.setColumnView(0,18);
				sheet.setColumnView(1,14);
				sheet.setColumnView(2,14);
				sheet.setColumnView(3,14);
				sheet.setColumnView(4,14);
				sheet.setColumnView(5,14);
				sheet.setColumnView(6,14);
				sheet.setColumnView(7,14);
				sheet.setColumnView(8,14);
					
				sheet.setRowView(0,400);
				sheet.setRowView(1,400);
				sheet.setRowView(2,600);
				sheet.setRowView(3,600);
				sheet.setRowView(4,600);
				sheet.setRowView(5,600);
				sheet.setRowView(6,600);
				sheet.setRowView(7,600);
				sheet.setRowView(8,600);
				
				workbook.write();
				workbook.close();
					
				String tempath = tempFile.getPath();
				InputStream in = new FileInputStream(tempFile.getPath());
				DownloadResponse ds = new DownloadResponse(response);
				ds.downloadInputStream(in, tempFile.getName());
				tempFile.delete();
			
				
				if(in!=null){
					in.close();
				}
			
			} catch (ServiceLocatorException e1) {
				e1.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (conn != null)
					try {
						conn.close();
						ConnLog.close("PrintDutyServlet");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		
	}

}
