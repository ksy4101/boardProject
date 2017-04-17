package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dao.BoardDAO;
import vo.BoardVO;

public class ModifyPanel extends JPanel {
	private JTextField AddBoardTF;
	private BoardDAO dao = new BoardDAO();
	private int y = 10;

	/**
	 * Create the panel.
	 */
	public ModifyPanel() {
		setBounds(0, 0, 1284, 962);
		setLayout(null);

		AddBoardTF = new JTextField();
		AddBoardTF.setFont(new Font("굴림", Font.PLAIN, 25));
		AddBoardTF.setBounds(133, 773, 369, 59);
		add(AddBoardTF);
		AddBoardTF.setColumns(10);

		JButton AddBoardBtn = new JButton("\uB4F1\uB85D");
		AddBoardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AddBoardBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		AddBoardBtn.setBounds(524, 773, 122, 59);
		add(AddBoardBtn);

		JButton DeleteBoardBtn = new JButton("\uC120\uD0DD\uC0AD\uC81C");
		DeleteBoardBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		DeleteBoardBtn.setBounds(934, 773, 161, 59);
		add(DeleteBoardBtn);

		JPanel AllBoardListPanel = new JPanel();
		AllBoardListPanel.setBackground(Color.WHITE);
		AllBoardListPanel.setBounds(133, 114, 962, 610);
		add(AllBoardListPanel);
		AllBoardListPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(480, 5, 2, 2);
		AllBoardListPanel.add(scrollPane);

		try {
			List<BoardVO> boardList = dao.selectAllBoard();

			for(int i = 0 ; i < boardList.size() ; i++) {
				JPanel BoardListPanel = new JPanel();
				BoardListPanel.setBackground(Color.WHITE);
				BoardListPanel.setBounds(12, y, 912, 60);
				AllBoardListPanel.add(BoardListPanel);
				BoardListPanel.setLayout(null);
	
				JButton btnNewButton_1 = new JButton("\uC218\uC815");
				btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 25));
				btnNewButton_1.setBounds(720, 10, 97, 40);
				BoardListPanel.add(btnNewButton_1);
	
				JCheckBox checkBox = new JCheckBox("");
				checkBox.setBackground(Color.WHITE);
				checkBox.setFont(new Font("굴림", Font.PLAIN, 18));
				checkBox.setBounds(854, 20, 21, 23);
				BoardListPanel.add(checkBox);
	
				JTextField ModifyBoardTF = new JTextField();
				ModifyBoardTF.setEditable(false);
				ModifyBoardTF.setText(boardList.get(i).getBoardName());
				ModifyBoardTF.setFont(new Font("굴림", Font.PLAIN, 50));
				ModifyBoardTF.setBounds(0, 0, 400, 60);
				BoardListPanel.add(ModifyBoardTF);
				
				y += 80;	
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}






