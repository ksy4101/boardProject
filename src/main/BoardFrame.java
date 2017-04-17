package main;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel RightPanel = new JPanel();
	private CardLayout card = new CardLayout();
	private JButton BoardMgrBtn = new JButton("\uAC8C\uC2DC\uD310 \uAD00\uB9AC");
	private BoardDAO dao = new BoardDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardFrame frame = new BoardFrame();
					frame.addEventListener();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void addEventListener() {
		BoardMgrBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton btn = (JButton) e.getSource();
		if (btn == BoardMgrBtn) {
			card.show(RightPanel, "modify");
		}

	}

	/**
	 * Create the frame.
	 */
	public BoardFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1800, 1000);
		setTitle("게시판");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel LeftPanel = new JPanel();
		LeftPanel.setBackground(Color.WHITE);
		LeftPanel.setBounds(0, 0, 500, 962);
		contentPane.add(LeftPanel);
		LeftPanel.setLayout(null);

		JPanel LoginPanel = new JPanel();
		LoginPanel.setLocation(36, 35);
		LoginPanel.setSize(271, 190);
		LeftPanel.add(LoginPanel);
		LoginPanel.setLayout(null);

		JLabel IdLable = new JLabel("000\uB2D8\r");
		IdLable.setFont(new Font("굴림", Font.PLAIN, 25));
		IdLable.setBounds(104, 27, 63, 47);
		LoginPanel.add(IdLable);

		JButton ImModifyBtn = new JButton("\uC815\uBCF4\uC218\uC815");
		ImModifyBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		ImModifyBtn.setBounds(28, 153, 97, 23);
		LoginPanel.add(ImModifyBtn);

		JButton LogoutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		LogoutBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		LogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		LogoutBtn.setBounds(150, 153, 97, 23);
		LoginPanel.add(LogoutBtn);

		JLabel MentLabel = new JLabel("\uD658\uC601\uD569\uB2C8\uB2E4!");
		MentLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		MentLabel.setBounds(75, 84, 128, 47);
		LoginPanel.add(MentLabel);

		BoardMgrBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		BoardMgrBtn.setBounds(329, 269, 114, 34);
		LeftPanel.add(BoardMgrBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 318, 407, 610);
		LeftPanel.add(scrollPane);

		ArrayList<BoardVO> boards = new ArrayList<BoardVO>();
		
		List<BoardVO> boardList = dao.selectAllBoard();

		for(int i = 0 ; i < boardList.size() ; i++) {
			boards.add(boardList.get(i).getBoardName());
		}
	
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "1\uBC88 \uAC8C\uC2DC\uD310", "2\uBC88 \uAC8C\uC2DC\uD310",
					"3\uBC88 \uAC8C\uC2DC\uD310" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setToolTipText("");
		list.setForeground(Color.BLACK);
		list.setFont(new Font("굴림", Font.PLAIN, 40));
		list.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(list);

		RightPanel.setBounds(500, 0, 1284, 962);
		contentPane.add(RightPanel);
		RightPanel.setLayout(card);

		RightPanel.add("modify", new ModifyPanel());
		RightPanel.add("article", new ArticlePanel());

		card.show(RightPanel, "article");

	}
}
