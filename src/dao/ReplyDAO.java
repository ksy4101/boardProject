package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conn.DBConn;
import vo.ReplyVO;

public class ReplyDAO {
	
	
	
	//댓글 등록
	public void insertReply (ReplyVO reply) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt =  null;
	
		int reNO = 0; 
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into reply(re_no,art_no,mem_id,re_content,re_date)"			);
			//시퀀스 게시글 번호,등록하는 아이디,댓글 내용,등록날짜
			sql.append("values(RE_NO_SEQ.nextval, ? ,? , ?, sysdate)"						);
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, reply.getArtNO()); // 
			pstmt.setString(2, reply.getMemId());
			pstmt.setString(3, reply.getReContent());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			
		
			
			
		} finally {
			if(conn != null)conn.close();
			if(pstmt != null)pstmt.close();
		
			
		}
		
		
	} 
	
	//댓글 수정
	public void updateReply(ReplyVO reply) throws SQLException {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("123123");
			
		} finally {
			if(conn != null)conn.close();
			if(pstmt != null)pstmt.close();
		}
		
	}
	
	
}


