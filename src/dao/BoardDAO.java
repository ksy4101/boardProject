package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import conn.DBConn;
import vo.BoardVO;

public class BoardDAO {

	// 게시판 등록
	public void insertBoard(BoardVO board) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into board(board_no, board_name)			");
			sql.append("values(board_no_seq.nextval, ?) 						");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, board.getBoardName());

			pstmt.executeUpdate();

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

	// 게시판 수정
	public void updateBoard(BoardVO board) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update board         												");
			sql.append("set board_name = ? 												");
			sql.append("where board_no = ?												");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, board.getBoardName());
			pstmt.setInt(2, board.getBoardNo());

			pstmt.executeUpdate();

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

	// 게시판 삭제
	public void deleteBoard(int boardNo) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConn.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from reply         																					");
			sql.append("where art_no = (select art_no from article where board_no = ?)							");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);

			pstmt.executeUpdate();
			pstmt.close();

			sql.delete(0, sql.length());

			sql.append("delete from article         										");
			sql.append("where board_no = ?												");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);

			pstmt.executeUpdate();
			pstmt.close();

			sql.delete(0, sql.length());

			sql.append("delete from board         										");
			sql.append("where board_no = ?												");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, boardNo);

			pstmt.executeUpdate();

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

	// 게시판 목록 조회
	public List<BoardVO> selectAllBoard() throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector<BoardVO> board = new Vector<BoardVO>();

		try {

			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuilder sql = new StringBuilder();
			sql.append("select board_name								");
			sql.append("from board 											");
			sql.append("order by board_name asc						");
			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardName(rs.getString(1));
				board.add(bvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return board;

	}

}