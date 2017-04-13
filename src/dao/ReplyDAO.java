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

		int reNO = 0; 
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into reply(re_no,art_no,mem_id,re_content,re_date)"			);
			//시퀀스 게시글 번호,등록하는 아이디,댓글 내용,등록날짜
			sql.append("values(RE_NO_SEQ.nextval, ? ,? , ?, sysdate)"						);
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, reply.getArtNo()); // 
			pstmt.setString(2, reply.getMemId());
			pstmt.setString(3, reply.getReContent());
			
			pstmt.executeUpdate();
			pstmt.close();

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
