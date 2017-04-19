package frame;

//회원가입 프레임

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
	private String id;
	private String firstPhone;
	private String lastEmail;
	private JTextField address2Field;
	private JPasswordField jumin2Field;

	public InsertFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x를 누르면 종료
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		setBounds(100, 100, 560, 590);
		panel.setBounds(0, 0, 549, 544);
		getContentPane().add(panel);
		panel.setLayout(null);

		idField = new JTextField();
		idField.setEnabled(false);
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

		JLabel label_2 = new JLabel(
				"\uC601\uBB38 \uB300\uC18C\uBB38\uC790 \uD63C\uC6A9 \uC22B\uC790 \uC0AC\uC6A9 \uB2E8 \uD2B9\uC218\uAE30\uD638 \uC0AC\uC6A9\uC548\uB428 ");
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
		jumin1Field.setDocument(new textlimit(6));// 최대 입력숫자를 6으로 제한하기 위한 메소드
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
		address1Field.setEnabled(false);// 주소는 주소입력 프레임쪽에서 입력받아 값을 전달하기 위해
										// 수정불가능하게 지정
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
		phone2Field.setDocument(new textlimit(4));// 휴대전화의 가운데 번호는 4이상 불가
		phone2Field.setColumns(10);
		phone2Field.setBounds(179, 366, 50, 21);
		panel.add(phone2Field);

		phone3Field = new JTextField(4);
		phone3Field.setDocument(new textlimit(4));// 4글자 이상 불가능
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
				new IDduplicationSearch(InsertFrame.this);
			}
		});
		idbutton.setBounds(247, 43, 102, 23);
		panel.add(idbutton);

		JButton addressbutton = new JButton("검색");
		addressbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddressFrame(InsertFrame.this, 1);// 회원가입 프레임에서 부르는 주소검색 프레임
			}
		});
		addressbutton.setFont(new Font("굴림", Font.PLAIN, 11));
		addressbutton.setBounds(363, 271, 102, 23);
		panel.add(addressbutton);

		JButton Joinbutton = new JButton("가입하기");
		// 회원가입 버튼을 눌렀을경우 로직
		Joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 각 텍스트필드에서 값을 가지고 와서 변수에 저장
				id = idField.getText();
				String pw1 = password1Field.getText();
				String pw2 = password2Field.getText();
				String name = nameField.getText();
				String jumin = jumin1Field.getText() + jumin2Field.getText();
				String address = address2Field.getText();
				String email = email1Field.getText() + "@" + lastEmail;
				String phone = firstPhone + "-" + phone2Field.getText() + "-" + phone3Field.getText();
				String memo = memoArea.getText();
				String juminPattern = "[0-9]{13}";
				String phonePattern = "\\d{3}-\\d{3,4}-\\d{4}";
				String emailPattern = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
				String namePattern = "[가-힣]{2,10}";
				String pwPattern = "^(?=.*[a-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$";

				boolean juminchk = Pattern.matches(juminPattern, jumin);
				boolean phonechk = Pattern.matches(phonePattern, phone);
				boolean emailchk = Pattern.matches(emailPattern, email);
				boolean namechk = Pattern.matches(namePattern, name);
				boolean pwchk = Pattern.matches(pwPattern,pw1);

				MemberDAO dao = new MemberDAO();
				if(!namechk){
					JOptionPane.showMessageDialog(null, "이름은 한글만 가능합니다.");
					name ="";
				}
				
				if (!juminchk) {
					JOptionPane.showMessageDialog(null, "주민등록번호를 올바르게 입력해주세요.");
					jumin = "";
				} else if (jumin.equals(dao.selectDuplication(jumin, 2))) {
					JOptionPane.showMessageDialog(null, "중복된 주민등록번호가 있습니다.");
					jumin = "";
				}
				
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
				if(!pwchk){
					JOptionPane.showMessageDialog(null, "비밀번호는 영문소문자,숫자,특수문자의 조합으로 8자리 이상 넣어주세요");
					pw1 = "";
					pw2 = "";
				}
				
				if (id.equals("") || pw1.equals("") || pw2.equals("") || name.equals("") || jumin.equals("")
						|| address.equals("") || email.equals("") || phone.equals("") || postSeq == 0) {
					// 각 텍스트 필드에 빈칸이 있는 경우
					JOptionPane.showMessageDialog(null, "빈칸을 모두 채워주세요(메모 제외)");
				} else {
					if (!(pw1.equals(pw2))) {
						// 패스워드와 패스워드 확인 필드가 일치하지 않는 경우
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
					} else {
						// 회원가입 로직
						MemberVO member = new MemberVO(id, jumin, pw1, name, postSeq, phone, email, memo, address);
						// 멤버VO 클래스의 객체 선언후 초기화
						try {
							dao.insertMemeber(member);// 멤버 객체를 디비에 입력
							dispose();// 현재창 종료
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
		// 취소 버튼을 눌렀을 경우 로직
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();// 현재창 종료
			}
		});
		cancelbutton.setFont(new Font("함초롬바탕", Font.PLAIN, 18));
		cancelbutton.setBounds(242, 490, 116, 44);
		panel.add(cancelbutton);

		// 이메일을 선택하는 콤보박스 구현부
		String[] ema = { "", "naver.com", "daum.net", "nate.com", "gmail.com"

		};
		JComboBox email2Field = new JComboBox(ema);// 이메일 콤보박스에 String배열을 입력
		email2Field.addActionListener(new ActionListener() {
			// 콤보박스를 클릭할 경우 동작하는 로직
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = email2Field.getSelectedIndex();// 현재 선택한 콤보박스의 인덱스를
															// 저장
				lastEmail = ema[index];// 이메일의 뒷자리 부분을 입력하는 변수에 저장
			}
		});

		email2Field.setBounds(216, 331, 129, 21);
		panel.add(email2Field);

		// 휴대폰 번호를 선택하는 콤보박스 구현부
		String[] pho = { "", "010", "011", "016", "017", "019" };

		JComboBox phone1Field = new JComboBox(pho);// 휴대폰 콤보박스에 String배열을 입력
		phone1Field.addActionListener(new ActionListener() {
			// 콤보박스를 클릭할 경우 동작하는 로직
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = phone1Field.getSelectedIndex();// 현재 선택한 콤보박스에 인덱스를
															// 저장
				firstPhone = pho[index];// 연락처 첫번째 부분을 입력하는 변수에 저장
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

	// 주소검색 프레임에서 전달해줄 값을 세팅하는 메소드
	public void setPostSeq(int seq) {// 현재 클래스의 주소입력부분을 담당하는 변수
		this.postSeq = seq;
	}

	public void setAddress1Field(String address) {// 현재 프레임의 주소텍스트 필드에 값을 넣어줄 변수
		address1Field.setText(address);
	}

	public void setID(String id) {
		this.id = id;
	}

	public void setIDField(String id) {
		idField.setText(id);
	}

	class textlimit extends PlainDocument {// 텍스트 필드의 자리수를 제한하기 위한 클래스 선언부
		private int limit;// 제한할 크기를 저장하는 변수

		public textlimit(int limit) {// 초기화 부분
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
