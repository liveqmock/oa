package com.icss.oa.fo.admin;

import java.sql.*;
import java.util.StringTokenizer;

import org.quartz.utils.DBConnectionManager;

/**
 * ������������������
 * Title:        fo story
 * Description:  fo.db
 * Copyright:    Copyright (c) 2004
 * Company:      nfc
 * @author sloan
 * @version 1.0
 */

public class DbTrans {

  public Connection conn;//�����޸���static������
  Statement stmt;
  boolean isAutoCommit;

  /**
   * ���캯��
   */
  public DbTrans(){
      initConnection();
      System.out.println("��ʼ������");
  }

  /**
   * �������Ĺ��캯��
   * @param conn ����
   */
  public DbTrans(Connection conn){
      this.conn = conn;
  }

  /**
   * ��ʼ����������
   */
  private void initConnection(){
      try{
          if(conn == null){
              DBConnectionManager connMgr=DBConnectionManager.getInstance();
              conn = connMgr.getConnection("oracle");
              System.out.println("��ʼ������initConnection");
          }
      }
      catch(Exception ex){
          System.out.println("Can not get new Connection"+ex.getMessage());
      }
  }

  public PreparedStatement getPreparedStmt(String sql) throws SQLException{
      PreparedStatement preStmt=null;
      try {
          preStmt = conn.prepareStatement(sql);
      }
      catch(SQLException ex){
          ex.printStackTrace();
          throw ex;
      }
      return preStmt;
  }

  /**
   * ���̿�ʼ
   * @throws SQLException ��׽����
   */
  public void beginTrans() throws SQLException {
      try {
          isAutoCommit = conn.getAutoCommit();
          conn.setAutoCommit(false);
      }
      catch(SQLException ex) {
          ex.printStackTrace();
          System.out.print("beginTrans Errors");
      throw ex;
      }
  }

  /**
   * ���������ύ
   * @throws SQLException ��׽����
   */
  public void commit() throws SQLException {
      try{
          conn.commit();
          conn.setAutoCommit(isAutoCommit);
      }
      catch(SQLException ex) {
          ex.printStackTrace();
          System.out.print("Commit Errors!");
          throw ex;
      }
  }

  /**
   * ��������ع�
   */
  public void roolback() {
      try {
          conn.rollback();
          conn.setAutoCommit(isAutoCommit);
      }
      catch(SQLException ex) {
          ex.printStackTrace();
          System.out.print("Roolback Error!");
      }
  }

  /**
   * �ж��Ƿ�Ϊ�Զ���������ģʽ
   * @return booleanֵ
   * @throws SQLException ��׽����
   */
  public boolean getAutoCommit() throws SQLException {
      boolean result = false;
      try {
          result = conn.getAutoCommit();
      }
      catch(SQLException ex) {
          ex.printStackTrace();
          System.out.println("getAutoCommit fail "+ex.getMessage());
          throw ex;
      }
      return result;
  }

  /**
   * executeQuery�������������ݲ�ѯ����Ҫ��Select
   * @param sql ��ѯ�ֶ�
   * @return ���ݼ�
   * @throws SQLException ��׽����
   */
  public ResultSet executeQuery(String sql) throws SQLException {
      ResultSet rs = null;
      try {
		  System.out.println("executeQueryok1");
          stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//		stmt=conn.createStatement();
          System.out.println("executeQueryok2");
		
          rs = stmt.executeQuery(sql);
		System.out.println("executeQueryok3");
      }
      catch (SQLException ex) {
          ex.printStackTrace();
          System.out.println("dbTrans.executeQuery:"+ex.getMessage());
          throw ex;
      }
      return rs;
  }

  /**
   * executeUpdate�������������ݸ��£���Ҫ��Update��Insert
   * @param sql ��ѯ�ֶ�
   * @throws SQLException ��׽����
   */
  public void executeUpdate(String sql) throws SQLException {
    try {
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        stmt.executeUpdate(sql);
    }
    catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("dbTrans.executeUpdate:"+ex.getMessage());
        throw ex;
    }
  }

  public int[] doBatch(String sql) throws SQLException {
      int[] rowResult=null;
      String a;
      try{
          stmt = conn.createStatement();
          StringTokenizer st=new StringTokenizer(sql,";");
          while (st.hasMoreElements()) {
              a = st.nextToken();
              stmt.addBatch(a);
          }
          rowResult=stmt.executeBatch();
      }
      catch (SQLException ex) {
          ex.printStackTrace();
          System.out.println("dbTrans.doBatch"+ex.getMessage());
          throw ex;
      }
      return rowResult;
  }

  /**
   * �رն���
   * @throws SQLException ��׽����
   */
  public void close() throws SQLException{
		if(conn != null) conn.close();
		//if(rset != null) rset.close();
		if(stmt != null) stmt.close();
  }
  /*
  public void close() throws SQLException {
      try {
          stmt.close();
          stmt = null;
          conn.close();
          conn = null;
      }
      catch (SQLException ex) {
          ex.printStackTrace();
          System.out.println("Closeing connection fail"+ex.getMessage());
          throw ex;
      }
  }
  */
  /**
   * ��β�������ռ�
   * @throws Throwable ��׽����
   */
  //protected void finalize() throws Throwable{
    //  close();
  //}
}