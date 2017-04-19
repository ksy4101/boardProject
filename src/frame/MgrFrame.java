package frame;

//관리자 프레임

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.MemberDAO;
import dao.PostDAO;
import vo.MemberVO;

import javax.swing.JComboBox;

public class MgrFrame extends JFrame {
	private static final TableModel DefaultTableModel = null;
	private JTextField idField;
	private JPasswordField password1Field;
	private JPasswordField password2Field;
	private JTextField nameField;
	private JTextField jumin1Field;
	private JTextField address1Field;
	private JTextField email1Field;
	private JTextField phone2Field;
	private JTextField phone3Field;
	private JTextField searchField;
	private JTable table;
	private JTextField address2Field;
	private JPasswordField jumin2Field;
	private DefaultTableModel dm;
	private MemberVO member = new MemberVO();
	private PostDAO pdao = new PostDAO();
	private String nowgrade;
	private int row;
	private Vector<Vector<Object>> rowData = null;
	private String key;
	MemberDAO dao = new MemberDAO();// DB접근을 위한 클래스 선언

	public MgrFrame() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x버튼을 누를경우 프로그램 종료

		String[] columnNames = { "아이디", "이름", "연락처", "이메일" };// 테이블의 컬럼 네임을 지정해줄
																// String 배열
		dm = new DefaultTableModel(columnNames, 0);// 컬럼네임을 넣어준 테이블 모델
		table = new JTable(dm); // Jtable을 현재 설정한 디폴트 테이블 모델으로 선언

		try {
			rowData = dao.selectAllMember();// 2차원 벡터에 회원 전체 리스트를 넣어줌
			for (int i = 0; i < rowData.size(); i++) {// 리스트의 숫자만큼 반복
				dm.addRow(rowData.elementAt(i));// 테이블 모델에 리스트를 저장
	}																																																																																																																																																																																																																																																																																																																																																																																																																															
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("\uD68C\uC6D0\uAD00\uB9AC");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();

		setBounds(100, 100, 1300, 590);
		panel.setBounds(741, 0, 543, 552);
		getContentPane().add(panel);
		panel.setLayout(null);

		idField = new JTextField();
		idField.setEditable(false);
		idField.setBounds(160, 41, 138, 21);
		panel.add(idField);
		idField.setColumns(10);

		password1Field = new JPasswordField();
		password1Field.setEnabled(false);
		password1Field.setEditable(false);
		password1Field.setBounds(160, 72, 138, 23);
		panel.add(password1Field);

		password2Field = new JPasswordField();
		password2Field.setEnabled(false);
		password2Field.setEditable(false);
		password2Field.setBounds(160, 106, 138, 23);
		panel.add(password2Field);

		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uC544\uC774\uB514*");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel.setBounds(79, 41, 66, 23);
		panel.add(lblNewLabel);

		JLabel lblId = new JLabel("\uD68C\uC6D0 ID\uC815\uBCF4\uC785\uB825");
		lblId.setBackground(Color.BLUE);
		lblId.setForeground(Color.GRAY);
		lblId.setBounds(69, 10, 102, 23);
		panel.add(lblId);

		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638*");
		label.setFont(new Font("굴림", Font.PLAIN, 11));
		label.setBounds(79, 72, 66, 23);
		panel.add(label);

		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778*");
		label_1.setFont(new Font("굴림", Font.PLAIN, 11));
		label_1.setBounds(79, 107, 80, 23);
		panel.add(label_1);

		JLabel label_4 = new JLabel("\uAE30\uBCF8\uC815\uBCF4 \uC785\uB825");
		label_4.setForeground(Color.GRAY);
		label_4.setBackground(Color.BLUE);
		label_4.setBounds(69, 136, 102, 23);
		panel.add(label_4);

		JLabel label_5 = new JLabel("\uC774\uB984*");
		label_5.setFont(new Font("굴림", Font.PLAIN, 11));
		label_5.setBounds(79, 169, 66, 23);
		panel.add(label_5);

		JLabel label_6 = new JLabel("\uC8FC\uBBFC\uB4F1\uB85D\uBC88\uD638*");
		label_6.setFont(new Font("굴림", Font.PLAIN, 11));
		label_6.setBounds(79, 202, 80, 23);
		panel.add(label_6);

		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setBounds(160, 169, 116, 21);
		panel.add(nameField);
		nameField.setColumns(10);

		JLabel label_7 = new JLabel("\uD55C\uAE00,\uC601\uBB38");
		label_7.setBounds(288, 168, 93, 23);
		panel.add(label_7);

		jumin1Field = new JTextField();
		jumin1Field.setEnabled(false);
		jumin1Field.setEditable(false);
		jumin1Field.setBounds(160, 202, 87, 21);
		panel.add(jumin1Field);
		jumin1Field.setColumns(10);

		JLabel label_8 = new JLabel("-");
		label_8.setBounds(259, 205, 6, 15);
		panel.add(label_8);

		JLabel label_9 = new JLabel("\uC2E0\uC0C1\uC815\uBCF4\uC785\uB825*");
		label_9.setForeground(Color.GRAY);
		label_9.setBackground(Color.BLUE);
		label_9.setBounds(69, 235, 102, 23);
		panel.add(label_9);

		JLabel label_10 = new JLabel("\uC8FC\uC18C*");
		label_10.setFont(new Font("굴림", Font.PLAIN, 11));
		label_10.setBounds(79, 268, 66, 23);
		panel.add(label_10);

		JLabel label_11 = new JLabel("\uC774\uBA54\uC77C*");
		label_11.setFont(new Font("굴림", Font.PLAIN, 11));
		label_11.setBounds(79, 328, 66, 23);
		panel.add(label_11);

		address1Field = new JTextField();
		address1Field.setEditable(false);
		address1Field.setColumns(10);
		address1Field.setBounds(160, 268, 255, 21);
		panel.add(address1Field);

		JLabel label_12 = new JLabel("(\uC0C1\uC138\uC8FC\uC18C)");
		label_12.setFont(new Font("굴림", Font.PLAIN, 11));
		label_12.setBounds(310, 299, 66, 23);
		panel.add(label_12);

		email1Field = new JTextField();
		email1Field.setEditable(false);
		email1Field.setColumns(10);
		email1Field.setBounds(160, 328, 87, 21);
		panel.add(email1Field);

		JTextArea memoArea = new JTextArea();

		memoArea.setBounds(160, 404, 197, 64);
		panel.add(memoArea);

		JLabel lblGbeovhsQjsgh = new JLabel("\uC5F0\uB77D\uCC98*");
		lblGbeovhsQjsgh.setFont(new Font("굴림", Font.PLAIN, 11));
		lblGbeovhsQjsgh.setBounds(79, 363, 66, 23);
		panel.add(lblGbeovhsQjsgh);

		phone2Field = new JTextField();
		phone2Field.setEditable(false);
		phone2Field.setColumns(10);
		phone2Field.setBounds(236, 363, 50, 21);
		panel.add(phone2Field);

		phone3Field = new JTextField();
		phone3Field.setEditable(false);
		phone3Field.setColumns(10);
		phone3Field.setBounds(304, 363, 50, 21);
		panel.add(phone3Field);

		JLabel label_13 = new JLabel("\uBA54\uBAA8");
		label_13.setFont(new Font("굴림", Font.PLAIN, 11));
		label_13.setBounds(79, 405, 66, 23);
		panel.add(label_13);

		// 회원의 등급을 관리할 콤보박스
		String[] deungeop = { "일반회원", "관리자" };
		JComboBox deungeopBox = new JComboBox(deungeop);

		deungeopBox.setBounds(160, 475, 117, 21);
		panel.add(deungeopBox);
		deungeopBox.addActionListener(new ActionListener() {
			// 콤보박스를 클릭했을 경우 로직
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = deungeopBox.getSelectedIndex();// 현재 선택한 콤보박스의 인덱스를
															// 저장
				nowgrade = deungeop[index];// 회원등급 변수에 저장
			}
		});

		JLabel label_2 = new JLabel("\uB4F1\uC5C5");
		label_2.setFont(new Font("굴림", Font.PLAIN, 11));
		label_2.setBounds(79, 473, 66, 23);
		panel.add(label_2);

		String[] email = { "", "naver.com", "gmail.com", "daum.net", "nate.com" };
		JComboBox email2Field = new JComboBox(email);
		email2Field.setEnabled(false);
		email2Field.setBounds(259, 328, 117, 21);
		panel.add(email2Field);

		String[] phone = { "", "010", "011", "016", "017", "019" };
		JComboBox phone1Field = new JComboBox(phone);
		phone1Field.setEnabled(false);
		phone1Field.setBounds(160, 363, 66, 21);
		panel.add(phone1Field);

		address2Field = new JTextField();
		address2Field.setEditable(false);
		address2Field.setColumns(10);
		address2Field.setBounds(160, 299, 116, 21);
		panel.add(address2Field);

		jumin2Field = new JPasswordField();
		jumin2Field.setEnabled(false);
		jumin2Field.setEditable(false);
		jumin2Field.setBounds(277, 201, 99, 23);
		panel.add(jumin2Field);

		JButton idbutton = new JButton("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
		idbutton.setEnabled(false);
		idbutton.setFont(new Font("굴림", Font.PLAIN, 11));
		idbutton.setBounds(310, 73, 102, 23);
		panel.add(idbutton);

		JButton addressbutton = new JButton("검색");
		addressbutton.setEnabled(false);
		addressbutton.setFont(new Font("굴림", Font.PLAIN, 11));
		addressbutton.setBounds(420, 268, 102, 23);
		panel.add(addressbutton);

		JButton Joinbutton = new JButton("\uC815\uBCF4\uC218\uC815");
		// 회원 등급 변경 버튼을 눌렀을 경우 로직
		Joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 관리자 등급 관리
				// 각 텍스트 필드에 뿌려진 값들을 변수에 저장
				String grade = nowgrade;
				// 현재 회원의 등급을 DB에 맞게 변환
				if (grade.equals("일반회원")) {
					grade = "0";
				} else if (grade.equals("관리자")) {
					grade = "1";
				}
				// 멤버 변수에 저장
				member.setGrade(grade);
				try {
					dao.updateMember(member);// 회원 정보 변경 메소드 실행
					JOptionPane.showMessageDialog(null, "등급 변경 완료");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		Joinbutton.setFont(new Font("함초롬바탕", Font.PLAIN, 18));
		Joinbutton.setBounds(104, 513, 116, 29);
		panel.add(Joinbutton);

		JButton cancelbutton = new JButton("\uD68C\uC6D0\uC0AD\uC81C");
		// 회원 삭제 버튼을 눌렀을 경우 로직
		cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원 삭제
				member = dao.selectMember(idField.getText());
				idField.setText("");
				password1Field.setText("");
				password2Field.setText("");
				nameField.setText("");
				jumin1Field.setText("");
				jumin2Field.setText("");
				address1Field.setText("");
				address2Field.setText("");
				email1Field.setText("");
				email2Field.setSelectedIndex(0);
				phone1Field.setSelectedIndex(0);
				phone2Field.setText("");
				phone3Field.setText("");
				memoArea.setText("");
				deungeopBox.setSelectedIndex(0);
				try {
					dao.deleteMember(member.getMemId());//삭제 메소드
					//새로고침
					rowData.clear();// 2차원 벡터 초기화
					for (int i = dm.getRowCount() - 1; i >= 0; i--) {
						dm.removeRow(i);// 입력받을 디폴트테이블모델 초기화
					}
					rowData = dao.selectAllMember();// 2차원 벡터에 회원 전체 리스트를 넣어줌
					for (int i = 0; i < rowData.size(); i++) {// 리스트의 숫자만큼 반복
						dm.addRow(rowData.elementAt(i));// 테이블 모델에 리스트를 저장
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cancelbutton.setFont(new Font("함초롬바탕", Font.PLAIN, 18));
		cancelbutton.setBounds(244, 513, 116, 29);
		panel.add(cancelbutton);

		JPanel panel_1 = new JPanel();

		panel_1.setBounds(0, 0, 739, 552);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 711, 433);
		panel_1.add(scrollPane);

		scrollPane.setViewportView(table);// 스크롤 형식의 테이블을 구현

		String[] search = { " ", "ID", "이름" };// 회원 검색을 위한 콤보박스 구현부
		JComboBox searchBox = new JComboBox(search);// String 배열에 있는 정보를 콤보박스에
													// 저장
		searchBox.addActionListener(new ActionListener() {
			// 콤보박스를 눌렀을때 동작하는 로직
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = searchBox.getSelectedIndex();// 현재 선택한 콤보박스의 인덱스를 반환
				key = search[index];// key 변수에 현재 선택한 콤보박스의 내용을 저장
			}
		});
		searchBox.setBounds(72, 471, 128, 32);
		panel_1.add(searchBox);

		searchField = new JTextField();
		searchField.setBounds(236, 472, 222, 32);
		panel_1.add(searchField);
		searchField.setColumns(10);

		MouseAdapter listener = new MouseAdapter() {
			// 마우스 동작을 구현할 클래스 선언
			@Override
			public void mouseClicked(MouseEvent e) {// 마우스 클릭을 할 경우 동작하는 로직
				row = table.getSelectedRow();// row에 현재 클릭한 테이블의 인덱스를 반환
				String id = (String) dm.getValueAt(row, 0);// 선택한 테이블의 아이디값 반환
				member = dao.selectMember(id);// 멤버 변수에 현재 선택한 아이디의 정보를 저장

				// 각 텍스트 필드에 멤버변수 안의 값들을 뿌려줌
				idField.setText(member.getMemId());
				password1Field.setText(member.getPassword());
				password2Field.setText(member.getPassword());
				nameField.setText(member.getName());
				jumin1Field.setText(member.getJumin().substring(0, 6));

				try {
					address1Field.setText(pdao.selectAddress(member.getPostNo()).toString());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				email1Field.setText(member.getEmail().substring(0, member.getEmail().lastIndexOf("@")));
				memoArea.setText(member.getMemo());
				phone2Field.setText(member.getPhone().substring(member.getPhone().indexOf("-") + 1,
						member.getPhone().lastIndexOf("-")));
				phone3Field.setText(member.getPhone().substring(member.getPhone().lastIndexOf("-") + 1));

				if (member.getGrade().equals("0")) {
					deungeopBox.setSelectedIndex(0);
				} else if (member.getGrade().equals("1")) {
					deungeopBox.setSelectedIndex(1);
				}

				for (int i = 0; i < email.length; i++) {
					if (member.getEmail().substring(member.getEmail().lastIndexOf("@") + 1).equals(email[i])) {
						email2Field.setSelectedIndex(i);
					}
				}

				for (int i = 0; i < phone.length; i++) {
					if (member.getPhone().substring(0, 3).equals(phone[i])) {
						phone1Field.setSelectedIndex(i);
					}
				}

				address2Field.setText(member.getAddress());
				jumin2Field.setText(member.getJumin().substring(6));
			}

		};

		table.addMouseListener(listener);// 구현한 마우스 동작을 테이블쪽에 넣어줌

		JButton searchButton = new JButton("\uAC80\uC0C9");
		// 회원 검색 버튼을 눌렀을 경우 로직
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 초기화
				rowData.clear();// 2차원 벡터 초기화
				for (int i = dm.getRowCount() - 1; i >= 0; i--) {
					dm.removeRow(i);// 입력받을 디폴트테이블모델 초기화
				}

				try {
					if (key == null || key.equals("")) {// 콤보박스 선택을 안했을 경우
						JOptionPane.showMessageDialog(null, "검색종류를 선택하세요.");
					} else if (searchField.getText().isEmpty()) {// 검색어가 없는 경우
						rowData = dao.selectAllMember();
						for (int i = 0; i < rowData.size(); i++) {// 리스트의 숫자만큼
																	// 반복
							dm.addRow(rowData.elementAt(i));// 테이블 모델에 검색한 리스트를
															// 저장
						}
					} else {
						rowData = dao.selectMember(key, searchField.getText());// 2차원
																				// 벡터에
																				// 회원
																				// 전체
																				// 리스트를
																				// 넣어줌

						if (rowData.isEmpty()) {// 2차원 벡터에 값이 없는 경우
							JOptionPane.showMessageDialog(null, "해당하는 회원이 없습니다.");
						} else {
							for (int i = 0; i < rowData.size(); i++) {// 리스트의
																		// 숫자만큼
																		// 반복
								dm.addRow(rowData.elementAt(i));// 테이블 모델에 검색한
																// 리스트를 저장
							}
						}

					}
				} catch (Exception e2) {
					e2.printStackTrace();

				}
			}
		});

		searchButton.setBounds(498, 471, 117, 32);
		panel_1.add(searchButton);

		setVisible(true);
	}

}
