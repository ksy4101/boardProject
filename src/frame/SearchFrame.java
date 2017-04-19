package frame;

//아이디/비밀번호 찾기 프레임

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import dao.MemberDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idNametxt;
	private JTextField idJumintxt1;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField pwNametxt;
	private JTextField pwJumintxt1;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField pwIDtxt;
	private JButton searchIDBtn;
	private JButton searchPWBtn;
	private JButton button;
	private JButton button_1;
	private String id;
	private JPasswordField pwJumintxt2;
	private JPasswordField idJumintxt2;

	public SearchFrame() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC774\uB984");
		lblNewLabel.setBounds(68, 44, 72, 15);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638");
		label.setBounds(68, 69, 72, 15);
		contentPane.add(label);

		idNametxt = new JTextField();
		idNametxt.setBounds(152, 41, 116, 21);
		contentPane.add(idNametxt);
		idNametxt.setColumns(10);

		idJumintxt1 = new JTextField(6);
		idJumintxt1.setDocument(new textlimit(6));// 최대 입력숫자를 6으로 제한하기 위한 메소드
		idJumintxt1.setBounds(152, 66, 72, 21);
		contentPane.add(idJumintxt1);
		idJumintxt1.setColumns(10);

		JLabel label_1 = new JLabel("-");
		label_1.setBounds(227, 69, 14, 15);
		contentPane.add(label_1);

		label_2 = new JLabel("\uC774\uB984");
		label_2.setBounds(68, 179, 72, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638");
		label_3.setBounds(68, 204, 72, 15);
		contentPane.add(label_3);

		pwNametxt = new JTextField();
		pwNametxt.setColumns(10);
		pwNametxt.setBounds(152, 176, 116, 21);
		contentPane.add(pwNametxt);

		pwJumintxt1 = new JTextField(6);
		pwJumintxt1.setDocument(new textlimit(6));// 최대 입력숫자를 6으로 제한하기 위한 메소드
		pwJumintxt1.setColumns(10);
		pwJumintxt1.setBounds(152, 201, 72, 21);
		contentPane.add(pwJumintxt1);

		label_4 = new JLabel("-");
		label_4.setBounds(227, 204, 14, 15);
		contentPane.add(label_4);

		label_5 = new JLabel("\uC544\uC774\uB514");
		label_5.setBounds(68, 151, 72, 15);
		contentPane.add(label_5);

		pwIDtxt = new JTextField();
		pwIDtxt.setColumns(10);
		pwIDtxt.setBounds(152, 148, 116, 21);
		contentPane.add(pwIDtxt);

		searchIDBtn = new JButton("ID \uCC3E\uAE30");
		// 아이디 찾기 버튼을 눌렀을 경우 로직
		searchIDBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 각 텍스트 필드의 값들을 변수에 저장
				String name = idNametxt.getText();
				String jumin = idJumintxt1.getText() + idJumintxt2.getText();

				MemberDAO dao = new MemberDAO();// DB 접근을 위한 클래스 선언
				String id = dao.selectID(name, jumin);// 아이디변수에 이름과 주민등록번호로 찾는
														// 메소드 선언해서 값을 넣음
				if (name.equals("") || jumin.equals("")) {
					JOptionPane.showMessageDialog(null, "정보를 입력하세요.");
				} else {
					if (id == null) {// 존재하는 아이디가 없는 경우
						JOptionPane.showMessageDialog(null, "ID가 존재하지 않습니다.");
					} else {// 존재하는 아이디가 있는 경우
						JOptionPane.showMessageDialog(null, id + " 입니다");
					}
				}
				// 현재 텍스트 필드를 초기화
				idNametxt.setText("");
				idJumintxt1.setText("");
				idJumintxt2.setText("");
			}
		});
		searchIDBtn.setBounds(152, 97, 97, 21);
		contentPane.add(searchIDBtn);

		searchPWBtn = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		// 패스워드 찾기 버튼을 눌렀을 경우 로직
		searchPWBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 각 텍스트 필드의 값을 변수에 저장
				id = pwIDtxt.getText();
				String name = pwNametxt.getText();
				String jumin = pwJumintxt1.getText() + pwJumintxt2.getText();

				MemberDAO dao = new MemberDAO();// DB 접근을 위한 클래스 선언
				if (id.equals("") || name.equals("") || jumin.equals("")) {
					JOptionPane.showMessageDialog(null, "정보를 입력해 주세요");
				} else {
					try {
						id = dao.checkData(id, name, jumin);// 아이디에 현재 아이디와 이름
															// 주민번호와 일치하는 데이터를
															// 반환
						if (id == null) {// 일치하는 데이터가 없는 경우
							JOptionPane.showMessageDialog(null, "데이터가 일치하지 않습니다.");
						} else {// 일치하는 데이터가 있는 경우
							new UpdatePWFrame(SearchFrame.this, 1);// 비밀번호 변경
																	// 프레임을 불러옴
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		searchPWBtn.setBounds(152, 231, 109, 21);
		contentPane.add(searchPWBtn);

		button = new JButton("\uCDE8\uC18C");
		// 취소하기 버튼을 눌렀을 경우 로직
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();// 로그인 프레임을 불러옴
				dispose();// 현재창 종료
			}
		});
		button.setBounds(273, 230, 109, 21);
		contentPane.add(button);

		button_1 = new JButton("\uCDE8\uC18C");
		// 취소하기 버튼을 눌렀을 경우 로직
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();// 로그인 프레임을 불러옴
				dispose();// 현재창 종료
			}
		});
		button_1.setBounds(261, 97, 97, 21);
		contentPane.add(button_1);

		pwJumintxt2 = new JPasswordField(7);
		pwJumintxt2.setDocument(new textlimit(7));// 최대 입력숫자를 6으로 제한하기 위한 메소드
		pwJumintxt2.setBounds(237, 201, 72, 21);
		contentPane.add(pwJumintxt2);

		idJumintxt2 = new JPasswordField(7);
		idJumintxt2.setDocument(new textlimit(7));// 최대 입력숫자를 6으로 제한하기 위한 메소드
		idJumintxt2.setBounds(236, 66, 72, 21);
		contentPane.add(idJumintxt2);
		setVisible(true);
	}

	public String getID() {// 비밀번호 변경 프레임에서 현재 입력한 아이디값을 가져오기 위한 메소드
		return this.id;
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
