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
import vo.BoardVO;

public class BoardFrame extends JFrame/* implements ActionListener, ListSelectionListener */{

	private JPanel contentPane;
	public static JPanel rightPanel = new JPanel();
	public static JPanel leftPanel = new JPanel();
	public static CardLayout card = new CardLayout();
	private JButton boardMgrBtn = new JButton("\uAC8C\uC2DC\uD310 \uAD00\uB9AC");
	private BoardDAO boardDAO = new BoardDAO();
	//private BoardVO boardVO = new BoardVO();	
	//private int boardNo;
	private JList<String> list;
	private List<BoardVO> boardList;
	public static int index, boardNo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardFrame frame = new BoardFrame();
					//frame.addEventListener();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*private void addEventListener() {
		boardMgrBtn.addActionListener(this);
		list.addListSelectionListener(this);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		boolean flag = e.getValueIsAdjusting();
		
		if(flag){
			index = list.getSelectedIndex();
			boardNo = boardList.get(index).getBoardNo();
			System.out.println("게시판번호 : " + boardList.get(index).getBoardNo());
			System.out.println("게시판이름 : " + boardList.get(index).getBoardName());
			rightPanel.add("article", new ArticlePanel(boardNo));
			card.show(rightPanel, "article");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object target =  e.getSource();
		if (target == boardMgrBtn) {
			rightPanel.add("modify", new ModifyPanel());
			card.show(rightPanel, "modify");
		} 
	}*/		

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

		//JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setBounds(0, 0, 500, 962);
		contentPane.add(leftPanel);
		leftPanel.setLayout(card);
		
		leftPanel.add("left", new LeftBoardPanel());
		card.show(leftPanel, "left");

		/*JPanel loginPanel = new JPanel();
		loginPanel.setLocation(36, 35);
		loginPanel.setSize(271, 190);
		leftPanel.add(loginPanel);
		loginPanel.setLayout(null);

		JLabel idLable = new JLabel("000\uB2D8\r");
		idLable.setFont(new Font("굴림", Font.PLAIN, 25));
		idLable.setBounds(104, 27, 63, 47);
		loginPanel.add(idLable);

		JButton imModifyBtn = new JButton("\uC815\uBCF4\uC218\uC815");
		imModifyBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		imModifyBtn.setBounds(28, 153, 97, 23);
		loginPanel.add(imModifyBtn);

		JButton logoutBtn = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logoutBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logoutBtn.setBounds(150, 153, 97, 23);
		loginPanel.add(logoutBtn);

		JLabel mentLabel = new JLabel("\uD658\uC601\uD569\uB2C8\uB2E4!");
		mentLabel.setFont(new Font("굴림", Font.PLAIN, 25));
		mentLabel.setBounds(75, 84, 128, 47);
		loginPanel.add(mentLabel);

		boardMgrBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		boardMgrBtn.setBounds(329, 269, 114, 34);
		leftPanel.add(boardMgrBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 318, 407, 610);
		leftPanel.add(scrollPane);
		
		try {
			boardList = boardDAO.selectAllBoard();

			String[] BoardNameVT = new String[boardList.size()];
			
			for(int i = 0 ; i < boardList.size() ; i++) {
				BoardNameVT[i] = boardList.get(i).getBoardName();
			}
			
			list = new JList<String>(BoardNameVT);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		list.setToolTipText("");
		list.setForeground(Color.BLACK);
		list.setFont(new Font("굴림", Font.PLAIN, 40));
		list.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(list);*/

		rightPanel.setBounds(500, 0, 1284, 962);
		contentPane.add(rightPanel);
		rightPanel.setLayout(card);
		
		rightPanel.add("main", new ArticlePanel(0));
		//rightPanel.add("article", new ArticlePanel(boardNo));
		//rightPanel.add("insertArticle", new InsertArticleJPanel(boardNo));
		//rightPanel.add("modify", new ModifyPanel());
		
		card.show(rightPanel, "main");

		/*RightPanel.add("modify", new ModifyPanel());
		RightPanel.add("article", new ArticlePanel());

		card.show(RightPanel, "article");*/

	}
}
