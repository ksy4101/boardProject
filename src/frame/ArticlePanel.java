package frame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import dao.ArticleDAO;
import vo.MemberVO;


public class ArticlePanel extends JPanel {

   private JTextField searchTF;
   private JTable articleTable;
   private ArticleDAO articleDAO = new ArticleDAO();
   private DefaultTableModel dm;
   private JCheckBox checkBox = new JCheckBox();
   private Vector<Vector<Object>> articleList;
   private Vector<Integer> list = new Vector<Integer>();
   private Vector<Integer> indexs = new Vector<Integer>();
   private JButton deleteArticleBtn;
   private int row;
   private String keyField;
   JScrollPane scrollPane_1 = new JScrollPane();
   private int boardNo;
   private MemberVO member;

   /**
    * Create the panel.
    */
   public ArticlePanel(MemberVO member,int boardNo) {
      this.boardNo = boardNo;
      this.member = member;
      setBounds(0, 0, 1284, 962);
      setLayout(null);

      // 검색 콤보박스
      JComboBox searchCB = new JComboBox();
      searchCB.setFont(new Font("굴림", Font.PLAIN, 20));
      String[] search = new String[] { "", "제목", "내용", "작성자" };
      searchCB.setModel(new DefaultComboBoxModel(search));
      searchCB.setBackground(Color.WHITE);
      searchCB.setBounds(96, 61, 89, 36);
      add(searchCB);
      // 검색 콤보박스 리스너
      searchCB.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            int index = searchCB.getSelectedIndex();
            keyField = search[index];
         }
      });

      // 검색 텍스트 필드
      searchTF = new JTextField();
      searchTF.setBounds(184, 61, 528, 36);
      add(searchTF);
      searchTF.setColumns(10);

      // 검색 버튼
      JButton searchBtn = new JButton("검색");
      searchBtn.setFont(new Font("굴림", Font.PLAIN, 20));
      searchBtn.setBounds(713, 61, 89, 36);
      add(searchBtn);
      // 검색 버튼 리스너
      searchBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (keyField == null || keyField == "") {
               JOptionPane.showMessageDialog(searchBtn, "검색 조건을 선택하세요.");
            } else if (searchTF.getText().isEmpty()) {
               JOptionPane.showMessageDialog(searchBtn, "검색하실 내용을 입력하세요.");
            } else {
               articleList.clear();

               for (int i = dm.getRowCount() - 1; i >= 0; i--) {
                  dm.removeRow(i);
               }
               try {
                  articleList = articleDAO.searchArticle(keyField, searchTF.getText(), boardNo);

                  for (int i = 0; i < articleList.size(); i++) {
                     Vector<Object> rowData = new Vector<Object>();
                     rowData.addElement(articleList.size() - i);
                     rowData.addElement(articleList.get(i).elementAt(1));
                     rowData.addElement(articleList.get(i).elementAt(2));
                     rowData.addElement(articleList.get(i).elementAt(3));
                     rowData.addElement(articleList.get(i).elementAt(4));
                     rowData.addElement("상세조회");
                     dm.addRow(rowData);
                  }

               } catch (Exception e2) {
                  e2.printStackTrace();
               }
            }
         }
      });

      // 글쓰기 버튼
      JButton writeArticleBtn = new JButton("\uAE00\uC4F0\uAE30");
      writeArticleBtn.setFont(new Font("굴림", Font.PLAIN, 20));      
      
      if (boardNo == 0){
               writeArticleBtn.setEnabled(false);
      }      
      
      writeArticleBtn.setBounds(1086, 63, 97, 36);
      add(writeArticleBtn);
      // 글쓰기 버튼 리스너
      writeArticleBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // 게시글 목록 화면에서 글쓰기 버튼 클릭 했을떄 글쓰는 창으로 화면전환
            Object t = e.getSource();
            if (t == writeArticleBtn) {
               BoardFrame.rightPanel.add("insertArticle", new InsertArticleJPanel(member, boardNo));
               BoardFrame.card.show(BoardFrame.rightPanel, "insertArticle");
            }
         }
      });

      // 선택삭제 버튼
      deleteArticleBtn = new JButton("\uC120\uD0DD\uC0AD\uC81C");
      deleteArticleBtn.setFont(new Font("굴림", Font.PLAIN, 20));
      
      if(boardNo == 0){
          deleteArticleBtn.setVisible(false);
       } 
      
      deleteArticleBtn.setBounds(1074, 127, 128, 36);
      add(deleteArticleBtn);
      if(member.getGrade().equals("0")){
         deleteArticleBtn.setEnabled(false);
      }
      else if(member.getGrade().equals("1")){
         deleteArticleBtn.setEnabled(true);
      }
      // 선택삭제 버튼 리스너
      deleteArticleBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (list.size() == 0) {
               JOptionPane.showMessageDialog(deleteArticleBtn, "삭제하실 게시 글을 선택하세요.");
               return;
            } else {
               int result = JOptionPane.showConfirmDialog(deleteArticleBtn, "삭제하시겠습니까?");

               if (result == JOptionPane.OK_OPTION) {

                  try {
                     articleDAO.deleteArticle(list);
                     
                     System.out.println(indexs.size());
                     
                     for (int i = indexs.size() - 1; i >= 0; i--) {
                        dm.removeRow(indexs.elementAt(i));
                     }
                  } catch (Exception e2) {
                     e2.printStackTrace();
                  }

               }
            }

         }

      });

      // JScrollPane scrollPane_1 = new JScrollPane();
      scrollPane_1.setBounds(96, 190, 1106, 737);
      add(scrollPane_1);

      // 게시 글 목록 테이블 컬럼명
      String[] columnNames = new String[] { "번호", "제목", "작성자", "작성일자", "비고", "remark" };

      // 게시 글 목록 테이블 모델 설정
      dm = new DefaultTableModel(columnNames, 0) {
         @Override
         public boolean isCellEditable(int row, int column) {
            if (column == 4 || column == 5) {
               return true;
            }
            return false;
         }
      };

      try {
         articleList = articleDAO.selectAllArticle(boardNo);
         for (int i = 0; i < articleList.size(); i++) {
            Vector<Object> rowData = new Vector<Object>();
            rowData.addElement(articleList.size() - i);
            rowData.addElement(articleList.get(i).elementAt(1));
            rowData.addElement(articleList.get(i).elementAt(2));
            rowData.addElement(articleList.get(i).elementAt(3));            
            rowData.addElement(false);
            rowData.addElement("상세조회");
            dm.addRow(rowData);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      // 설정한 모델로 테이블 생성
      articleTable = new JTable(dm);
      articleTable.setRowHeight(30);
      
      articleTable.getColumnModel().getColumn(0).setPreferredWidth(56);
      articleTable.getColumnModel().getColumn(1).setPreferredWidth(600);
      articleTable.getColumnModel().getColumn(2).setPreferredWidth(150);
      articleTable.getColumnModel().getColumn(3).setPreferredWidth(150);
      articleTable.getColumnModel().getColumn(4).setPreferredWidth(50);
      articleTable.getColumnModel().getColumn(5).setPreferredWidth(100);
      
      articleTable.getColumn("remark").setCellRenderer(new ButtonRenderer());
      articleTable.getColumn("remark").setCellEditor(new ButtonEditor(new JCheckBox()));      
      articleTable.getColumn("비고").setCellEditor(new DefaultCellEditor(checkBox));
      articleTable.getColumn("비고").setCellRenderer(dcr);
   
   
      scrollPane_1.setViewportView(articleTable);
      

      // 테이블에 있는 체크박스 리스너
      checkBox.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
         
            try {
               row = articleTable.getSelectedRow();
               int articleNo = (Integer) articleList.elementAt(row).elementAt(0);

               if (checkBox.isSelected()) {
                  list.addElement(articleNo);
                  indexs.addElement(row);
               } else {
                  list.removeElement(articleNo);
                  indexs.removeElement(row);
               }

            } catch (Exception e2) {
               e2.printStackTrace();
            }

         }
      });

   }
   

   class ButtonEditor extends DefaultCellEditor {
      protected JButton button;
      private String label;
      private boolean isPushed;

      public ButtonEditor(JCheckBox checkBox) {      
         super(checkBox);
         button = new JButton();
         button.setOpaque(true);
         button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            
               fireEditingStopped();               
            }
         });
      }

      public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
            int column) {
         if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
         } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
         }
         label = (value == null) ? "" : value.toString();
         button.setText(label);
         isPushed = true;
         return button;
      }
      
      public Object getCellEditorValue() {
         if (isPushed) {
            
            int row = articleTable.getSelectedRow();
               int articleNo = (Integer) articleList.get(row).elementAt(0);

               BoardFrame.rightPanel.add("updateArticle", new UpdateArticleJPanel(member, articleNo, boardNo));
               BoardFrame.card.show(BoardFrame.rightPanel, "updateArticle");
            
         }
         isPushed = false;
         return new String(label);
      }
      
   

      public boolean stopCellEditing() {
         isPushed = false;
         return super.stopCellEditing();
      }

      protected void fireEditingStopped() {
         super.fireEditingStopped();
      }
   }

   
   class ButtonRenderer extends JButton implements TableCellRenderer {
      public ButtonRenderer() {
         setOpaque(true);
      }

      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
         if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
         } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
         }
         setText((value == null) ? "" : value.toString());
         return this;
      }
   }

   
   DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
         JCheckBox box = new JCheckBox();
         box.setSelected(((Boolean) value).booleanValue());
         box.setHorizontalAlignment(JLabel.CENTER);
         return box;
      }
   };

}