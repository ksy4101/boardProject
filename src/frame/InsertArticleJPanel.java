package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.ArticleDAO;
import vo.ArticleVO;
import vo.MemberVO;


//게시글 등록 화면
public class InsertArticleJPanel extends JPanel {

	// 제목, 아이디, 작성일자 TextField변수선언
	private JTextField subTF, memTF, dateTF;


	public InsertArticleJPanel(MemberVO member, int boardNo) {
		String id = member.getMemId();
		setLayout(null);
		setBounds(499, 0, 1285, 962);
		
		dateTF = new JTextField();
		dateTF.setEditable(false);
		dateTF.setBounds(1033, 10, 240, 59);
		add(dateTF);
		dateTF.setColumns(10);

		// 날짜 받아와서 출력해주는 변수선언 . date .
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat d_format = new SimpleDateFormat("yyyy년 MM월 dd일 aa hh시 mm분");
		String date = d_format.format(cal.getTime());

		dateTF.setText(date); // 오늘 날짜 출력

		subTF = new JTextField();
		subTF.setBackground(Color.WHITE);
		subTF.setBounds(77, 10, 550, 59);
		add(subTF);
		subTF.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\uC791\uC131\uC77C\uC790");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 23));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(936, 10, 92, 51);
		add(lblNewLabel_1);

		JLabel label_1 = new JLabel("\uC791\uC131\uC790");
		label_1.setEnabled(false);
		label_1.setFont(new Font("굴림", Font.PLAIN, 23));
		label_1.setBounds(634, 10, 70, 51);
		add(label_1);

		JLabel label = new JLabel("\uC81C\uBAA9");
		label.setEnabled(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 23));
		label.setBounds(12, 10, 70, 59);
		add(label);

		memTF = new JTextField();
		memTF.setBounds(710, 10, 227, 59);
		add(memTF);
		memTF.setColumns(10);
		memTF.setText(id); // 작성자 아이디 받아와서 출력하기
		memTF.setEditable(false);

		JTextArea contTA = new JTextArea();
		contTA.setBounds(12, 71, 1261, 851);
		add(contTA);

		JButton insertB = new JButton("\uB4F1\uB85D");
		insertB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 등록버튼 클릭
				Object t = e.getSource();
				try {
					if (t == insertB) {
						String subject = subTF.getText(); // 제목 작성
						String content = contTA.getText(); // 내용 작성

						if (subTF.getText().length() == 0) {
							JOptionPane.showMessageDialog(subTF, "제목을 입력하세요.");
							return;
						} else if (contTA.getText().length() == 0) {
							JOptionPane.showMessageDialog(contTA, "내용을 입력하세요.");
							return;
						} else {
							ArticleDAO dao = new ArticleDAO();
							dao.insertArticle(new ArticleVO(boardNo, id, subject, content));

							JOptionPane.showMessageDialog(insertB, "등록되었습니다.");
							
							BoardFrame.rightPanel.add("article", new ArticlePanel(member,boardNo));
							BoardFrame.card.show(BoardFrame.rightPanel, "article");
						}
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		insertB.setBounds(1039, 929, 70, 23);
		add(insertB);
		
	}
}
