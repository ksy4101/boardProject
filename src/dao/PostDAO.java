package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn.DBConn;
import vo.PostVO;

public class PostDAO {
	
	//동을 입력받아 리스트에 출력하는 메소드
	public List<PostVO> selectAddress(String dong) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostVO> list = new ArrayList<PostVO>();//리턴할 ArrayList 객체 생성
		try {
			conn = DBConn.getConnection();//커넥션 연결
			StringBuilder sql = new StringBuilder();//쿼리문 받을 변수 선언
			
			sql.append("select POST_NO, SIDO, GUGUN, DONG, BUNJI, ZIPCODE, RI, BLDG ");
			sql.append("from POST ");
			sql.append("where DONG like ? ");
			
			pstmt = conn.prepareStatement(sql.toString());//쿼리문 입력
			
			pstmt.setString(1, "%" + dong + "%");//입력받은 dong값 세팅
			
			rs = pstmt.executeQuery();//쿼리문 실행 및 resultset에 저장
			
			while(rs.next()){
				int postNo = rs.getInt(1);
				String sido = rs.getString(2);
				String gugun = rs.getString(3);
				dong = rs.getString(4);
				String bunji = rs.getString(5);
				String zipcode = rs.getString(6);
				String ri = rs.getString(7);
				String bldg = rs.getString(8);
				list.add(new PostVO(postNo, sido, gugun, dong, bunji, zipcode, ri, bldg));//리스트에 주소 추가
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
}
