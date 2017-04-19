package dao;
//디비 이용하는 메소드들 만드세여

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import conn.DBConn;
import vo.MemberVO;

public class MemberDAO {
	
	//회원 상세 조회
	public MemberVO selectMember(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();//커넥션 연결
			StringBuilder sql = new StringBuilder();//쿼리문 받을 변수 선언
			sql.append("select MEM_ID, to_char(JUMIN), PASSWORD, NAME, POST_NO, PHONE, EMAIL, MEMO, grade, ADDRESS  ");//쿼리문 입력
			sql.append("from MEMBER ");//쿼리문 입력
			sql.append("where MEM_ID = ?");//쿼리문 입력
			
			pstmt = conn.prepareStatement(sql.toString());//쿼리문 입력
			
			pstmt.setString(1, id);//입력받은 id값 세팅
			
			rs = pstmt.executeQuery();//쿼리문 실행 및 resultset에 저장
			
			while(rs.next()){
				String memId = rs.getString(1);
				String jumin = rs.getString(2);
				String psw = rs.getString(3);
				String name = rs.getString(4);
				int post = rs.getInt(5);
				String phone = rs.getString(6);
				String email = rs.getString(7);
				String memo = rs.getString(8);
				String grade = rs.getString(9);
				String address = rs.getString(10);
				return new MemberVO(memId, jumin, psw, name, post, phone, email, memo, grade, address);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	//아이디 찾기
	public String selectID(String name, String jumin){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id = null;
		try {
			conn = DBConn.getConnection();//커넥션 연결
			StringBuilder sql = new StringBuilder();//쿼리문 받을 변수 선언
			sql.append("select MEM_ID  ");//쿼리문 입력
			sql.append("from MEMBER ");//쿼리문 입력
			sql.append("where name = ? and jumin = ?");//쿼리문 입력
			
			pstmt = conn.prepareStatement(sql.toString());//쿼리문 입력
			
			pstmt.setString(1, name);//입력받은 name값 세팅
			pstmt.setString(2, jumin);//입력받은 jumin값 세팅
			
			rs = pstmt.executeQuery();//쿼리문 실행 및 resultset에 저장
			
			while(rs.next()){
				id = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return id;
	}
	
	//아이디 중복 확인
	public String selectDuplication(String id, int type){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();//커넥션 연결
			StringBuilder sql = new StringBuilder();//쿼리문 받을 변수 선언
			sql.append("select count(*)  ");//쿼리문 입력
			sql.append("from MEMBER ");//쿼리문 입력
			
			if(type == 1){
				sql.append("where mem_id = ?");//쿼리문 입력
			}
			else if(type == 2){
				sql.append("where jumin = ?");//쿼리문 입력
			}
			
			
			pstmt = conn.prepareStatement(sql.toString());//쿼리문 입력
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();//쿼리문 실행 및 resultset에 저장
			
			while(rs.next()){
				int index = rs.getInt(1);
				if(index == 1){
					return id;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	//회원 로그인
	public MemberVO selectLogin(String id, String password) throws SQLException{
		
		MemberVO member = this.selectMember(id);//입력한 아이디에 해당하는 객체
		if(member == null){
			return null;
		}
		if(member.getPassword().equals(password)){//해당 객체의 비밀번호와 입력한 비밀번호가 같은 경우
			return member;//아이디 반환
		}
		else{//같지 않은경우
			return null;//null 반환
		}
	}
	
	//id, 이름, 주민번호 체크 하는 메소드
	public String checkData(String id, String name, String jumin) throws SQLException{
		MemberVO member = this.selectMember(id);//입력한 아이디에 해당하는 객체
		if(member == null){
			return null;
		}
		if(member.getJumin().equals(jumin) && member.getName().equals(name)){
			return id;
		}
		return null;
	}
	
	//비밀번호 변경 - 실제로 변경하는 메소드
	public void updatePassword(String id, String password){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();//커넥션 연결
			StringBuilder sql = new StringBuilder();//쿼리문 받을 변수 선언
			sql.append("UPDATE member ");//쿼리문 입력
			sql.append("set PASSWORD = ? ");//쿼리문 입력
			sql.append("where MEM_ID = ? ");//쿼리문 입력
			pstmt = conn.prepareStatement(sql.toString());//쿼리문 입력
			
			pstmt.setString(1, password);//입력받은 pw값 세팅
			pstmt.setString(2, id);//입력받은 id값 세팅
			
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//전체 회원 조회
	public Vector<Vector<Object>> selectAllMember() throws SQLException {
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
			sql.append("order by mem_id asc 				        					");//아디순으로 오름차순
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
	
	//회원 검색
	public Vector<Vector<Object>> selectMember(String keyfield, String keyword) throws SQLException {
		
		Vector<Vector<Object>> mems = new Vector<Vector<Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			
			StringBuilder sql = new StringBuilder();
			if(keyfield.equals("ID")){
				sql.append("select mem_id, name, phone, email  							");//아이디 이름 휴대폰 이메일 입력
				sql.append("from member	    										    ");
				sql.append("where mem_id like ?   ");
				sql.append("order by mem_id asc 				        					");
			}
			else if(keyfield.equals("이름")){
				sql.append("select mem_id, name, phone, email  							");//아이디 이름 휴대폰 이메일 입력
				sql.append("from member	    										    ");
				sql.append("where name like ?   ");
				sql.append("order by mem_id asc 				        					");
			}
			else if(keyfield.equals("")){
				sql.append("select mem_id, name, phone, email  							");//아이디 이름 휴대폰 이메일 입력
				sql.append("from member	    										    ");
				sql.append("order by mem_id asc 				        					");
			}

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();//rs로 넘긴다.
			
			while(rs.next()) {
				Vector<Object> mem = new Vector<Object>();//벡터로 불러옴
				String temp1 = rs.getString(1);
				String temp2 = rs.getString(2);
				String temp3 = rs.getString(3);
				String temp4 = rs.getString(4);
				mem.addElement(temp1);
				mem.addElement(temp2);
				mem.addElement(temp3);
				mem.addElement(temp4);
				mems.add(mem);
			}
		} finally {
			if(rs != null) rs.close(); 
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		return mems;
	}
	
	//회원 등록 메소드
	public void insertMemeber(MemberVO member) throws SQLException {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = DBConn.getConnection();

	         StringBuilder sql = new StringBuilder();
	         sql.append(
	               "insert into member(mem_id, jumin, password, name, post_no, phone, email, memo, address)         ");
	         sql.append("values(?, ?, ?, ?, ?, ?, ?, ?, ? )  ");

	         pstmt = conn.prepareStatement(sql.toString());

	         pstmt.setString(1, member.getMemId());
	         pstmt.setString(2, member.getJumin());
	         pstmt.setString(3, member.getPassword());
	         pstmt.setString(4, member.getName());
	         pstmt.setInt(5, member.getPostNo());
	         pstmt.setString(6, member.getPhone());
	         pstmt.setString(7, member.getEmail());
	         pstmt.setString(8, member.getMemo());
	         pstmt.setString(9, member.getAddress());

	         pstmt.executeUpdate();


	      } finally {
	         if (pstmt != null)
	            pstmt.close();
	         if (conn != null)
	            conn.close();
	      }
	   }
	
	//회원 정보 변경 메소드
	public void updateMember(MemberVO member) throws SQLException {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = DBConn.getConnection();

	         StringBuilder sql = new StringBuilder();
	         sql.append("UPDATE member ");
	         sql.append("set POST_NO = ?, ADDRESS = ?, EMAIL = ?, PHONE = ?, MEMO = ?, grade = ?  ");
	         sql.append("where MEM_ID = ?");

	         pstmt = conn.prepareStatement(sql.toString());

	         pstmt.setInt(1, member.getPostNo());
	         pstmt.setString(2, member.getAddress());
	         pstmt.setString(3, member.getEmail());
	         pstmt.setString(4, member.getPhone());
	         pstmt.setString(5, member.getMemo());
	         pstmt.setString(6, member.getGrade());
	         pstmt.setString(7, member.getMemId());

	         pstmt.executeUpdate();


	      } finally {
	         if (pstmt != null)
	            pstmt.close();
	         if (conn != null)
	            conn.close();
	      }
	   }
	
	//회원 삭제
	public void deleteMember(String id) throws SQLException {
	      Connection conn = null;
	      CallableStatement cstmt = null;
	      try {
	         conn = DBConn.getConnection();

	         StringBuilder sql = new StringBuilder();
	         sql.append("{call removeMember(?)} ");

	         cstmt = conn.prepareCall(sql.toString());

	         cstmt.setString(1, id);

	         cstmt.executeUpdate();
	         

	      } finally {
	         if (cstmt != null)
	            cstmt.close();
	         if (conn != null)
	            conn.close();
	      }
	   }
}



