package frame;
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
	private InsertFrame insertFrame;
	private JPanel contentPane;
	private JTextField dongName;
	JComboBox comboBox = new JComboBox<String>();
	private Vector<PostVO> list = new Vector();
	ArrayList<Integer> postSeq = new ArrayList<Integer>();

	int index = 0;

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
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dong = dongName.getText();

				comboBox.removeAllItems();
				postSeq.clear();
				if (dong.equals("")) {
					JOptionPane.showMessageDialog(null, "동이름을 입력해주세요");
				} 
				else{
					comboBox.setEnabled(true);

					PostDAO dao = new PostDAO();
					

					try {
						list = dao.selectAddress(dong);
						String[] lists = new String[list.size()];
						for (int i = 0; i < list.size(); i++) {
							lists[i] = list.get(i).toString();
							postSeq.add(list.get(i).getPostNo());
							comboBox.addItem(lists[i].toString());
						}
				
						comboBox.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								//System.out.println(comboBox.getSelectedIndex());
								index = comboBox.getSelectedIndex();
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
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isEmpty()){
					JOptionPane.showMessageDialog(null, "동을 검색해 주세요");
				}
				else{
					if(type == 1){
						((InsertFrame) insertFrame).setPostSeq(list.get(index).getPostNo());
						((InsertFrame) insertFrame).setAddress1Field(list.get(index).toString());
						dispose();		
					}
					else if(type == 2){
						((UpdateFrame) insertFrame).setPostSeq(list.get(index).getPostNo());
						((UpdateFrame) insertFrame).setAddress1Field(list.get(index).toString());
						dispose();		
					}
				}
			}
		});
		insertBtn.setBounds(111, 202, 97, 23);
		contentPane.add(insertBtn);

		JButton disposeBtn = new JButton("\uCDE8\uC18C");
		disposeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		disposeBtn.setBounds(240, 202, 97, 23);
		contentPane.add(disposeBtn);

		setVisible(true);
	}
}
