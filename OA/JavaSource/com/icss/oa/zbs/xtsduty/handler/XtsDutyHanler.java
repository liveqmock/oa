package com.icss.oa.zbs.xtsduty.handler;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.zbs.xtsduty.dao.TbXtsWorkinfotypeDAO;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfotypeVO;

public class XtsDutyHanler {

	private Connection conn;

	public XtsDutyHanler(Connection conn) {
		this.conn = conn;
	}

	public HashMap getDutyName() throws DAOException {

		TbXtsWorkinfotypeDAO dao = new TbXtsWorkinfotypeDAO();
		DAOFactory f = new DAOFactory(conn);
		f.setDAO(dao);

		List list = f.find(new TbXtsWorkinfotypeVO());

		HashMap map = new HashMap();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			TbXtsWorkinfotypeVO vo = (TbXtsWorkinfotypeVO) it.next();
			map.put(vo.getWitId(), vo.getWitName());
		}

		return map;

	}

}