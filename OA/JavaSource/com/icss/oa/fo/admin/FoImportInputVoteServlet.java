/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.fo.admin;        

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoInputvalueVO;
import com.icss.oa.fo.vo.FoInputvoteVO;
import com.icss.oa.fo.vo.FoInputvotevalueVO;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.log.handler.SendFileBean;
import com.icss.oa.log.handler.AttachFileBean;
import com.icss.oa.util.FileTypeUtil;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 * 
 * �ϴ�����
 */
public class FoImportInputVoteServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer planid=request.getParameter("planid")==null?new Integer(-1):new Integer(request.getParameter("planid"));
        
		Connection conn = null;
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String url = ""; 
			FoHandler handler=new FoHandler(conn);
			
			try{
				url = getUploadFileFullName(request, "attfile");
				System.out.println("FoImportInputVoteServlet attfile=  "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/fo/alert.jsp?errormsg=��ȡ�ļ�·������");
			}
			// �жϸ����ܺ��Ƿ����10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "attfile");
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
			System.out.println("++++++++++FoImportInputVoteServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// �õ����ص��ļ�����
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						
					}
					System.out.println("++++++++++FoImportInputVoteServlet2+++filename===+++++++"+filename);
					// ----------�����ص��ļ�ת�Ƶ�ָ�����ʼ�����Ŀ¼��----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"fo"+sep+"upload"+sep));
					Context ctx = Context.getInstance();
					if(ctx==null){
						response.sendRedirect(
								request.getContextPath()
									+ "/fo/alert.jsp?errormsg=�û���Ϣ����,�����µ�¼ϵͳ��");
					}
					UserInfo user = ctx.getCurrentLoginInfo();
					String	username=user.getEnName();
					path.append("input").append(username).append(System.currentTimeMillis()).append(File.separator);
					System.out.println("++++++++++FoImportInputVoteServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();
					}
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));
					System.out.println("++++++++++FoImportInputVoteServlet++++++++++filepath=======++"+filepath);
					
					
					
					
					if(ins!= null){
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
//					 ----------------------��ȡ�ļ��ļ�---------------------------
					System.out.println("��ȡ�ļ��ļ� begin");
					
					List implist=new ArrayList();
					//List implist2=new ArrayList();
//					ȡ��excel���е�������ֵ
					implist=handler.inputVoteReadExcel(filepath,planid);
					System.out.println("��ȡ�ļ���� ��С="+implist.size());
					Iterator impitr=implist.iterator();
					
					List artlist=handler.getartIdList(planid);
					System.out.println("ȡ������ID���б�"+artlist.size());
					boolean right=true;
					while(impitr.hasNext()){//����ÿ��ֵ���Ƿ����ļ��б���
						FoInputvotevalueVO invaluevo=(FoInputvotevalueVO) impitr.next();
						//implist2.add(invaluevo);
						
						if(new Integer(-1).equals(invaluevo.getArtId())||!artlist.contains(invaluevo.getArtId().toString())){
							System.out.println("����ÿ��ֵ���Ƿ����ļ��б���"+invaluevo.getArtId());
							right=false;
						
							
						}
						
					}
					System.out.println("+++++++++++++right="+right+"+++++++++implist"+implist.size());
					if(right){//�ļ�����
						
						Iterator impitr2=implist.iterator();
						//�ȴ���inputvote��¼
						
						boolean firstflag=true;
						
						Integer invoteid=null;
						
			            Date date=new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String formatTime = formatter.format(date);
//						ȡ��������ϢMAP
						Map artmap=new HashMap();
						artmap=handler.getSimpleArticleMap(planid.toString());
						while(impitr2.hasNext()){//����ÿ��ֵ���Ƿ����ļ��б���
							//System.out.println("��ʼ��������");
							FoInputvotevalueVO invaluevo=(FoInputvotevalueVO) impitr2.next();
							if(firstflag){
								FoInputvoteVO invotevo=new FoInputvoteVO();
								invotevo.setInvotePerson(invaluevo.getInvotePerson());
								invotevo.setInvotePersonnum(invaluevo.getInvotePersonnum());
								invotevo.setInvoteRecorder(user.getCnName());
								invotevo.setPlanPlanid(planid);
								invotevo.setInvoteDate(formatTime);
								invoteid=handler.addInputvote(invotevo);
								System.out.println("��������ͶƱ��¼ invaluevo.getInvotePerson()="+invaluevo.getInvotePerson()+"invaluevo.getInvotePersonnum()="+invaluevo.getInvotePersonnum());
								
//								���ļƻ��е�ͶƱ����
								FoPlanVO planvo=handler.getPlanVo(planid.toString());
								int invotepersonnum=invaluevo.getInvotePersonnum().intValue();
								int oldvotenum=planvo.getPlanPersonnum().intValue();
								int newvotenum=oldvotenum+new Integer(invotepersonnum).intValue();
								planvo.setPlanPersonnum(new Integer(newvotenum));
								handler.updatePlan(planvo);
								System.out.println("���ļƻ��е�ͶƱ���� newvotenum="+newvotenum);
								firstflag=false;
								
								
								
								
							}
//							����inputvalue������Ϣ
							FoInputvalueVO newinvaluevo=new FoInputvalueVO();
							newinvaluevo.setInvoteId(invoteid);
							newinvaluevo.setArtId(invaluevo.getArtId());
							newinvaluevo.setInvalueValue(invaluevo.getInvalueValue());
							handler.addInputVoteValue(newinvaluevo);
							System.out.println("����ͶƱ���� invaluevo.getArtId()"+invaluevo.getArtId()+"invaluevo.getInvalueValue()"+invaluevo.getInvalueValue()+"invoteid="+invoteid);
							//���������е�ͶƱֵ��Ϣ
							FoArticalVO artvo=(FoArticalVO) artmap.get(invaluevo.getArtId());
							int newvotenum=invaluevo.getInvalueValue().intValue();
							handler.updateArticalVotenum(invaluevo.getArtId().toString(),new Integer(artvo.getArtVotenum().intValue()+newvotenum));
							System.out.println("���������е�ͶƱֵ��Ϣ newvotenum"+newvotenum);
							
						}
						
						response.sendRedirect(request.getContextPath()
									+ "/servlet/FoInputVoteManagerServlet?planid="+planid);
						
					}else{//�ļ�����
						response.sendRedirect(
								request.getContextPath()
									+ "/fo/alert.jsp?errormsg=�ļ�ģ������������������ȷ��ģ��!");
						
					}
					
					System.out.println("��ȡ�ļ��ļ� end");
					
					
					
					
				}else{   // file isn't exist
					
					response.sendRedirect(
							request.getContextPath()
								+ "/fo/alert.jsp?errormsg=�ļ���Ϊ�գ�����������!");
					
				
				}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally{
			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		
	}
	
	
	
}


