package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conn.DBConn;
import vo.ArticleVO;

public class ArticleDAO {

	//게시글 등록
	public void insertArticle(ArticleVO art) throws SQLException{
		  Connection conn = null;
	      PreparedStatement ps = null;
	      Statement s = null;
	      ResultSet rs = null;
	      try {
			conn = DBConn.getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("insert into article(art_no, mem_id, board_no, subject, content, write_date)"    );
			sql.append("values(ART_NO_SEQ.nextval, ?, ?, ?, ?, sysdate)"    );
	    	  
			ps.setString(1, art.getMemId());
			
			
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
	
	//게시글 삭제
	
	//게시글 목록 조회
	
	//게시글 상세 조회
	
	//게시글 검색

	
}
