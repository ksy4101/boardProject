package main;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ArticleDAO;

public class ArticlePanel extends JPanel {
	
	private JTextField SearchTF;
	private JTable ArticleTable;
	private ArticleDAO dao = new ArticleDAO();
	private DefaultTableModel dm;

	/**
	 * Create the panel.
	 */
	public ArticlePanel() {
		setBounds(0, 0, 1284, 962);
		setLayout(null);
		
		JComboBox SearchCB = new JComboBox();
		SearchCB.setFont(new Font("굴림", Font.PLAIN, 20));
		SearchCB.setModel(
				new DefaultComboBoxModel(new String[] { " \uC81C\uBAA9", " \uB0B4\uC6A9", "\uC791\uC131\uC790" }));
		SearchCB.setBackground(Color.WHITE);
		SearchCB.setBounds(96, 61, 89, 36);
		add(SearchCB);

		SearchTF = new JTextField();
		SearchTF.setBounds(184, 61, 528, 36);
		add(SearchTF);
		SearchTF.setColumns(10);

		JButton SearchBtn = new JButton("\uAC80\uC0C9");
		SearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SearchBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		SearchBtn.setBounds(713, 61, 89, 36);
		add(SearchBtn);

		JButton WriteArticleBtn = new JButton("\uAE00\uC4F0\uAE30");
		WriteArticleBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		WriteArticleBtn.setBounds(1086, 63, 97, 36);
		add(WriteArticleBtn);

		JButton DeleteArticleBtn = new JButton("\uC120\uD0DD\uC0AD\uC81C");
		DeleteArticleBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		DeleteArticleBtn.setBounds(1074, 127, 128, 36);
		add(DeleteArticleBtn);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(96, 190, 1106, 737);
		add(scrollPane_1);		
		
		String[] columnNames = new String[] { "\uBC88\uD638",
				"          \uC81C                                                   \uBAA9", "\uC791\uC131\uC77C\uC790",
				"\uC791\uC131\uC790", "비고" };
		dm = new DefaultTableModel(columnNames, 0);		

		try {
			Vector<Vector<Object>> rowData = dao.selectAllArticle(4);
			for (int i = 0 ; i < rowData.size() ; i++) {
				dm.addRow(rowData.elementAt(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArticleTable = new JTable(dm);
		ArticleTable.setRowHeight(30);
		
		/*ArticleTable.setModel(new DefaultTableModel(			
			new Object[][] {				
				//{null, null, null, null, null},	//데이터 추가하는 곳
			},
			new String[] {
				"\uBC88\uD638", "\uC81C\uBAA9", "\uC791\uC131\uC790", "\uC791\uC131\uC77C\uC790", "\uBE44\uACE0"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});		*/
		
		/*DefaultTableModel dm = new DefaultTableModel();
		
		ArticleTable.setModel(dm);
		
		try {
			Vector<Vector<Object>> rowData = dao.selectAllArticle(4);
			for (int i = 0 ; i < rowData.size() ; i++) {
				dm.addRow(rowData.elementAt(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		ArticleTable.getColumnModel().getColumn(0).setPreferredWidth(56);
		ArticleTable.getColumnModel().getColumn(1).setPreferredWidth(700);
		ArticleTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		ArticleTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		ArticleTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		ArticleTable.getColumn("비고").setCellEditor(new DefaultCellEditor(new JCheckBox()));
		ArticleTable.getColumn("비고").setCellRenderer(dcr);
		scrollPane_1.setViewportView(ArticleTable);

		/*table = new JTable();
		table.setRowHeight(30);
		
		String[] columnNames = new String[] { "\uBC88\uD638",
				"          \uC81C                                                   \uBAA9", "\uC791\uC131\uC77C\uC790",
				"\uC791\uC131\uC790", "비고" };
		dm = new DefaultTableModel(columnNames, 0);

		dm.addRow(new Object[] { null, null, null, null, false });
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\uBC88\uD638", "          \uC81C                                                   \uBAA9", "\uC791\uC131\uC77C\uC790", "\uC791\uC131\uC790", "\uBE44\uACE0"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(56);
		table.getColumnModel().getColumn(1).setPreferredWidth(700);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumn("비고").setCellEditor(new DefaultCellEditor(new JCheckBox()));
		table.getColumn("비고").setCellRenderer(dcr);
		scrollPane_1.setViewportView(table);*/
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
				int index = ArticleTable.getSelectedRow();
				JOptionPane.showMessageDialog(button, index + ": Ouch!");
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

}