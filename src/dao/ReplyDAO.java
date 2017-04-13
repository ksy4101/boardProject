package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import conn.DBConn;
import vo.ReplyVO;

public class ReplyDAO {

	// 댓글 등록
	public void insertReply(ReplyVO reply) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();

			sql.append("insert into reply(re_no,art_no,mem_id,re_content,re_date)");
			// 시퀀스 게시글 번호,등록하는 아이디,댓글 내용,등록날짜
			sql.append("values(RE_NO_SEQ.nextval, ? ,? , ?, sysdate)");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, reply.getArtNo()); //
			pstmt.setString(2, reply.getMemId());
			pstmt.setString(3, reply.getReContent());

			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	

	// 댓글 수정
	public void updateReply(ReplyVO reply) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update  reply                    												");
			sql.append("set re_content = ?																");
			sql.append("where re_no = ?															");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, reply.getReContent());
			pstmt.setInt(2, reply.getReNo());
			
			
			pstmt.executeUpdate();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			
		}
		
	}
	
	//댓글 삭제
	public void deletetReply(int reNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("delete from reply				");
			sql.append("where re_no = ?					");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, reNo);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//댓글 삭제
		public void deletetReply(String memId) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBConn.getConnection();
				
				StringBuilder sql = new StringBuilder();
				sql.append("delete from reply				");
				sql.append("where mem_id = ?					");
				pstmt = conn.prepareStatement(sql.toString());
				
				pstmt.setString(1, memId);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
	
	//내 댓글 조회
	public List<ReplyVO> selectMyReply(String memId) throws SQLException {
		ArrayList<ReplyVO> replys = new ArrayList<ReplyVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			
			
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select re_no,art_no,re_content, mem_id,re_date		");
			sql.append("from reply								");
			sql.append("where memId = ?							");
			sql.append("order by re_date DESC					");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery(sql.toString());
			
			while(rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setReNo(rs.getInt(1));
				reply.setReNo(rs.getInt(2));
				reply.setReContent(rs.getString(3));
				memId = rs.getString(4);
				reply.setReDate(rs.getString(5));
				replys.add(reply);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null) rs.close(); 
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		

		return replys;
	}
	
	//전체 댓글 조회
		public List<ReplyVO> selectReplyList(int artNo) throws SQLException {
			ArrayList<ReplyVO> replys = new ArrayList<ReplyVO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBConn.getConnection();
				
				
				
				StringBuilder sql = new StringBuilder();
				
				sql.append("select re_no,art_no,re_content, mem_id,re_date		");
				sql.append("from reply								");
				sql.append("where artNo = ?							");
				sql.append("order by re_date desc					");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, artNo);
				rs = pstmt.executeQuery(sql.toString());
				
				while(rs.next()) {
					ReplyVO reply = new ReplyVO();
					reply.setReNo(rs.getInt(1));
					artNo = rs.getInt(2);
					reply.setReContent(rs.getString(3));
					reply.setMemId(rs.getString(4));
					reply.setReDate(rs.getString(5));
					replys.add(reply);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(rs != null) rs.close(); 
					if (pstmt != null) pstmt.close();
					if (conn != null) conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			return replys;
		}

}