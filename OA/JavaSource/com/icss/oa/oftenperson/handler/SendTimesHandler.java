package com.icss.oa.oftenperson.handler;

import java.sql.Connection;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.oftenperson.dao.FiletransferStatDAO;
import com.icss.oa.oftenperson.vo.FiletransferStatVO;

/**
 * @author wangjiang
 */
public class SendTimesHandler {
	Connection conn;
	public SendTimesHandler(Connection _conn) {
		conn = _conn;
	}
	/**
	 * 记录发件人userid向收件人发邮件的次数 
	 */
	public void recordSendTimes(List sendpersons, String userid)
		throws DAOException {
		DAOFactory factory = new DAOFactory(conn);
		for (int i = 0; i < sendpersons.size(); i++) {
			FiletransferStatDAO dao = new FiletransferStatDAO(conn);
			String receiveruserid = (String) sendpersons.get(i);
			if (receiveruserid.indexOf("@") > 0) {
				receiveruserid =
					receiveruserid.substring(0, receiveruserid.indexOf("@"));
				dao.setSenderuserid(userid);
				dao.setReceiveruserid(receiveruserid);
				factory.setDAO(dao);
				List temp = factory.find(new FiletransferStatVO());
				if (temp.isEmpty()) {
					FiletransferStatDAO dao1 = new FiletransferStatDAO(conn);
					dao1.setSenderuserid(userid);
					dao1.setReceiveruserid(receiveruserid);
					dao1.setTimes(new Integer(1));
					dao1.create();
				} else {
					FiletransferStatDAO dao2 = new FiletransferStatDAO(conn);
					FiletransferStatVO vo = (FiletransferStatVO) temp.get(0);
					int times = vo.getTimes().intValue();
					dao2.setTimes(new Integer(times + 1)); //将发送次数加1
					dao2.setStatid(vo.getStatid());
					dao2.update(true);
				}
			}
		}
	}
}
