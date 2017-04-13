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
			sql.append("insert into reply (re_no, art_no, mem_id, re_content, re_date)  ");
			sql.append("values (reNo_seq.nextval, ?, ?, ?, sysdate) "); 
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, reply.getArtNo());
			pstmt.setString(2, reply.getMemId());
			pstmt.setString(3, reply.getReContent());

			pstmt.executeUpdate();
			pstmt.close();

		}  finally {
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
	public void updateReply(ReplyVO reply) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update  reply  ");
			sql.append("set re_content = ?	");
			sql.append("where re_id = ? "); // 댓글번호가 같은 경우 수정
			System.out.println(sql.toString());
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, reply.getReContent());
			pstmt.setInt(2, reply.getReNo());
			
		}	finally {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			
		}
	}
}
