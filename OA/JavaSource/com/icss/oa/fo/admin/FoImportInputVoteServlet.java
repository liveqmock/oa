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
 * 上传附件
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
							+ "/fo/alert.jsp?errormsg=读取文件路径错误");
			}
			// 判断附件总和是否大于10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "attfile");
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
			System.out.println("++++++++++FoImportInputVoteServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// 得到上载的文件名称
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						
					}
					System.out.println("++++++++++FoImportInputVoteServlet2+++filename===+++++++"+filename);
					// ----------把上载的文件转移到指定的邮件下载目录下----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"fo"+sep+"upload"+sep));
					Context ctx = Context.getInstance();
					if(ctx==null){
						response.sendRedirect(
								request.getContextPath()
									+ "/fo/alert.jsp?errormsg=用户信息过期,请重新登录系统！");
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
//					 ----------------------读取文件文件---------------------------
					System.out.println("读取文件文件 begin");
					
					List implist=new ArrayList();
					//List implist2=new ArrayList();
//					取得excel表中的行数据值
					implist=handler.inputVoteReadExcel(filepath,planid);
					System.out.println("读取文件完毕 大小="+implist.size());
					Iterator impitr=implist.iterator();
					
					List artlist=handler.getartIdList(planid);
					System.out.println("取得文章ID的列表"+artlist.size());
					boolean right=true;
					while(impitr.hasNext()){//遍历每个值看是否在文件列表中
						FoInputvotevalueVO invaluevo=(FoInputvotevalueVO) impitr.next();
						//implist2.add(invaluevo);
						
						if(new Integer(-1).equals(invaluevo.getArtId())||!artlist.contains(invaluevo.getArtId().toString())){
							System.out.println("遍历每个值看是否在文件列表中"+invaluevo.getArtId());
							right=false;
						
							
						}
						
					}
					System.out.println("+++++++++++++right="+right+"+++++++++implist"+implist.size());
					if(right){//文件无误
						
						Iterator impitr2=implist.iterator();
						//先创建inputvote记录
						
						boolean firstflag=true;
						
						Integer invoteid=null;
						
			            Date date=new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String formatTime = formatter.format(date);
//						取得文章信息MAP
						Map artmap=new HashMap();
						artmap=handler.getSimpleArticleMap(planid.toString());
						while(impitr2.hasNext()){//遍历每个值看是否在文件列表中
							//System.out.println("开始增加数据");
							FoInputvotevalueVO invaluevo=(FoInputvotevalueVO) impitr2.next();
							if(firstflag){
								FoInputvoteVO invotevo=new FoInputvoteVO();
								invotevo.setInvotePerson(invaluevo.getInvotePerson());
								invotevo.setInvotePersonnum(invaluevo.getInvotePersonnum());
								invotevo.setInvoteRecorder(user.getCnName());
								invotevo.setPlanPlanid(planid);
								invotevo.setInvoteDate(formatTime);
								invoteid=handler.addInputvote(invotevo);
								System.out.println("增加输入投票记录 invaluevo.getInvotePerson()="+invaluevo.getInvotePerson()+"invaluevo.getInvotePersonnum()="+invaluevo.getInvotePersonnum());
								
//								更改计划中的投票人数
								FoPlanVO planvo=handler.getPlanVo(planid.toString());
								int invotepersonnum=invaluevo.getInvotePersonnum().intValue();
								int oldvotenum=planvo.getPlanPersonnum().intValue();
								int newvotenum=oldvotenum+new Integer(invotepersonnum).intValue();
								planvo.setPlanPersonnum(new Integer(newvotenum));
								handler.updatePlan(planvo);
								System.out.println("更改计划中的投票人数 newvotenum="+newvotenum);
								firstflag=false;
								
								
								
								
							}
//							创建inputvalue数据信息
							FoInputvalueVO newinvaluevo=new FoInputvalueVO();
							newinvaluevo.setInvoteId(invoteid);
							newinvaluevo.setArtId(invaluevo.getArtId());
							newinvaluevo.setInvalueValue(invaluevo.getInvalueValue());
							handler.addInputVoteValue(newinvaluevo);
							System.out.println("增加投票数据 invaluevo.getArtId()"+invaluevo.getArtId()+"invaluevo.getInvalueValue()"+invaluevo.getInvalueValue()+"invoteid="+invoteid);
							//更改文章中的投票值信息
							FoArticalVO artvo=(FoArticalVO) artmap.get(invaluevo.getArtId());
							int newvotenum=invaluevo.getInvalueValue().intValue();
							handler.updateArticalVotenum(invaluevo.getArtId().toString(),new Integer(artvo.getArtVotenum().intValue()+newvotenum));
							System.out.println("更改文章中的投票值信息 newvotenum"+newvotenum);
							
						}
						
						response.sendRedirect(request.getContextPath()
									+ "/servlet/FoInputVoteManagerServlet?planid="+planid);
						
					}else{//文件有误
						response.sendRedirect(
								request.getContextPath()
									+ "/fo/alert.jsp?errormsg=文件模板有误，请重新上载正确的模板!");
						
					}
					
					System.out.println("读取文件文件 end");
					
					
					
					
				}else{   // file isn't exist
					
					response.sendRedirect(
							request.getContextPath()
								+ "/fo/alert.jsp?errormsg=文件名为空，请重新上载!");
					
				
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


