package frame;
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

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField pwField;
	private MemberVO login = new MemberVO();

	public LoginFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(67, 96, 67, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setBounds(67, 135, 67, 15);
		contentPane.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setBounds(146, 93, 116, 21);
		contentPane.add(idField);
		idField.setColumns(10);
		
		pwField = new JTextField();
		pwField.setBounds(146, 132, 116, 21);
		contentPane.add(pwField);
		pwField.setColumns(10);
		
		JButton loginBtn = new JButton("\uB85C\uADF8\uC778");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO dao = new MemberDAO();
				
				 try {
					login = dao.selectMember(idField.getText());
					
					if(login == null){
						JOptionPane.showMessageDialog(null,"ID가 존재하지 않습니다.");
					}
					else{
						login = dao.selectLogin(idField.getText(), pwField.getText());
						if(login == null){
							JOptionPane.showMessageDialog(null,"ID와 비밀번호가 일치하지 않습니다.");
						}
						else{
							setVisible(false);
							//게시판으로 가버렷!
						}
					}
					//게시판 화면으로 전환
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		loginBtn.setBounds(281, 92, 97, 23);
		contentPane.add(loginBtn);
		
		JButton insertBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new InsertFrame();
			}
		});
		insertBtn.setBounds(281, 131, 97, 23);
		contentPane.add(insertBtn);
		
		JButton searchBtn = new JButton("\uC544\uC774\uB514 / \uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SearchFrame();
				
			}
		});
		searchBtn.setBounds(156, 164, 222, 23);
		contentPane.add(searchBtn);
		
		setVisible(true);
	}
	
}
