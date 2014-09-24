/*
 * 创建日期 2008-2-26
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.duty.dao;

/**
 * @author 王苏
 * 
 * 更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
import java.sql.Connection;
import java.sql.Timestamp;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

public class TbZbsWorkinfoDAO extends DAO {

	private Integer wiId;
	private Integer wimId;
	private Integer witId;
	private String witContent;
	private String witCreater;
	private Timestamp witCreatetime;
	private String witModifyer;
	private Timestamp witModifytime;

	public TbZbsWorkinfoDAO(Connection conn) {
		super(conn);
	}

	public TbZbsWorkinfoDAO() {
		super();
	}

	public void setWiId(java.lang.Integer _wiId) {
		firePropertyChange("wiId", wiId, _wiId);
		wiId = _wiId;
	}

	public java.lang.Integer getWiId() {
		return wiId;
	}

	public void setWimId(java.lang.Integer _wimId) {
		firePropertyChange("wimId", wimId, _wimId);
		wimId = _wimId;
	}

	public java.lang.Integer getWimId() {
		return wimId;
	}

	public void setWitId(java.lang.Integer _witId) {
		firePropertyChange("witId", witId, _witId);
		witId = _witId;
	}

	public java.lang.Integer getWitId() {
		return witId;
	}

	public void setWitContent(String _witContent) {
		firePropertyChange("witContent", witContent, _witContent);
		witContent = _witContent;
	}

	public String getWitContent() {
		return witContent;
	}

	public void setWitCreater(java.lang.String _witCreater) {
		firePropertyChange("witCreater", witCreater, _witCreater);
		witCreater = _witCreater;
	}

	public java.lang.String getWitCreater() {
		return witCreater;
	}

	public void setWitCreatetime(java.sql.Timestamp _witCreatetime) {
		firePropertyChange("witCreatetime", witCreatetime, _witCreatetime);
		witCreatetime = _witCreatetime;
	}

	public java.sql.Timestamp getWitCreatetime() {
		return witCreatetime;
	}

	public void setWitModifyer(java.lang.String _witModifyer) {
		firePropertyChange("witModifyer", witModifyer, _witModifyer);
		witModifyer = _witModifyer;
	}

	public java.lang.String getWitModifyer() {
		return witModifyer;
	}

	public void setWitModifytime(java.sql.Timestamp _witModifytime) {
		firePropertyChange("witModifytime", witModifytime, _witModifytime);
		witModifytime = _witModifytime;
	}

	public java.sql.Timestamp getWitModifytime() {
		return witModifytime;
	}

	protected void setupFields() throws DAOException {
		addField("wiId", "WI_ID");
		addField("wimId", "WIM_ID");
		addField("witId", "WIT_ID");
		addField("witContent", "WIT_CONTENT");
		addField("witCreater", "WIT_CREATER");
		addField("witCreatetime", "WIT_CREATETIME");
		addField("witModifyer", "WIT_MODIFYER");
		addField("witModifytime", "WIT_MODIFYTIME");
		setTableName("TB_ZBS_WORKINFO");
		addKey("WI_ID");
		setAutoIncremented("WI_ID");
	}

//	public void update(boolean byPrimaryKey)
//			throws com.icss.j2ee.dao.DAOException {
//
//		ResultSet rs = null;
//		if (this.getWitContent() == null) {
//			super.update(byPrimaryKey);
//		} else {
//
//			Statement stmt = null;
//			Writer outStream = null;
//			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@id="+wiId);
//			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@content="+ witContent);
//			try {
//
//				stmt = _connection.createStatement(); // Prepare a statement
//
//				// 插入一个空对象empty_clob()
//				stmt.executeUpdate(" UPDATE TB_ZBS_WORKINFO SET  WIT_CONTENT1 = EMPTY_CLOB() WHERE WI_ID = "
//								+ wiId);
//
//				// 锁定数据行进行更新，注意“for update”语句
//				rs = stmt.executeQuery(" SELECT WIT_CONTENT1 "
//						+ " FROM TB_ZBS_WORKINFO " + " WHERE WI_ID = " + wiId
//						+ " FOR UPDATE");
//				// Obtain the Clob locator
//				if (rs.next()) {
//					
//					//得到java.sql.Clob对象后强制转换为oracle.sql.CLOB   
//					oracle.sql.CLOB clob = (oracle.sql.CLOB) rs.getClob(1);   
//					outStream = clob.getCharacterOutputStream();   
//					//data是传入的字符串，定义：String data   
//					char[] c = witContent.toCharArray();   
//					outStream.write(c, 0, c.length); 
//				}
//					outStream.flush();   
//					outStream.close();   
//				// zhangguokai 20031229-->
////				stmt.executeUpdate(" UPDATE TB_ZBS_WORKINFO SET  WI_ID = "
////						+ wiId + " WHERE WI_ID = " + wiId);
//			}catch (Exception e) {
//				e.printStackTrace();
//				traceSQL("SQL_FAIL", "", e);
//			} finally {
//				try {
//					if (rs != null) {
//						rs.close();
//					}
//					if (stmt != null) {
//						stmt.close(); 
//						// Close statement which also closes
//						// open result sets
//					}
//				} catch (SQLException ex) {
//					traceSQL("SQL_FAIL", "", ex);
//				} catch (Exception ee) {
//					ee.printStackTrace();
//				}
//			}
//		}
//	}

}