// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   CopyFolderServlet.java

package com.icss.oa.folder.control;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.FolderManagementVO;
import com.icss.oa.folder.vo.FolderPackageVO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Referenced classes of package com.icss.oa.folder.control:
//            CopyChildFolder

public class CopyFolderServlet extends ServletBase
{

    public CopyFolderServlet()
    {
    }

    protected void performTask(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        Connection conn = null;
        Integer targetFolderId = new Integer(request.getParameter("targetFolderId"));
        String sfolderId = request.getParameter("folderId");
        try
        {
            conn = DBConnectionLocator.getInstance().getConnection("java:comp/env/ResourceOne/DataSource");
            ConnLog.open("CopyFolderServlet");
            conn.setAutoCommit(false);
            FolderHandler handler = new FolderHandler(conn);
            UserMsgHandler userHandler = new UserMsgHandler(conn);
            String userId = userHandler.getUserId();
            List fmList = handler.getUserList(userId);
            Iterator fit = fmList.iterator();
            FolderManagementVO fmvo = null;
            if(fit.hasNext())
                fmvo = (FolderManagementVO)fit.next();
            List folderId = handler.sringToList(sfolderId);
            for(int i = 0; i < folderId.size(); i++)
            {
                int errorFlag = 0;
                Integer sourceFolderId = new Integer(folderId.get(i).toString());
                FolderPackageVO sourceFolderVO = handler.getFolderVO(sourceFolderId);
                boolean hasSameName = handler.checkSameName(sourceFolderVO.getFpName(), targetFolderId, fmvo.getFmId());
                if(hasSameName)
                {
                    errorFlag = 1;
                    forward(request, response, "/folder/error.jsp?errormsg=contain same name file");
                } else
                if(targetFolderId.equals(sourceFolderId))
                {
                    errorFlag = 1;
                    forward(request, response, "/folder/error.jsp?errormsg=contain same folder");
                } else
                {
                    List folderList = handler.getFolderListById(sourceFolderId);
                    for(Iterator it = folderList.iterator(); it.hasNext();)
                    {
                        FolderPackageVO vo = (FolderPackageVO)it.next();
                        if(vo.getFpId().equals(targetFolderId))
                        {
                            errorFlag = 1;
                            break;
                        }
                    }

                    if(errorFlag == 1)
                        forward(request, response, "/folder/error.jsp?errormsg=\u79FB\u52A8\u7684\u6587\u4EF6\u5939\u662F\u539F\u6587\u4EF6\u5939\u5B50\u6587\u4EF6\u5939");
                }
                if(errorFlag == 0)
                {
                    if(targetFolderId.intValue() != 1)
                    {
                        FolderPackageVO targetVO = handler.getFolderVO(targetFolderId);
                        sourceFolderVO.setFolFpId(targetFolderId);
                        sourceFolderVO.setFpId(null);
                        sourceFolderVO.setFmId(targetVO.getFmId());
                    } else
                    {
                        sourceFolderVO.setFolFpId(new Integer(1));
                        sourceFolderVO.setFpId(null);
                        sourceFolderVO.setFmId(fmvo.getFmId());
                    }
                    Integer copyedFolderId = handler.addFolder(sourceFolderVO);
                    if(sourceFolderVO.getFpIsfile().equals("1"))
                    {
                        CopyChildFolder copyChildFolder = new CopyChildFolder(conn);
                        copyChildFolder.copyChild(sourceFolderId, copyedFolderId);
                    } else
                    {
                        CopyChildFolder copyChildFolder = new CopyChildFolder(conn);
                        copyChildFolder.copyFile(sourceFolderId, copyedFolderId);
                    }
                }
            }

            conn.commit();
            forward(request, response, "/mail/reload.jsp");
        }
        catch(Exception e)
        {
            try
            {
                conn.rollback();
            }
            catch(SQLException e1)
            {
                e1.printStackTrace();
            }
            handleError(e);
        }
        finally
        {
            try
            {
                if(conn != null)
                {
                    conn.close();
                    ConnLog.close("CopyFolderServlet");
                }
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        return;
    }
}