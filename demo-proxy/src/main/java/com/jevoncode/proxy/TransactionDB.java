package com.jevoncode.proxy;

public class TransactionDB {
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	/**
	 * 开启事务
	 * 
	 */
	public static void beginTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			conn.setAutoCommit(false);
		}
	}

	/**
	 * 提交事务
	 * 
	 */
	public static void commitTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			conn.commit();
		}
	}

	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction() {
		Connection conn = getConnection();
		if (conn != null) {
			conn.rollback();
		}
	}

	/**
	 * 关闭连接，并移除Threadlocal中保存的连接
	 */
	public static void closeConnection() {
		Connection conn = getConnection();
		if (conn != null) {
			try {
				conn.close();
			} finally {
				threadLocal.remove();
			}
		}
	}

	/**
	 * 从Threadlocal中获取线程保护的Connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = threadLocal.get();
		if (conn == null) {
			conn = new Connection();
			threadLocal.set(conn);
		}
		return conn;
	}
}
