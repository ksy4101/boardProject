package dao;
//디비 이용하는 메소드들 만드세여

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import conn.DBConn;
import vo.MemberVO;

public class MemberDAO2 {
	public Vector<Vector<Object>> selectMemberList(String keyfield, String keyword) throws SQLException {
		Vector<Vector<Object>> mem = new Vector<Vector<Object>>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConnection();
			
			stmt = conn.createStatement();
			
			StringBuilder sql = new StringBuilder();
			sql.append("select mem_id, jumin, password, name, phone, email, grade, address	" );		
			sql.append("from member 			  " );
			sql.append("where ");
			
			
			sql.append("order by department_id asc 					" );
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				Vector<Object> member = new Vector<Object>();
				member.addElement(rs.getString(1));
				member.addElement(rs.getString(2));
				member.addElement(rs.getString(3));
				member.addElement(rs.getString(4));
				member.addElement(rs.getString(5));
				member.addElement(rs.getString(6));
				member.addElement(rs.getString(7));
				member.addElement(rs.getString(8));
				member.addElement(rs.getString(9));
				mem.add(member);
			}			
		} finally {
			if(rs != null) rs.close(); 
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		return mem;
	}
	


		

	// 회원정보입력
	public void insertMemeber(MemberVO member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean ok = false;
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

			

			int r = pstmt.executeUpdate();
			
			if(r>0){
                System.out.println("가입 성공");   
                ok=true;
           
            }
           

		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}



// 회원전체조회


	public Vector<Vector<Object>> selectAllMember() throws SQLException {
		Vector<Vector<Object>> mems = new Vector<Vector<Object>>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuilder sql = new StringBuilder();
			sql.append("select mem_id, name, phone, email  							");// 아이디
			sql.append("from member	    										    ");
			sql.append("order by memId asc 				        					");// 아디순으로
																						// 오름차순
			rs = stmt.executeQuery(sql.toString());// rs로 넘긴다.
			while (rs.next()) {
				Vector<Object> mem = new Vector<Object>();// 벡터로 불러옴
				mem.addElement(rs.getString(1));
				mem.addElement(rs.getString(2));
				mem.addElement(rs.getString(3));
				mem.addElement(rs.getString(4));
				mems.add(mem);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return mems;
	}
	////////////////////////////////////////////////
	
	//회원 상세 조회
	public MemberVO selectMember(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();//커넥션 연결
			StringBuilder sql = new StringBuilder();//쿼리문 받을 변수 선언
			sql.append("select MEM_ID, JUMIN, PASSWORD, NAME, POST_NO, PHONE, EMAIL, MEMO, GRADE, ADDRESS ");//쿼리문 입력
			sql.append("from MEMBER");//쿼리문 입력
			sql.append("where MEM_ID = ? ");//쿼리문 입력
			
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
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	//회원 로그인
	public String selectLogin(String id, String password) throws SQLException{
		
		MemberVO member = this.selectMember(id);//입력한 아이디에 해당하는 객체
		
		if(member.getPassword().equals(password)){//해당 객체의 비밀번호와 입력한 비밀번호가 같은 경우
			return id;//아이디 반환
		}
		else{//같지 않은경우
			return null;//null 반환
		}
	}
	
	//id, 이름, 주민번호 체크 하는 메소드
	public String checkData(String id, String name, String jumin) throws SQLException{
		MemberVO member = this.selectMember(id);//입력한 아이디에 해당하는 객체
		if(member.getPassword().equals(jumin) && member.getName().equals(name)){
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
			
			pstmt.setString(1, id);//입력받은 id값 세팅
			
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//전체 회원 조회
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



