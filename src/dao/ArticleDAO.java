package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import conn.DBConn;
import vo.ArticleVO;

public class ArticleDAO {

	//게시글 등록
	public void insertArticle(ArticleVO art) throws SQLException{
		  Connection conn = null;
	      PreparedStatement ps = null;
	      try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into article(art_no, mem_id, board_no, subject, content, write_date)"    );
			sql.append("values(ART_NO_SEQ.nextval, ?, ?, ?, ?, sysdate)"    );
	    	  
			ps.setString(1, art.getMemId());
			ps.setInt(2, art.getBoardNo());
			ps.setString(3, art.getSubject());
			ps.setString(4, art.getContent());
			
			ps.executeUpdate();
			
			
	      } catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	      
	}
	
	//게시글 수정
	public void updateArticle(ArticleVO art) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("update article    ");
			sql.append("set subject = ? , content = ?    ");
			sql.append("where board_no = ? and art_no = ?"    );
			ps = conn.prepareStatement(sql.toString());
			
			ps.setString(1, art.getSubject());
			ps.setString(2, art.getContent());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//게시글 삭제
	
	
	//게시글 목록 조회
	public Vector<Vector<Object>> selectAllArticle() throws SQLException{
		Vector<Vector<Object>> art = new Vector<>();
		Connection conn =  null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();
			st = conn.createStatement();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select art_no, mem_id, subject, to_char(write_date, 'YYYY/MM/DD')  ");
			sql.append("from article    ");
			sql.append("where art_no = ?    ");
			rs = st.executeQuery(sql.toString());
			
			while(rs.next()){
				Vector<Object> a = new Vector<Object>();
				a.addElement(rs.getInt(1));
				a.addElement(rs.getString(2));
				a.addElement(rs.getString(3));
				a.addElement(rs.getString(4));
				art.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs != null) rs.close();
				if(st != null)st.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return art;
	}
	
	//게시글 상세 조회
	public ArticleVO selectArticle(int artNo) throws SQLException{
		ArticleVO art = new ArticleVO();
		Connection conn =  null;
		PreparedStatement ps = null;
		try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select art_no, mem_id, subject, content, write_date  ");
			sql.append("from article    ");
			sql.append("where art_no = ?, board_no = ?    ");
			ps = conn.prepareStatement(sql.toString());
			
			ps.setInt(1, art.getBoardNo());
			ps.setInt(2, art.getArtNo());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return art;
	}
	
	//게시글 검색
	
	
}
