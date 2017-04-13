package dao;
//디비 이용하는 메소드들 만드세여

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conn.DBConn;
import vo.MemberVO;

public class MemberDAO {
	//로그인 부분
	public MemberVO selectLogin(String id, String password){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConnection();//커넥션 연결
			StringBuilder sql = new StringBuilder();//쿼리문 받을 변수 선언
			sql.append("select MEM_ID, JUMIN, PASSWORD, NAME, POST_NO, PHONE, EMAIL, MEMO, GRADE, ADDRESS ");//쿼리문 입력
			sql.append("from MEMBER");//쿼리문 입력
			sql.append("where MEM_ID = ? and PASSWORD = ?");//쿼리문 입력
			
			pstmt.setString(1, id);//입력받은 id값 세팅
			pstmt.setString(2, password);//입력받은 password값 세팅
			
			pstmt = conn.prepareStatement(sql.toString());//쿼리문 입력
			
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
				//return new MemberVO(memId, jumin, psw, name, post, phone, email, memo, grade, address);
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
}
