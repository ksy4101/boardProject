package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.ArticleDAO;
import dao.MemberDAO;
import vo.ArticleVO;
import vo.MemberVO;

public class UpdateArticleJPanel extends JPanel {
   private JTextField subTF, memTF, dateTF;

   private int artNo;
   private ArticleDAO dao = new ArticleDAO();
   private MemberDAO mdao = new MemberDAO();
   

   /**
    * Create the panel.
    */
   public UpdateArticleJPanel(MemberVO member, int artNo, int boardNo) {

      
      //this.artNo = artNo;
      Vector<Integer> artNoList = new Vector<Integer>();
      artNoList.addElement(artNo);
      
      setLayout(null);
      setBounds(499, 0, 1285, 962);

      try {
         ArticleVO article = dao.selectArticle(artNo);
         dateTF = new JTextField();
         dateTF.setEditable(false);
         dateTF.setBounds(1033, 10, 240, 59);
         dateTF.setText(article.getWriteDate());
         add(dateTF);
         dateTF.setColumns(10);

         subTF = new JTextField();
         subTF.setEditable(false);
         subTF.setText(article.getSubject());
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
         memTF.setText(article.getMemId()); // 작성자 아이디 받아와서 출력하기
         memTF.setEditable(false);

         JTextArea contTA = new JTextArea();
         contTA.setEditable(false);
         contTA.setBounds(12, 71, 1261, 851);
         contTA.setText(article.getContent());
         add(contTA);        

         JButton replyB = new JButton("댓글보기");
         replyB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	BoardFrame.rightPanel.add("reply", new ReplyPanel(member,artNo));
            	BoardFrame.card.show(BoardFrame.rightPanel, "reply");
            }
         });
         replyB.setBounds(931, 929, 97, 23);
         add(replyB);

         JButton updateB = new JButton("수정");
         updateB.setEnabled(false);

         updateB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // 수정버튼
               if(e.getActionCommand().equals("수정")){
                  subTF.setEditable(true);
                  contTA.setEditable(true);
                  updateB.setText("수정확인");
               } else if(e.getActionCommand().equals("수정확인")){
                  try {
                	  //제목, 내용이 스페이스일때 b,c
                	  String p = "((\\S|[a-zA-Z0-9가-힣ㄱ-ㅎ]+\\s){1,100})";
                      boolean b = Pattern.matches(p, subTF.getText());
                      boolean c = Pattern.matches(p, contTA.getText());
                      
                     if (!b) {
                          JOptionPane.showMessageDialog(subTF, "제목을 입력하세요");
                          return;
                       } else if (!c) {
                          JOptionPane.showMessageDialog(contTA, "내용을 입력하세요");
                          return;
                       } else {
	                        ArticleVO article = dao.selectArticle(artNo);
	                        
	                        article.setSubject(subTF.getText());
	                        article.setContent(contTA.getText());
	                        
	                        dao.updateArticle(article);
	                        
	                        JOptionPane.showMessageDialog(updateB, "수정되었습니다.");
	                        
	                        BoardFrame.rightPanel.add("article", new ArticlePanel(member,boardNo));
	                        BoardFrame.card.show(BoardFrame.rightPanel, "article");
                       }
                  } catch (Exception e2) {
                     e2.printStackTrace();
                  }
               }
            }
         });
         updateB.setBounds(1121, 929, 70, 23);
         add(updateB);
         
         JButton deleteB = new JButton("삭제");
         deleteB.setEnabled(false);
         
         if(member.getMemId().equals(article.getMemId())){
        	 updateB.setEnabled(true);
        	 deleteB.setEnabled(true);
         }
         deleteB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               //게시글 삭제 버튼
               Object t = e.getSource();
            try {
               if (t == deleteB) {
                  int result = JOptionPane.showConfirmDialog(deleteB, "정말 삭제하시겠습니까?");
                  if(result == JOptionPane.OK_OPTION){
                     dao.deleteArticle(artNoList);
                     JOptionPane.showMessageDialog(deleteB, "삭제되었습니다.");
                     
                     BoardFrame.rightPanel.add("article", new ArticlePanel(member,boardNo));
                          BoardFrame.card.show(BoardFrame.rightPanel, "article");
                  }
                  
               } else {
                  JOptionPane.showMessageDialog(deleteB, "취소되었습니다.");
                  BoardFrame.rightPanel.add("article", new ArticlePanel(member,boardNo));
                       BoardFrame.card.show(BoardFrame.rightPanel, "article");
               }

            } catch (Exception e2) {
               e2.printStackTrace();
            }
            }
         });
         deleteB.setBounds(1204, 929, 97, 23);
         add(deleteB);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

}