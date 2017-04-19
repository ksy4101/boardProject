package frame;
//회원 상세 조회 및 수정, 탈퇴 프레임

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

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
import dao.PostDAO;
import vo.MemberVO;

public class UpdateFrame extends JFrame {

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
	private JTextField address2Field;
	private JPasswordField jumin2Field;
	MemberDAO dao = new MemberDAO();
	MemberVO member = new MemberVO();
	PostDAO pdao = new PostDAO();
	private String firstPhone;
	private String lastEmail;

	public UpdateFrame(LoginFrame login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		//게시판 프레임에서 멤버의 정보를 가져오는 기능 추가
		
		member = login.getLogin();

		JPanel panel = new JPanel();
		setBounds(100, 100, 560, 590);
		panel.setBounds(0, 0, 549, 544);
		getContentPane().add(panel);
		panel.setLayout(null);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setText(member.getMemId());
		idField.setBounds(103, 44, 138, 21);
		panel.add(idField);
		idField.setColumns(10);

		password1Field = new JPasswordField();
		password1Field.setText(member.getPassword());
		password1Field.setEditable(false);
		password1Field.setBounds(103, 75, 138, 23);
		panel.add(password1Field);

		password2Field = new JPasswordField();
		password2Field.setText(member.getPassword());
		password2Field.setEditable(false);
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
		nameField.setEditable(false);
		nameField.setText(member.getName());
		nameField.setBounds(103, 172, 116, 21);
		panel.add(nameField);
		nameField.setColumns(10);

		JLabel label_7 = new JLabel("\uD55C\uAE00,\uC601\uBB38");
		label_7.setBounds(231, 171, 93, 23);
		panel.add(label_7);

		jumin1Field = new JTextField(6);
		jumin1Field.setText(member.getJumin().substring(0, 6));
		jumin1Field.setEditable(false);
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
		try {
			address1Field.setText(pdao.selectAddress(member.getPostNo()).toString());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		address1Field.setEnabled(false);
		address1Field.setColumns(10);
		address1Field.setBounds(103, 271, 255, 21);
		panel.add(address1Field);

		JLabel label_12 = new JLabel("(\uC0C1\uC138\uC8FC\uC18C)");
		label_12.setFont(new Font("굴림", Font.PLAIN, 11));
		label_12.setBounds(373, 304, 66, 23);
		panel.add(label_12);

		email1Field = new JTextField();
		email1Field.setText(member.getEmail().substring(0, member.getEmail().lastIndexOf("@")));
		email1Field.setColumns(10);
		email1Field.setBounds(103, 331, 87, 21);
		panel.add(email1Field);

		JLabel lblGbeovhsQjsgh = new JLabel("\uC5F0\uB77D\uCC98*");
		lblGbeovhsQjsgh.setFont(new Font("굴림", Font.PLAIN, 11));
		lblGbeovhsQjsgh.setBounds(22, 366, 66, 23);
		panel.add(lblGbeovhsQjsgh);

		phone2Field = new JTextField(4);
		phone2Field.setDocument(new textlimit(4));

		phone2Field.setText(
				member.getPhone().substring(member.getPhone().indexOf("-") + 1, member.getPhone().lastIndexOf("-")));

		phone2Field.setColumns(10);
		phone2Field.setBounds(179, 366, 50, 21);
		panel.add(phone2Field);

		phone3Field = new JTextField(4);
		phone3Field.setDocument(new textlimit(4));
		phone3Field.setText(member.getPhone().substring(member.getPhone().lastIndexOf("-") + 1));
		phone3Field.setColumns(10);
		phone3Field.setBounds(247, 366, 50, 21);
		panel.add(phone3Field);

		JLabel label_13 = new JLabel("\uBA54\uBAA8");
		label_13.setFont(new Font("굴림", Font.PLAIN, 11));
		label_13.setBounds(22, 408, 66, 23);
		panel.add(label_13);
		String[] ema = { "", "naver.com", "daum.net", "nate.com", "gmail.com"

		};
		JComboBox email2Field = new JComboBox(ema);
		for (int i = 0; i < ema.length; i++) {
			if (member.getEmail().substring(member.getEmail().lastIndexOf("@") + 1).equals(ema[i])) {
				email2Field.setSelectedIndex(i);
			}
		}

		email2Field.setBounds(216, 331, 129, 21);
		panel.add(email2Field);
		email2Field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = email2Field.getSelectedIndex();
				lastEmail = ema[index];
			}
		});

		String[] pho = { "", "010", "011", "016", "017", "019" };

		JComboBox phone1Field = new JComboBox(pho);
		for (int i = 0; i < pho.length; i++) {
			if (member.getPhone().substring(0, 3).equals(pho[i])) {
				phone1Field.setSelectedIndex(i);
			}
		}

		phone1Field.setBounds(103, 366, 66, 21);
		panel.add(phone1Field);
		phone1Field.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = phone1Field.getSelectedIndex();
				firstPhone = pho[index];
			}
		});

		JLabel lblNewLabel_1 = new JLabel("@");
		lblNewLabel_1.setBounds(195, 334, 25, 15);
		panel.add(lblNewLabel_1);

		address2Field = new JTextField();
		address2Field.setText(member.getAddress());
		address2Field.setColumns(10);
		address2Field.setBounds(103, 302, 255, 21);
		panel.add(address2Field);

		jumin2Field = new JPasswordField();
		jumin2Field.setText(member.getJumin().substring(6));
		jumin2Field.setEditable(false);
		jumin2Field.setBounds(220, 205, 87, 21);
		panel.add(jumin2Field);

		JButton button = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					dao.deleteMember(member.getMemId());
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("함초롬바탕", Font.PLAIN, 18));
		button.setBounds(373, 490, 116, 44);
		panel.add(button);

		JTextArea memoArea = new JTextArea();
		memoArea.setText(member.getMemo());
		memoArea.setBounds(103, 407, 197, 64);
		panel.add(memoArea);

		JButton updatePWBtn = new JButton("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
		updatePWBtn.setFont(new Font("굴림", Font.PLAIN, 11));
		updatePWBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdatePWFrame(UpdateFrame.this, 2);
				dispose();
			}
		});
		updatePWBtn.setBounds(243, 76, 115, 23);
		panel.add(updatePWBtn);

		JButton addressbutton = new JButton("검색");
		addressbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddressFrame(UpdateFrame.this, 2);
			}
		});
		addressbutton.setFont(new Font("굴림", Font.PLAIN, 11));
		addressbutton.setBounds(363, 271, 102, 23);
		panel.add(addressbutton);

		JButton Joinbutton = new JButton("\uBCC0\uACBD\uD558\uAE30");
		Joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					postSeq = pdao.selectAddress(member.getPostNo()).getPostNo();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String address = address2Field.getText();
				lastEmail = (String) email2Field.getSelectedItem();
				firstPhone = (String) phone1Field.getSelectedItem();
				String email = email1Field.getText() + "@" + lastEmail;
				String phone = firstPhone + "-" + phone2Field.getText() + "-" + phone3Field.getText();
				String memo = memoArea.getText();

				String phonePattern = "\\d{3}-\\d{3,4}-\\d{4}";
				String emailPattern = "\\w+@\\w+\\.\\w+(\\.\\w+)?";

				boolean phonechk = Pattern.matches(phonePattern, phone);
				boolean emailchk = Pattern.matches(emailPattern, email);

				if (!emailchk) {
					// 이메일을 입력하지 않았을 경우
					JOptionPane.showMessageDialog(null, "이메일을 올바르게 입력해 주세요");
					email = "";
				}

				if (!phonechk) {
					// 연락처의 가운데 자리가 3~4글자가 아니거나 마지막 자리가 4글자가 아닌경우
					JOptionPane.showMessageDialog(null, "휴대폰 번호를 올바르게 입력해 주세요");
					phone = "";
				}

				if (address.equals("") || email.equals("") || phone.equals("") || postSeq == 0) {
					JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요(메모 제외)");
				} else {
					member.setPostNo(postSeq);
					member.setPhone(phone);
					member.setEmail(email);
					member.setMemo(memo);
					member.setAddress(address);
					
					try {
						dao.updateMember(member);
						dispose();
						new LoginFrame();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
				dispose();
			}
		});
		cancelbutton.setFont(new Font("함초롬바탕", Font.PLAIN, 18));
		cancelbutton.setBounds(231, 490, 116, 44);
		panel.add(cancelbutton);

		setVisible(true);
	}

	public void setPostSeq(int seq) {
		this.postSeq = seq;
	}

	public void setAddress1Field(String address) {
		address1Field.setText(address);
	}

	public String getID() {
		return member.getMemId();
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
