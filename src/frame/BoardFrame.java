package frame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.BoardDAO;
import dao.MemberDAO;
import vo.BoardVO;
import vo.MemberVO;

public class BoardFrame extends JFrame{

	private JPanel contentPane;
	public static JPanel rightPanel = new JPanel();
	public static JPanel leftPanel = new JPanel();
	public static CardLayout card = new CardLayout();
	public static int index, boardNo;

	public BoardFrame(LoginFrame login) {
		MemberVO member = login.getLogin();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1800, 1000);
		setTitle("게시판");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBounds(0, 0, 500, 962);
		contentPane.add(leftPanel);
		leftPanel.setLayout(card);
		
		leftPanel.add("left", new LeftBoardPanel(BoardFrame.this, member));
		card.show(leftPanel, "left");


		rightPanel.setBounds(500, 0, 1284, 962);
		contentPane.add(rightPanel);
		rightPanel.setLayout(card);
		
		rightPanel.add("main", new ArticlePanel(member,0));
		
		card.show(rightPanel, "main");
		
		
		
		setVisible(true);

	}
}
