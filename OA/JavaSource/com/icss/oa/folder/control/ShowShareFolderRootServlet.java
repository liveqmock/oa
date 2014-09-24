/*
 * Created on 2005-1-8
 *
 * 
 */
package com.icss.oa.folder.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.ShareShareaccessVO;

/**
 * @author sunchuanting
 *
 * 
 */
public class ShowShareFolderRootServlet extends ServletBase{
	protected void performTask( HttpServletRequest request,
			                    HttpServletResponse response) 
	          throws ServletException, IOException {
		
		Connection conn = null;
		List shareList = null;
		List shareNameList = new ArrayList();
		
		try {
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("ShowShareFolderRootServlet");
			FolderHandler handler = new FolderHandler(conn);
			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			shareList = handler.getShareList(userId);
			
			String DouSharename []=new String[200];
			
			Iterator it = shareList.iterator();
			int i=0;
			
			while (it.hasNext()) {
				ShareShareaccessVO vo = (ShareShareaccessVO) it.next();
				String isName = vo.getFsName();
				
				int nPosition=0;
				nPosition=isName.lastIndexOf(":");
				DouSharename[i++]=isName;
				
				boolean Douflag=false;
				String DouSharename1 [][]=new String[1][2];
				
				DouSharename1[0][0]=isName.substring(0,nPosition);
                DouSharename1[0][1]=new String(vo.getFpId().toString());
				
				for(int j=0;j<i-1;j++){
					
					 String str1 = isName.substring(0,nPosition);
		             String str2 = DouSharename[j].substring(0,nPosition);
		             
					if(str1.equals(str2))
					    { Douflag=true;
					      break; 
					    }
					}
				
                if(Douflag) { continue; }
                
                shareNameList.add(shareNameList.size(),DouSharename1);
                   
			}
			
			request.setAttribute("list",shareNameList);
			
		    this.forward(request, response, "/folder/fileRootList.jsp");
		    
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("ShowShareFolderRootServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}

