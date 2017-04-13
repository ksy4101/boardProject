package dao;

import java.sql.*;
import java.awt.*;
import java.awt.Event.*;

import vo.ReplyVO;
import conn.DBConn;

public class ReplyDAO {

	public void insertReply(ReplyVO reply) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into reply (reNo, memId, reContent, reDate) ");
			sql.append("values (?, ?, ?, sysdate)");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, reply.getReNo());
			pstmt.setString(2, reply.getMemId());
			pstmt.setString(3, reply.getReContent());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
