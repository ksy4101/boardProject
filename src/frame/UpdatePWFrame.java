package frame;
//비밀번호 변경 프레임

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.MemberDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class UpdatePWFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField pw1Field;
	private JPasswordField pw2Field;

	public UpdatePWFrame(JFrame search, int type) {
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblNewLabel.setBounds(75, 116, 113, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\uBCC0\uACBD\uD560 \uBE44\uBC00\uBC88\uD638");
		label.setBounds(75, 45, 113, 15);
		contentPane.add(label);
		
		JButton updateBtn = new JButton("\uD655\uC778");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw1 = pw1Field.getText();
				String pw2 = pw2Field.getText();
				
				String pwPattern = "^(?=.*[a-z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$";
				boolean pwchk = Pattern.matches(pwPattern,pw1);
				if(!pwchk){
					JOptionPane.showMessageDialog(null, "비밀번호는 영문소문자,숫자,특수문자의 조합으로 8자리 이상 넣어주세요");
					pw1 = "";
					pw2 = "";
				}
				
				if(!(pw1.equals(pw2)) || pw1.equals("")){
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
				else{
					MemberDAO dao = new MemberDAO();
					if(type == 1){
						dao.updatePassword(((SearchFrame) search).getID(), pw1);
					}
					else if(type == 2){
						dao.updatePassword(((UpdateFrame) search).getID(), pw1);
					}
					JOptionPane.showMessageDialog(null, "변경이 완료되었습니다.");
					dispose();
				}

			}
		});
		updateBtn.setBounds(91, 186, 97, 23);
		contentPane.add(updateBtn);
		
		JButton returnBtn = new JButton("\uCDE8\uC18C");
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		returnBtn.setBounds(219, 186, 97, 23);
		contentPane.add(returnBtn);
		
		pw1Field = new JPasswordField();
		pw1Field.setBounds(200, 42, 116, 21);
		contentPane.add(pw1Field);
		
		pw2Field = new JPasswordField();
		pw2Field.setBounds(200, 113, 116, 21);
		contentPane.add(pw2Field);
		
		setVisible(true);
	}
}
