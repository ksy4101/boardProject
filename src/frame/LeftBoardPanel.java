package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.javafx.font.Disposer;

import dao.BoardDAO;
import vo.BoardVO;
import vo.MemberVO;

public class LeftBoardPanel extends JPanel{

	public static JPanel rightPanel = new JPanel();
	public static CardLayout card = new CardLayout();
	private JButton boardMgrBtn = new JButton("\uAC8C\uC2DC\uD310 \uAD00\uB9AC");
	private BoardDAO boardDAO = new BoardDAO();
	private JList<String> list;
	private List<BoardVO> boardList;
	public static int index, boardNo;

	public LeftBoardPanel(BoardFrame board, MemberVO member) {
		setBackground(Color.WHITE);
		setBounds(0, 0, 500, 962);
		setLayout(null);

		JPanel loginPanel = new JPanel();
		loginPanel.setLocation(36, 35);
		loginPanel.setSize(243, 190);
		add(loginPanel);
		loginPanel.setLayout(null);

		JLabel idLable = new JLabel(member.getMemId()+"\uB2D8\r");
		idLable.setFont(new Font("굴림", Font.PLAIN, 18));
		idLable.setBounds(90, 28, 95, 47);
		loginPanel.add(idLable);

		JButton imModifyBtn = new JButton("\uC815\uBCF4\uC218\uC815");
		imModifyBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		imModifyBtn.setBounds(12, 153, 90, 23);
		imModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateFrame(member);
			}
		});
		loginPanel.add(imModifyBtn);

		JButton logoutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logoutBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		logoutBtn.setBounds(145, 153, 86, 23);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				board.dispose();
				//프레임을 꺼야함
			}
		});
		loginPanel.add(logoutBtn);

		JLabel mentLabel = new JLabel("\uD658\uC601\uD569\uB2C8\uB2E4!");
		mentLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		mentLabel.setBounds(57, 85, 128, 47);
		loginPanel.add(mentLabel);

		boardMgrBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		boardMgrBtn.setBounds(329, 269, 114, 34);
		add(boardMgrBtn);
		if (member.getGrade().equals("0")) {
			boardMgrBtn.setVisible(false);
		} else if (member.getGrade().equals("1")) {
			boardMgrBtn.setVisible(true);
		}
		boardMgrBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object target = e.getSource();
				if (target == boardMgrBtn) {
					BoardFrame.rightPanel.add("modify", new ModifyPanel(board, member));
					BoardFrame.card.show(BoardFrame.rightPanel, "modify");
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 318, 407, 610);
		add(scrollPane);
		
		JButton memberMgrBtn = new JButton("\uD68C\uC6D0 \uAD00\uB9AC");
		memberMgrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		memberMgrBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		if(member.getGrade().equals("0")){
			memberMgrBtn.setVisible(false);
		}
		else if(member.getGrade().equals("1")){
			memberMgrBtn.setVisible(true);
		}
		memberMgrBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MgrFrame();
			}
		});
		memberMgrBtn.setBounds(203, 269, 114, 34);
		add(memberMgrBtn);

		try {
			boardList = boardDAO.selectAllBoard();

			String[] BoardNameVT = new String[boardList.size()];

			for (int i = 0; i < boardList.size(); i++) {
				BoardNameVT[i] = boardList.get(i).getBoardName();
			}

			list = new JList<String>(BoardNameVT);

		} catch (Exception e) {
			e.printStackTrace();
		}

		list.setToolTipText("");
		list.setForeground(Color.BLACK);
		list.setFont(new Font("굴림", Font.PLAIN, 25));
		list.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(list);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean flag = e.getValueIsAdjusting();
				if (flag) {
					index = list.getSelectedIndex();
					boardNo = boardList.get(index).getBoardNo();
					BoardFrame.rightPanel.add("article", new ArticlePanel(member, boardNo));
					BoardFrame.card.show(BoardFrame.rightPanel, "article");
				}
			}
		});

	}
}