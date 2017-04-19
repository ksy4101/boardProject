package frame;
//비밀번호 변경 프레임

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.MemberDAO;
import vo.MemberVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class IDduplicationSearch extends JFrame {

	private JPanel contentPane;
	private JTextField searchIDField;
	private MemberDAO dao = new MemberDAO();
	private MemberVO member = new MemberVO();
	private String id;

	public IDduplicationSearch(InsertFrame insert) {
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uC544\uC774\uB514 \uC911\uBCF5 \uD655\uC778");
		label.setBounds(12, 45, 113, 15);
		contentPane.add(label);
			
		JLabel dupliMsg = new JLabel("");
		dupliMsg.setBounds(85, 70, 213, 49);
		contentPane.add(dupliMsg);
		
		searchIDField = new JTextField();
		searchIDField.setBounds(137, 42, 116, 21);
		contentPane.add(searchIDField);
		searchIDField.setColumns(10);
		
		
		JButton searchBtn = new JButton("\uAC80\uC0C9");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = dao.selectDuplication(searchIDField.getText(),1);
				if(id == null){
					dupliMsg.setText("중복된 아이디가 없습니다.");
				}
				else{
					dupliMsg.setText("중복된 아이디 입니다.");
				}
			}
		});
		searchBtn.setBounds(275, 41, 97, 23);
		contentPane.add(searchBtn);
		
		
		JButton insertIDBtn = new JButton("\uD655\uC778");
		insertIDBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dupliMsg.getText().equals("중복된 아이디 입니다.")){
					JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
				}
				else if(dupliMsg.getText().equals("중복된 아이디가 없습니다.")){
					id = dao.selectDuplication(searchIDField.getText(),1);
					if(id == null){
						String p = "([a-z_0-9]{4,13})";
						boolean b = Pattern.matches(p, searchIDField.getText());
						if(b){
							((InsertFrame) insert).setID(searchIDField.getText());
							((InsertFrame) insert).setIDField(searchIDField.getText());
							dispose();//정보 전달이후 현재창 종료
						}
						else{
							JOptionPane.showMessageDialog(null, "아이디는 영어 소문자와 숫자의 조합으로 4~13자리만 입력 가능합니다.");
						}					
					}
					else{
						JOptionPane.showMessageDialog(null, "중복된 아이디 입니다.");
					}
				}
				
			}
		});
		insertIDBtn.setBounds(75, 129, 97, 23);
		contentPane.add(insertIDBtn);
		
		JButton returnBtn = new JButton("\uCDE8\uC18C");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		returnBtn.setBounds(201, 129, 97, 23);
		contentPane.add(returnBtn);
		

		
		setVisible(true);
	}
}
