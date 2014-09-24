package com.icss.oa.zbs.xtsduty.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.zbs.xtsduty.handler.XtsWorkInfoHandler;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfoVO;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfomainVO;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

public class XtsDutyEditAjaxServlet extends ServletBase {

	@Override
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub
		Connection conn = null;

		Date now = new Date();
		Timestamp dateTime = new Timestamp(now.getTime());

		try {
			Context ctx = Context.getInstance();
			String uuid = ctx.getCurrentPersonUuid();
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);

			String mainId = request.getParameter("mainId");

			XtsWorkInfoHandler handler = new XtsWorkInfoHandler(conn);

			List list = handler.getLastInfo(new Integer(mainId));

			for (int i = 0; i < list.size(); i++) {
				TbXtsWorkinfoVO tvo = (TbXtsWorkinfoVO) list.get(i);
				if(request.getParameter("type_fck_"+tvo.getWitId().toString())!=null){
				String content = URLDecoder.decode(request.getParameter("type_fck_"+tvo.getWitId().toString()),"utf-8");
				//System.out.println(content);
				content = content.replaceAll("σρ","&");
				tvo.setWitContent(content);

				tvo.setWitModifyer(uuid);
				tvo.setWitModifytime(dateTime);

				// infovo.setWiId(new Integer(infoids[i]));
				Integer infoid = handler.updateDutyContentInfo(tvo);
				}

			}
			
			PrintWriter out = response.getWriter();

			//是否有其他人编辑
			UserMsgHandler userMsghandler = new UserMsgHandler(conn); 
			TbXtsWorkinfomainVO vo = new TbXtsWorkinfomainVO();
			vo = handler.getMainDutyById(mainId);
			if(userMsghandler.getUserName().equals(vo.getLastEditer()) && request.getRemoteAddr().equals(vo.getLastIP())){
				SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
				out.println("在"+formater.format(new Date())+"自动保存成功!");
			}else{
				request.setAttribute("lastEditer", vo.getLastEditer());
				request.setAttribute("lastIP", vo.getLastIP());
				request.setAttribute("noediter", "yes");
				out.println("冲突$"+vo.getLastEditer()+"$"+vo.getLastIP());
			}
			out.close();
		} catch (DBConnectionLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private String format(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}