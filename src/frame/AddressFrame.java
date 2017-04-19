package frame;
//주소 검색 프레임

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PostDAO;
import vo.PostVO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddressFrame extends JFrame {
	private JPanel contentPane;
	private JTextField dongName;
	JComboBox comboBox = new JComboBox<String>();
	private Vector<PostVO> list = new Vector();
	ArrayList<Integer> postSeq = new ArrayList<Integer>();

	int index = 0;
	
	//회원가입 프레임과 회원정보변경 프레임에서 둘다 사용하기 때문에
	//매개변수를 JFrame으로 받아 다양한 프레임에서 사용가능하게 만들었다.
	//타입이 1일때 회원가입 프레임쪽으로 다운캐스팅
	//타입이 2일경우 회원정보변경 프레임으로 다운캐스팅함.

	public AddressFrame(JFrame insertFrame, int type) { 
		setBounds(200, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uB3D9\uC774\uB984");
		lblNewLabel.setBounds(73, 81, 57, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"\uB3D9 \uC774\uB984\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694 (ex. \uC751\uC554, \uB2F9\uC0B0, \uB3D9\uC0BC)");
		lblNewLabel_1.setBounds(73, 56, 264, 15);
		contentPane.add(lblNewLabel_1);

		dongName = new JTextField();
		dongName.setBounds(142, 78, 116, 21);
		contentPane.add(dongName);
		dongName.setColumns(10);

		comboBox.setEnabled(false);
		comboBox.setBounds(73, 137, 294, 21);
		contentPane.add(comboBox);

		JButton searchBtn = new JButton("\uAC80\uC0C9");
		//검색버튼을 클릭했을때 로직
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dong = dongName.getText();//동 이름을 받는 텍스트 필드에서 값을 저장

				comboBox.removeAllItems();//현재 콤보박스를 초기화해줌
				postSeq.clear();//postDB의 PK인 주소인덱스를 받기위한 배열을 초기화해줌
				if (dong.equals("")) {//텍스트 필드에 값이 없는 경우
					JOptionPane.showMessageDialog(null, "동이름을 입력해주세요");
				} 
				else{
					comboBox.setEnabled(true);//아닌경우 콤보박스 활성화

					PostDAO dao = new PostDAO();//DB접근을 위한 메소드를 사용하기 위해 선언
					
					try {
						list = dao.selectAddress(dong);//주소 리스트를 받는 클래스 벡터에 동이름으로 주소를 검색해서 저장
						String[] lists = new String[list.size()];//주소를 저장하기 위한 클래스 배열 선언
						for (int i = 0; i < list.size(); i++) {//검색된 리스트의 크기만큼 반복
							lists[i] = list.get(i).toString();//현재 리스트의 내용을 배열에 저장
							postSeq.add(list.get(i).getPostNo());//현재 리스트의 주소인덱스값을 저장
							comboBox.addItem(lists[i].toString());//콤보박스에 배열에 추가한 내용을 저장함
						}
				
						comboBox.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								index = comboBox.getSelectedIndex();//현재 콤보박스에서 선택한 인덱스를 저장함.
							}
						});
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					
				}
			}
		});

		searchBtn.setBounds(270, 77, 97, 23);
		contentPane.add(searchBtn);



		JButton insertBtn = new JButton("\uD655\uC778");
		//확인을 눌렀을때 동작하는 로직
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isEmpty()){//검색한 리스트가 없는 경우
					JOptionPane.showMessageDialog(null, "동을 검색해 주세요");
				}
				else{//검색한 리스트가 있는 경우
					if(type == 1){//타입이 1일경우 회원가입 프레임에서 사용하기 위함이므로
						((InsertFrame) insertFrame).setPostSeq(list.get(index).getPostNo());//회원가입 프레임에 주소인덱스를 전달
						((InsertFrame) insertFrame).setAddress1Field(list.get(index).toString());//회원가입 프레임의 주소 출력부분 전달
						dispose();//정보 전달이후 현재창 종료
					}
					else if(type == 2){//2일경우 회원정보변경 프레임에서 사용함
						((UpdateFrame) insertFrame).setPostSeq(list.get(index).getPostNo());
						((UpdateFrame) insertFrame).setAddress1Field(list.get(index).toString());
						dispose();//정보 전달이후 현재창 종료
					}
				}
			}
		});
		insertBtn.setBounds(111, 202, 97, 23);
		contentPane.add(insertBtn);

		JButton disposeBtn = new JButton("\uCDE8\uC18C");
		//취소버튼을 눌렀을 경우 로직
		disposeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();//현재창 종료
			}
		});
		disposeBtn.setBounds(240, 202, 97, 23);
		contentPane.add(disposeBtn);

		setVisible(true);
	}
}
