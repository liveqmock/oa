/*
 * Created on 2004-4-15
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.folder.control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import com.icss.oa.commsite.handler.HandlerException;
import com.icss.oa.folder.handler.FolderHandler;
import com.icss.oa.folder.vo.DbfilePackageVO;
import com.icss.oa.folder.vo.FolderDbfileVO;
import com.icss.oa.folder.vo.FolderPackageVO;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CopyChildFolder {
	private Connection conn;
	private FolderHandler handler = null;
	public CopyChildFolder(Connection conn) {
		this.conn = conn;
		this.handler = new FolderHandler(conn);
	}
	public void copyFile(Integer sourceFolderId, Integer targetFolderId) throws HandlerException, com.icss.oa.folder.handler.HandlerException {
		FolderDbfileVO sourceVO = new FolderDbfileVO();
		DbfilePackageVO filePackageVO = null;
		List list = handler.getDbFileList(sourceFolderId);
		if (list != null) {
			Iterator it = list.iterator();
			if (it.hasNext()) {
				filePackageVO = (DbfilePackageVO) it.next();
			}
		}
		
	    sourceVO.setFpId(targetFolderId);
		sourceVO.setFdbfId(null);  
		InputStream in = filePackageVO.getFdbfAccessory();
		sourceVO.setFdbfAccessory(in);
		try {
			handler.addFile(sourceVO);
		    //in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

	public void copyChild(Integer sourceFolderId, Integer targetFolderId) throws HandlerException, com.icss.oa.folder.handler.HandlerException {
		List folderList = handler.getFileList(sourceFolderId);
		if (folderList != null) {
			FolderPackageVO sourceVO = null;
			FolderPackageVO targetVO = null;
			Iterator it = folderList.iterator();
			while (it.hasNext()) {
				sourceVO = (FolderPackageVO) it.next();
				Integer tSourceFolderId = sourceVO.getFpId();
				targetVO = handler.getFolderVO(targetFolderId);
				sourceVO.setFpId(null);
				sourceVO.setFolFpId(targetFolderId);
				sourceVO.setFmId(targetVO.getFmId());
				Integer tTargetFolderId = handler.addFolder(sourceVO);
				if (sourceVO.getFpIsfile().equals("0")) {
					copyFile(tSourceFolderId, tTargetFolderId);
				} else {
					copyChild(tSourceFolderId, tTargetFolderId);
				}
			}

		}

	}
}
