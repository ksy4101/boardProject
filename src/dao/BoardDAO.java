package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public void deleteBoard(List<Integer> list) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConn.getConnection();

			for (int i = 0; i < list.size(); i++) {
				StringBuilder sql = new StringBuilder();
				sql.append("delete from reply         																					");
				sql.append("where art_no in (select art_no from article where board_no = ?)							");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, list.get(i));

				pstmt.executeUpdate();
				pstmt.close();

				sql.delete(0, sql.length());

				sql.append("delete from article         										");
				sql.append("where board_no = ?												");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, list.get(i));

				pstmt.executeUpdate();
				pstmt.close();

				sql.delete(0, sql.length());

				sql.append("delete from board         										");
				sql.append("where board_no = ?												");
				pstmt = conn.prepareStatement(sql.toString());

				pstmt.setInt(1, list.get(i));

				pstmt.executeUpdate();
			}

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

	// 게시판 목록조회
	public List<BoardVO> selectAllBoard() throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> boards = new ArrayList<BoardVO>();

		try {

			conn = DBConn.getConnection();

			stmt = conn.createStatement();

			StringBuilder sql = new StringBuilder();
			sql.append("select board_no, board_name								");
			sql.append("from board 											");
			sql.append("order by board_name asc						");
			rs = stmt.executeQuery(sql.toString());

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt(1));
				board.setBoardName(rs.getString(2));
				boards.add(board);
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

		return boards;

	}

}