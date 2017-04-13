package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import conn.DBConn;


//디비 이용하는 메소드들 만드세여
//회원정보입력



//회원전체조회
public class MemberDAO {
	
	public Vector<Vector<Object>> selectMemberList() throws SQLException {
		Vector<Vector<Object>> mems = new Vector<Vector<Object>>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select mem_id, name, phone, email  							");//아이디 이름 휴대폰 이메일 입력
			sql.append("from member	    										    ");
			sql.append("order by memId asc 				        					");//아디순으로 오름차순
			rs = stmt.executeQuery(sql.toString());//rs로 넘긴다.
			while(rs.next()) {
				Vector<Object> mem = new Vector<Object>();//벡터로 불러옴
				mem.addElement(rs.getString(1));
				mem.addElement(rs.getString(2));
				mem.addElement(rs.getString(3));
				mem.addElement(rs.getString(4));
				mems.add(mem);
			}			
		} finally {
			if(rs != null) rs.close(); 
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		return mems;
	}
		
}
		

