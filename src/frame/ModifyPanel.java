package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dao.BoardDAO;
import vo.BoardVO;

//게시판 관리
public class ModifyPanel extends JPanel {

	private List<Integer> boardNoList = new ArrayList<Integer>();
	private JTextField addBoardTF;
	private BoardDAO dao = new BoardDAO();
	private JPanel AllBoardListPanel, BoardListPanel;
	private int y = 10;

	/**
	 * Create the panel.
	 */
	public ModifyPanel() {
		setBounds(0, 0, 1284, 962);
		setLayout(null);

		addBoardTF = new JTextField();
		addBoardTF.setFont(new Font("굴림", Font.PLAIN, 25));
		addBoardTF.setBounds(133, 773, 369, 59);
		add(addBoardTF);
		addBoardTF.setColumns(10);

		AllBoardListPanel = new JPanel();
		AllBoardListPanel.setBackground(Color.WHITE);
		AllBoardListPanel.setBounds(133, 114, 962, 610);
		add(AllBoardListPanel);
		AllBoardListPanel.setLayout(null);

		JButton addBoardBtn = new JButton("\uB4F1\uB85D");
		addBoardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 게시판 등록 버튼
				try {
					dao.insertBoard(new BoardVO(0, addBoardTF.getText()));
					JOptionPane.showMessageDialog(addBoardBtn, "게시판 등록완료");
					AllBoardListPanel.removeAll();
					AllBoardListPanel.revalidate();
					AllBoardListPanel.repaint();
					// addBoardTF.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				addBoardTF.revalidate();

				BoardFrame.rightPanel.add("modify", new ModifyPanel());
				BoardFrame.card.show(BoardFrame.rightPanel, "modify");

				BoardFrame.leftPanel.add("left", new LeftBoardPanel());
				BoardFrame.card.show(BoardFrame.leftPanel, "left");
			}
		});
		addBoardBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		addBoardBtn.setBounds(524, 773, 122, 59);
		add(addBoardBtn);

		JButton DeleteBoardBtn = new JButton("\uC120\uD0DD\uC0AD\uC81C");
		DeleteBoardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// System.out.println("-------------------" + boardNoList.size());
				// DB에서 선택 삭제하기
				try {
					dao.deleteBoard(boardNoList);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(DeleteBoardBtn, "삭제되었습니다");

				BoardFrame.rightPanel.add("modify", new ModifyPanel());
				BoardFrame.card.show(BoardFrame.rightPanel, "modify");

				BoardFrame.leftPanel.add("left", new LeftBoardPanel());
				BoardFrame.card.show(BoardFrame.leftPanel, "left");
			}
		});
		DeleteBoardBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		DeleteBoardBtn.setBounds(934, 773, 161, 59);
		add(DeleteBoardBtn);

		ShowBoard();

	}

	public void ShowBoard() {

		try {
			List<BoardVO> boardList = dao.selectAllBoard();

			for (int i = 0; i < boardList.size(); i++) {
				int index = i;
				BoardListPanel = new JPanel();
				BoardListPanel.setBackground(Color.WHITE);
				BoardListPanel.setBounds(12, y, 912, 60);
				AllBoardListPanel.add(BoardListPanel);
				BoardListPanel.setLayout(null);

				final JButton updateB = new JButton("\uC218\uC815");
				updateB.setFont(new Font("굴림", Font.PLAIN, 25));
				updateB.setBounds(720, 10, 97, 40);
				BoardListPanel.add(updateB);

				JCheckBox checkBox = new JCheckBox("");

				checkBox.setBackground(Color.WHITE);
				checkBox.setFont(new Font("굴림", Font.PLAIN, 18));
				checkBox.setBounds(854, 20, 21, 23);
				BoardListPanel.add(checkBox);

				final JTextField modifyBoardTF = new JTextField();
				modifyBoardTF.setEditable(false);
				modifyBoardTF.setText(boardList.get(i).getBoardName());
				modifyBoardTF.setFont(new Font("굴림", Font.PLAIN, 50));
				modifyBoardTF.setBounds(0, 0, 400, 60);
				BoardListPanel.add(modifyBoardTF);

				y += 80;

				checkBox.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 선택 체크박스
						if (checkBox.isSelected()) {
							boardNoList.add(boardList.get(index).getBoardNo());
						} else {
							boardNoList.remove(new Integer(boardList.get(index).getBoardNo()));
						}
					}
				});

				updateB.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 게시판이름 수정
						if (e.getActionCommand().equals("수정")) {
							modifyBoardTF.setEditable(true);
							updateB.setText("확인");
						} else if (e.getActionCommand().equals("확인")) {
							try {
								System.out.println(index);
								dao.updateBoard(
										new BoardVO(boardList.get(index).getBoardNo(), modifyBoardTF.getText()));
								modifyBoardTF.setEditable(false);
								updateB.setText("수정");
								JOptionPane.showMessageDialog(updateB, "수정되었습니다");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						BoardFrame.leftPanel.add("left", new LeftBoardPanel());
						BoardFrame.card.show(BoardFrame.leftPanel, "left");
					}
				});

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}