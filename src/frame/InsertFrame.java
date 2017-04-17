package frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import dao.MemberDAO;
import vo.MemberVO;

public class InsertFrame extends JFrame {
	
	private JTextField idField;
	private JPasswordField password1Field;
	private JPasswordField password2Field;
	private JTextField nameField;
	private JTextField jumin1Field;
	private JTextField address1Field;
	private JTextField email1Field;
	private JTextField phone2Field;
	private JTextField phone3Field;
	private int postSeq = 0;
	private String firstPhone;
	private String lastEmail;
	private JTextField address2Field;
	private JPasswordField jumin2Field;
	
	public InsertFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		setBounds(100, 100, 560, 590);
		panel.setBounds(0, 0, 549, 544);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		idField = new JTextField();
		idField.setEditable(false);
		idField.setText("\uC544\uC774\uB514\uB97C \uAC80\uC0C9\uD558\uC2DC\uC624");
		idField.setBounds(103, 44, 138, 21);
		panel.add(idField);
		idField.setColumns(10);
		
		password1Field = new JPasswordField();
		password1Field.setBounds(103, 75, 138, 23);
		panel.add(password1Field);
		
		password2Field = new JPasswordField();
		password2Field.setBounds(103, 108, 138, 23);
		panel.add(password2Field);
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uC544\uC774\uB514*");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel.setBounds(22, 44, 66, 23);
		panel.add(lblNewLabel);
		
		JLabel lblId = new JLabel("\uD68C\uC6D0 ID\uC815\uBCF4\uC785\uB825");
		lblId.setBackground(Color.BLUE);
		lblId.setForeground(Color.GRAY);
		lblId.setBounds(12, 13, 102, 23);
		panel.add(lblId);
		
		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638*");
		label.setFont(new Font("굴림", Font.PLAIN, 11));
		label.setBounds(22, 75, 66, 23);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778*");
		label_1.setFont(new Font("굴림", Font.PLAIN, 11));
		label_1.setBounds(22, 110, 80, 23);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\uC601\uBB38 \uB300\uC18C\uBB38\uC790 \uD63C\uC6A9 \uC22B\uC790 \uC0AC\uC6A9 \uB2E8 \uD2B9\uC218\uAE30\uD638 \uC0AC\uC6A9\uC548\uB428 ");
		label_2.setBounds(247, 75, 293, 23);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\uC911\uBCF5\uD655\uC778");
		label_3.setBounds(247, 108, 71, 23);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\uAE30\uBCF8\uC815\uBCF4 \uC785\uB825");
		label_4.setForeground(Color.GRAY);
		label_4.setBackground(Color.BLUE);
		label_4.setBounds(12, 139, 102, 23);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\uC774\uB984*");
		label_5.setFont(new Font("굴림", Font.PLAIN, 11));
		label_5.setBounds(22, 172, 66, 23);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638*");
		label_6.setFont(new Font("굴림", Font.PLAIN, 11));
		label_6.setBounds(22, 205, 80, 23);
		panel.add(label_6);
		
		nameField = new JTextField();
		nameField.setBounds(103, 172, 116, 21);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel label_7 = new JLabel("\uD55C\uAE00,\uC601\uBB38");
		label_7.setBounds(231, 171, 93, 23);
		panel.add(label_7);
		
		jumin1Field = new JTextField(6);
		jumin1Field.setDocument(new textlimit(6));
		jumin1Field.setBounds(103, 205, 87, 21);
		panel.add(jumin1Field);
		jumin1Field.setColumns(10);
		
		JLabel label_8 = new JLabel("-");
		label_8.setBounds(202, 208, 6, 15);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("\uC2E0\uC0C1\uC815\uBCF4\uC785\uB825*");
		label_9.setForeground(Color.GRAY);
		label_9.setBackground(Color.BLUE);
		label_9.setBounds(12, 238, 102, 23);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("\uC8FC\uC18C*");
		label_10.setFont(new Font("굴림", Font.PLAIN, 11));
		label_10.setBounds(22, 271, 66, 23);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("\uC774\uBA54\uC77C*");
		label_11.setFont(new Font("굴림", Font.PLAIN, 11));
		label_11.setBounds(22, 331, 66, 23);
		panel.add(label_11);
		
		address1Field = new JTextField();
		address1Field.setText("\uC8FC\uC18C\uB97C \uC785\uB825\uD558\uC2DC\uC624");
		address1Field.setEnabled(false);
		address1Field.setColumns(10);
		address1Field.setBounds(103, 271, 255, 21);
		panel.add(address1Field);
		
		JLabel label_12 = new JLabel("(\uC0C1\uC138\uC8FC\uC18C)");
		label_12.setFont(new Font("굴림", Font.PLAIN, 11));
		label_12.setBounds(373, 304, 66, 23);
		panel.add(label_12);
		
		email1Field = new JTextField();
		email1Field.setColumns(10);
		email1Field.setBounds(103, 331, 87, 21);
		panel.add(email1Field);
		
		JLabel lblGbeovhsQjsgh = new JLabel("\uC5F0\uB77D\uCC98*");
		lblGbeovhsQjsgh.setFont(new Font("굴림", Font.PLAIN, 11));
		lblGbeovhsQjsgh.setBounds(22, 366, 66, 23);
		panel.add(lblGbeovhsQjsgh);
		
		phone2Field = new JTextField(4);
		phone2Field.setDocument(new textlimit(4));
		phone2Field.setColumns(10);
		phone2Field.setBounds(179, 366, 50, 21);
		panel.add(phone2Field);
		
		phone3Field = new JTextField(4);
		phone3Field.setDocument(new textlimit(4));
		phone3Field.setColumns(10);
		phone3Field.setBounds(247, 366, 50, 21);
		panel.add(phone3Field);
		
		JLabel label_13 = new JLabel("\uBA54\uBAA8");
		label_13.setFont(new Font("굴림", Font.PLAIN, 11));
		label_13.setBounds(22, 408, 66, 23);
		panel.add(label_13);
		
		JLabel label_14 = new JLabel("\uC601\uBB38 \uC18C\uBB38\uC790 \uC22B\uC790 \uD63C\uC6A9\uAC00\uB2A5 5~14");
		label_14.setBounds(352, 42, 188, 23);
		panel.add(label_14);
		
		JTextArea memoArea = new JTextArea();
		memoArea.setBounds(103, 407, 197, 64);
		panel.add(memoArea);
		setVisible(true);
		
		JButton idbutton = new JButton("ID\uC911\uBCF5\uD655\uC778");
		idbutton.setFont(new Font("굴림", Font.PLAIN, 11));
		idbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("아이디를 입력하세요");
				if(id == null){
					id = "\uC544\uC774\uB514\uB97C \uAC80\uC0C9\uD558\uC2DC\uC624";
				}
				MemberDAO dao = new MemberDAO();
				String jid = dao.selectID(id);
				while(id.equals(jid)){
					id = JOptionPane.showInputDialog("중복 되었습니다 다시 입력해주세요");
				}
				idField.setText(id);
			}
		});
		idbutton.setBounds(247, 43, 102, 23);
		panel.add(idbutton);
		
		JButton addressbutton = new JButton("검색");
		addressbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddressFrame(InsertFrame.this, 1);
			}
		});
		addressbutton.setFont(new Font("굴림", Font.PLAIN, 11));
		addressbutton.setBounds(363, 271, 102, 23);
		panel.add(addressbutton);
		
		JButton Joinbutton = new JButton("가입하기");
		Joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String pw1 = password1Field.getText();
				String pw2 = password2Field.getText();
				String name = nameField.getText();
				String jumin = jumin1Field.getText() + jumin2Field.getText();
				String address = address2Field.getText();
				String email = email1Field.getText() + "@" + lastEmail;
				String phone = firstPhone + "-" + phone2Field.getText() + "-" + phone3Field.getText();
				String memo = memoArea.getText();
				
				if(jumin1Field.getText().length() != 6 || jumin2Field.getText().length() != 7){
					JOptionPane.showMessageDialog(null, "주민등록번호를 올바르게 입력해 주세요");
					jumin = "";
				}
				else{
					if(jumin1Field.getText().equals("") || jumin2Field.getText().equals("")){
						jumin = "";
					}
				}

				if(email1Field.getText().equals("") || lastEmail == null){
					JOptionPane.showMessageDialog(null, "이메일을 올바르게 입력해 주세요");
					email = "";
				}
				if(firstPhone == null || phone2Field.getText().length() < 3 || phone2Field.getText().length() > 4 || phone3Field.getText().length() !=4){
					JOptionPane.showMessageDialog(null, "휴대폰 번호를 올바르게 입력해 주세요");
					phone = "";
				}
				else{
					if(phone2Field.getText().equals("") || phone3Field.getText().equals("")){
						phone = "";
					}
				}

				

				
				if(id.equals("") || pw1.equals("") || pw2.equals("") || name.equals("") || jumin.equals("") || address.equals("") || email.equals("") || phone.equals("") || postSeq == 0){
					JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요(메모 제외)");
				}
				else{
					if(!(pw1.equals(pw2))){
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
					}
					else{
						MemberDAO dao = new MemberDAO();
						MemberVO member = new MemberVO(id, jumin, pw1, name, postSeq, phone, email, memo, address);
						try {
							dao.insertMemeber(member);
							dispose();
							new LoginFrame();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		Joinbutton.setFont(new Font("함초롬바탕", Font.PLAIN, 18));
		Joinbutton.setBounds(103, 490, 116, 44);
		panel.add(Joinbutton);
		
		JButton cancelbutton = new JButton("\uCDE8\uC18C\uD558\uAE30");
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		cancelbutton.setFont(new Font("함초롬바탕", Font.PLAIN, 18));
		cancelbutton.setBounds(242, 490, 116, 44);
		panel.add(cancelbutton);
		
		String [] ema =
			{
					"",
					"naver.com",
					"daum.net",
					"nate.com",
					"gmail.com"
					
			};
		JComboBox email2Field = new JComboBox(ema);
		email2Field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = email2Field.getSelectedIndex();
				lastEmail = ema[index];
			}
		});
		
		email2Field.setBounds(216, 331, 129, 21);
		panel.add(email2Field);
		
		String [] pho =
			{ 
					"",
					"010",
					"011",
					"016",
					"017",
					"019"
			};
		
		JComboBox phone1Field = new JComboBox(pho);
		phone1Field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = phone1Field.getSelectedIndex();
				firstPhone = pho[index];
			}
		});
		
		phone1Field.setBounds(103, 366, 66, 21);
		panel.add(phone1Field);
		
		JLabel lblNewLabel_1 = new JLabel("@");
		lblNewLabel_1.setBounds(195, 334, 25, 15);
		panel.add(lblNewLabel_1);
		
		address2Field = new JTextField();
		address2Field.setColumns(10);
		address2Field.setBounds(103, 302, 255, 21);
		panel.add(address2Field);
		
		jumin2Field = new JPasswordField();
		jumin2Field.setDocument(new textlimit(7));
		jumin2Field.setBounds(220, 205, 87, 21);
		panel.add(jumin2Field);
	}
	
	public void setPostSeq(int seq){
		this.postSeq = seq;
	}
	
	public void setAddress1Field(String address){
		address1Field.setText(address);
	}

	
	
	
	class textlimit extends PlainDocument {
		private int limit;

		public textlimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;
			if (getLength() + str.length() <= limit)
				super.insertString(offset, str, attr);
		}
	}
}


