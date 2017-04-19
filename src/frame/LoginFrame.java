package frame;
//로그인 프레임

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.MemberDAO;
import vo.MemberVO;
import javax.swing.JPasswordField;
import java.awt.Font;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField pwField;
	private MemberVO login = new MemberVO();
	private JPasswordField passwordField;

	public LoginFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼을 누를경우 프로그램 종료
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 10));
		lblNewLabel.setBounds(67, 96, 67, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(67, 135, 67, 15);
		contentPane.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setBounds(146, 93, 116, 21);
		contentPane.add(idField);
		idField.setColumns(10);
		
		pwField = new JPasswordField();
		pwField.setBounds(146, 132, 116, 21);
		contentPane.add(pwField);
		pwField.setColumns(10);
		
		JButton loginBtn = new JButton("\uB85C\uADF8\uC778");
		//로그인 버튼을 누를 경우 로직
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();//DB접근을 위한 클래스 선언
				
				 try {
					login = dao.selectMember(idField.getText());//현재 로그인 변수에 아이디필드에 해당하는 아이디가 있는 경우 값을 넣음
					
					if(login == null){//해당하는 아이디가 없는 경우
						JOptionPane.showMessageDialog(null,"ID가 존재하지 않습니다.");
					}
					else{//해당하는 아이디가 있는 경우
						login = dao.selectLogin(idField.getText(), pwField.getText());//로그인 변수에 로그인 메소드를 이용하여 값을 넣음
						if(login == null){//일치하는 데이터가 없는 경우
							JOptionPane.showMessageDialog(null,"ID와 비밀번호가 일치하지 않습니다.");
						}
						else{//아이디와 비밀번호가 모두 일치하는 경우
							setVisible(false);
							//게시판으로 가버렷!
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		loginBtn.setBounds(281, 92, 97, 23);
		contentPane.add(loginBtn);
		
		JButton insertBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		//회원 가입 버튼을 눌렀을 경우 로직
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);//현재창을 비활성화 시킨후
				new InsertFrame();//로그인 프레임을 불러온다.
			}
		});
		insertBtn.setBounds(281, 131, 97, 23);
		contentPane.add(insertBtn);
		
		JButton searchBtn = new JButton("\uC544\uC774\uB514 / \uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		//아이디/비밀번호 찾기 버튼을 눌렀을 경우 로직
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);//현재창을 비활성화 시킨후
				new SearchFrame();//서치 프레임을 불러온다.
				
			}
		});
		searchBtn.setBounds(156, 164, 222, 23);
		contentPane.add(searchBtn);
		
		
		setVisible(true);
	}
}
