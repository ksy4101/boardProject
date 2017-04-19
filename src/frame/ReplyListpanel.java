package frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.ReplyDAO;

import vo.ReplyVO;

public class ReplyListpanel extends JPanel {

	
	
	private JTextArea replyContent;
	private JLabel replyMemId, replyDate;
	private JButton replyUpdate,replyDelete;
	
	
	/**
	 * Create the panel.
	 */
	public ReplyListpanel() {
		setBounds(0, 0, 1280, 730);
		setLayout(null);
        
	
		
		
				// 댓글내용 들어가는 Text
				replyContent = new JTextArea();
				replyContent.setText("댓글내용");
				replyContent.setBounds(12, 10, 700, 100);
				add(replyContent);

				
				// 아이디 들어가는 라벨
				replyMemId = new JLabel("아이디");
				replyMemId.setBounds(857, 14, 57, 15);
				add(replyMemId);
				
				// 등록날짜 들어가는 라벨
				replyDate = new JLabel("등록날짜");
				replyDate.setBounds(926, 14, 120, 15);
				add(replyDate);
				
				// 수정하는 버튼
				replyUpdate = new JButton("수정");
				replyUpdate.setBounds(848, 39,70, 20);
				add(replyUpdate);
				
				// 삭제하는 버튼
				replyDelete = new JButton("삭제");
				replyDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				replyDelete.setBounds(917, 39, 66, 23);
				add(replyDelete);
				
				
				
				
				
				
				
		
		
		
		
		
		
		
	}

	

}
