package com.icss.oa.fo.admin;

import java.sql.*;
import java.util.StringTokenizer;

import org.quartz.utils.DBConnectionManager;

/**
 * 该类用于数据事务处理
 * Title:        fo story
 * Description:  fo.db
 * Copyright:    Copyright (c) 2004
 * Company:      nfc
 * @author sloan
 * @version 1.0
 */

public class DbTrans {

  public Connection conn;//这里修改了static的申明
  Statement stmt;
  boolean isAutoCommit;

  /**
   * 构造函数
   */
  public DbTrans(){
      initConnection();
      System.out.println("初始化连接");
  }

  /**
   * 带参数的构造函数
   * @param conn 连接
   */
  public DbTrans(Connection conn){
      this.conn = conn;
  }

  /**
   * 初始化建立连接
   */
  private void initConnection(){
      try{
          if(conn == null){
              DBConnectionManager connMgr=DBConnectionManager.getInstance();
              conn = connMgr.getConnection("oracle");
              System.out.println("初始化连接initConnection");
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
   * 过程开始
   * @throws SQLException 捕捉错误
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
   * 数据事务提交
   * @throws SQLException 捕捉错误
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
   * 数据事务回滚
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
   * 判断是否为自动加入数据模式
   * @return boolean值
   * @throws SQLException 捕捉错误
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
   * executeQuery操作，用于数据查询，主要是Select
   * @param sql 查询字段
   * @return 数据集
   * @throws SQLException 捕捉错误
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
   * executeUpdate操作，用于数据更新，主要是Update，Insert
   * @param sql 查询字段
   * @throws SQLException 捕捉错误
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
   * 关闭对象
   * @throws SQLException 捕捉错误
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
   * 收尾和垃圾收集
   * @throws Throwable 捕捉错误
   */
  //protected void finalize() throws Throwable{
    //  close();
  //}
}