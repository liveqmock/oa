package com.icss.orgtree.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.orgtree.vo.SysOrgVO;
import com.icss.orgtree.vo.SysOrgTreeVO;

public class PhoneOrgHandler {
	
	private Connection conn;
	
	public PhoneOrgHandler(Connection conn) {
		this.conn = conn;
	}

	public List getOrgTreeList(String orgId) throws HandlerException {
		List list = new ArrayList();
		SysOrgTreeVO treeVO = null;;
		SysOrgVO vo = null;

		try{
		 	PreparedStatement ps = conn.prepareStatement("select org1.orgcode,org1.ORGNAME,org1.PARENTCODE,org1.SEQ,count(org2.ORGCODE) as childcount from gmis2002.hrorg org1 left join gmis2002.hrorg org2 on org1.ORGCODE=org2.PARENTCODE where org1.orgcode<>'03' and org1.parentcode=? group by org1.orgcode,org1.ORGNAME,org1.PARENTCODE,org1.SEQ order by org1.SEQ");
			ps.setString(1, orgId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				vo = new SysOrgVO();
				vo.setOrguuid(rs.getString("orgcode"));
				vo.setCnname(rs.getString("orgname"));
				vo.setParentorguuid(rs.getString("parentcode"));
				vo.setSerialindex(rs.getInt("seq"));
				
				treeVO = new SysOrgTreeVO();
				treeVO.setVO(vo);
				treeVO.setHasChild(rs.getInt("childcount") > 0);
				list.add(treeVO);
			}
			rs.close();
			ps.close();
		}catch (SQLException e) {
			throw new HandlerException(e);
		}
		
		return list;
	}

	public SysOrgVO getOrg(String orgId) throws HandlerException {
		SysOrgVO vo = null;
		
		try{
		 	PreparedStatement ps = conn.prepareStatement("select * from gmis2002.hrorg where orgcode=?");
			ps.setString(1, orgId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				vo = new SysOrgVO();
				vo.setOrguuid(rs.getString("orgcode"));
				vo.setCnname(rs.getString("orgname"));
				vo.setParentorguuid(rs.getString("parentcode"));
				vo.setSerialindex(rs.getInt("seq"));
			}
			rs.close();
			ps.close();
		}catch (SQLException e) {
			throw new HandlerException(e);
		}
		
		return vo;
	}
}
