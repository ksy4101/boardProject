package frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import dao.ReplyDAO;
import vo.MemberVO;
import vo.ReplyVO;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;

public class ReplyPanel extends JPanel {

	private static final LayoutManager GridBagLayout = null;
	private int artNo;
	private int reNo;
	private int x = 0;
	private int y = 0;
	private String p;
	private ReplyListpanel replyListpanel;
	private JTextArea DBreplytext, insertReplyText;
	private JLabel insertReplyId, insertReplyDate, replyInsert;
	private JButton updateReplyBtn, deleteReplybtn, insertBtn, selectMyReplyBtn, allReplyBtn;
	private JPanel replyListPanel, replyPanel, replyinsert;
	private JButton allreplyDeletebutton, myinsertBtn;
	private JScrollPane pane;
	private MemberVO member;
	private int height = 730;

	ReplyTextLimit limit = new ReplyTextLimit(99);
	// 클래스에서 가져온다. 범위는 (99) 이유: 한글은 1개가 3바이트이므로 re_content가 300이여야 100지를 쓸수있으므로
	// 99로 했다. 100은 303까지 이용한


	// 내 댓글조회
	private void MyRelyList() {
		try {
			ReplyDAO dao = new ReplyDAO();
			List<ReplyVO> replys = dao.selectMyReply(artNo, member.getMemId()); // memid,artNo로
																	// 댓글들 조회
			for (int i = 0; i < replys.size(); i++) { // for문으로 댓글들 갯수만큼 증가시킴
				ReplyVO reply = replys.get(i); // dao에서 값을불러와 reply에 저장

				replyPanel = new JPanel();
				replyPanel.setLayout(null);

				replyPanel.setBounds(0, y, 1280, 120); // y값을 변경을줘서 댓글을 for문이
														// 끝날때가지 실행

				JTextArea DBreplytext = new JTextArea();
				DBreplytext.setBounds(12, 10, 700, 100);
				DBreplytext.setLineWrap(true); // 자동 택스트 넘김
				DBreplytext.setFont(new Font("굴림", Font.PLAIN, 16));
				DBreplytext.setText(reply.getReContent()); // db 저장된 값을 가지온다.
				replyPanel.add(DBreplytext); // 패널에 값을 넣은다.

				insertReplyId = new JLabel(reply.getMemId()); // db 저장된 값을 가지온다.
				insertReplyId.setBounds(857, 14, 57, 15);
				replyPanel.add(insertReplyId); // 패널에 값을 넣은다.

				insertReplyDate = new JLabel(reply.getReDate()); // db 저장된 값을
				// 가지온다.
				insertReplyDate.setBounds(926, 14, 120, 15);
				replyPanel.add(insertReplyDate); // 패널에 값을 넣은다.

				updateReplyBtn = new JButton("수정"); // 수정버튼
				updateReplyBtn.setBounds(844, 39, 70, 20);

				updateReplyBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							int result = JOptionPane.showConfirmDialog(ReplyPanel.this, "수정하시겠습니까?","수정", 2); // 수정선택
							if (result == JOptionPane.OK_OPTION) {// ok루느면 수정
								String reContent = DBreplytext.getText(); // db에
																			// 있는
																			// 내용을
																			// 객체로
																			// 가져온다.

								int reNo = reply.getReNo();// 선택된것의 댓글 번호를 가져온다.

								dao.updateReply(new ReplyVO(reContent, reNo)); // 댓글
																				// 내용과
																				// 번호를
																				// 가지고
																				// 수정한다.

								DBreplytext.setText(reContent);// db에 저장한다.
							}
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				});
				replyPanel.add(updateReplyBtn);

				deleteReplybtn = new JButton("삭제"); // 삭제 버튼
				deleteReplybtn.setBounds(926, 38, 66, 23);

				deleteReplybtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							int result = JOptionPane.showConfirmDialog(ReplyPanel.this, "삭제하시겠습니까?","삭제", 2); // 2개선택
																										
							if (result == JOptionPane.OK_OPTION) { // ok루느면 삭제
								dao.deletetReply(reply.getReNo()); // 댓글 번호를 가져와
								// 삭제기능 실행
								replyListPanel.removeAll(); // 댓글 보여주는 화면 객체를 삭제
								replyListPanel.repaint(); // 댓글 보여주는 화면을 호출하여 갱신
								y = 0; // 댓글이 보여주는 값을 0으로 초기화
								MyRelyList(); // 자기 자신(내 댓글보기)를 불러온다.
							}
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				});
				replyPanel.add(deleteReplybtn);

				pane.setViewportView(replyListPanel); // 스크롤 팬 위에 패널을 올린다.
				pane.setBounds(0, 0, 1280, 730); // 패널위치 지정
				pane.getHorizontalScrollBar().setValue(0);
				add(pane); // 패널 더하기
				replyListPanel.add(replyPanel);

				y += 140;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// 전체 댓글조회
	private void ReplyList() {

		try {
			ReplyDAO dao = new ReplyDAO();
			List<ReplyVO> replys = dao.selectReplyList(artNo); // artNO로 댓글들 조회
			
			for (int i = 0; i < replys.size(); i++) {// for문으로 댓글들 갯수만큼 증가시킴
				ReplyVO reply = replys.get(i); // dao에서 값을불러와 reply에 저장			
				
				replyPanel = new JPanel();
				replyPanel.setLayout(null);

				replyPanel.setBounds(0, y, 1280, 120);
				
				JTextArea DBreplytext = new JTextArea();
				DBreplytext.setBounds(12, 10, 700, 100);
				DBreplytext.setLineWrap(true);// 자동 택스트 넘김
				DBreplytext.setFont(new Font("굴림", Font.PLAIN, 16));
				DBreplytext.setText(reply.getReContent()); // db 저장된 값을 가지온다.
				replyPanel.add(DBreplytext);// 패널에 값을 넣은다.

				insertReplyId = new JLabel(reply.getMemId());// db 저장된 값을 가지온다.
				insertReplyId.setBounds(857, 14, 57, 15);
				replyPanel.add(insertReplyId);// 패널에 값을 넣은다.

				insertReplyDate = new JLabel(reply.getReDate());// db 저장된 값을
																// 가지온다.
				insertReplyDate.setBounds(926, 14, 120, 15);
				replyPanel.add(insertReplyDate);// 패널에 값을 넣은다.

				updateReplyBtn = new JButton("수정");
				updateReplyBtn.setBounds(844, 39, 70, 20);

				updateReplyBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							int result = JOptionPane.showConfirmDialog(ReplyPanel.this, "수정하시겠습니까?", "수정", 2); //선택2개
							if (result == JOptionPane.OK_OPTION) {// ok루느면 수정
								String reContent = DBreplytext.getText();// db에
																			// 있는
																			// 내용을
																			// 객체로
																			// 가져온다.
								int reNo = reply.getReNo();// 선택된것의 댓글 번호를 가져온다.

								dao.updateReply(new ReplyVO(reContent, reNo));// 댓글
																				// 내용과
																				// 번호를
																				// 가지고
																				// 수정한다.

								DBreplytext.setText(reContent);// db에 저장한다

							}

						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				});
				replyPanel.add(updateReplyBtn);

				deleteReplybtn = new JButton("삭제");
				deleteReplybtn.setBounds(926, 38, 66, 23);

				deleteReplybtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {

							int result = JOptionPane.showConfirmDialog(ReplyPanel.this, "삭제하시겠습니까?", "삭제", 2); //2개 선택
							if (result == JOptionPane.OK_OPTION) {// ok루느면 삭제
								dao.deletetReply(reply.getReNo());// 댓글 번호를 가져와
																	// 삭제기능 실행
								replyListPanel.removeAll(); // 댓글 보여주는 화면 객체를 삭제
								replyListPanel.repaint(); // 댓글 보여주는 화면을 호출하여 갱신
								y = 0; // 댓글이 보여주는 값을 0으로 초기화
								ReplyList(); // 전체 댓글(전체 댓글보기)를 불러온다.

							}

						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}
				});

				replyPanel.add(deleteReplybtn);

				if (reply.getMemId().equals(member.getMemId())) { // 만약 db에 저장된 댓글의 아이디와
														// 로그인된 아이디가 같으면 수정 삭제
														// 가능

					updateReplyBtn.setVisible(true);
					deleteReplybtn.setVisible(true);
				} else if (!(reply.getMemId().equals(member.getMemId())) && member.getGrade().equals("1")) {

					// 만약 db에 저장된 댓글의 아이디와 로그인된 아이디가 다르지만 관리자이면 삭제만 가능
					DBreplytext.setEditable(false);
					updateReplyBtn.setVisible(false);
					deleteReplybtn.setVisible(true);
				} else { // 나머지 경우는 다 안됨
					DBreplytext.setEditable(false);
					updateReplyBtn.setVisible(false);
					deleteReplybtn.setVisible(false);
				}
				
									
				pane.setViewportView(replyListPanel);// 스크롤 팬 위에 패널을 올린다.
				pane.setBounds(0, 0, 1280, 730);// 위치지정
				pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMaximum());

				add(pane);
				replyListPanel.add(replyPanel);
				y += 140; // for문이 돌아갈때마다 더해준다.

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Create the panel.
	 */
	public ReplyPanel(MemberVO member, int artNo) {
		this.member = member;
		this.artNo = artNo; // boardFrame에서 게시글 번호를 가져온다.

		setLayout(null);

		setBackground(Color.WHITE);
		//setBounds(0, 0, 1280, 980);

		//////////////////////////////////////// 댓글 목록 조회
		//////////////////////////////////////// ////////////////////////////////////////
		//////////////////////////////////////// //////////////////////////////////////
		// 스크롤
		pane = new JScrollPane();

		replyListPanel = new JPanel();
		replyListPanel.setLayout(null);// GridBagLayout스크롤 오류나면 이넘으로 수정- 구글링에서
		// panel를 이걸로 변경하라는 설명이있다.
		
		add(pane);
		replyListPanel.setPreferredSize(new Dimension(1280,3000)); // 사이즈 정보를 가지고 있는
																// 객체를 이용해 패널의
																// 사이즈 지정

		ReplyList();

		//////////////////////////////////////// 댓글
		//////////////////////////////////////// 등록////////////////////////////////////////////////////////////

		// 등록 패널
		replyinsert = new JPanel();
		replyinsert.setBounds(0, 740, 1280, 240);
		add(replyinsert);
		replyinsert.setLayout(null);

		replyInsert = new JLabel("댓글 달기");
		replyInsert.setBounds(139, 63, 80, 15);
		replyinsert.add(replyInsert);

		// 전체 댓글조회 버튼
		allReplyBtn = new JButton("전체댓글조회");
		allReplyBtn.setBounds(932, 25, 135, 23);
		allReplyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMyReplyBtn.setVisible(true); // 내댓글 보기 버튼이 활성화
				allReplyBtn.setVisible(false); // 전체 댓글보기 버튼이 비활성화
				insertBtn.setVisible(true); // 전체 댓글보기 등록 버튼이 활성화
				myinsertBtn.setVisible(false);// 내 댓글보기 등록 버튼이 비활성화

				replyListPanel.removeAll(); // 화면 초기화
				replyListPanel.repaint();// 화면 불러오기
				y = 0; // y는 0으로 초기화
				ReplyList(); // 전체 댓글 보기 불러오기

			}
		});
		selectMyReplyBtn = new JButton("내댓글 조회");
		selectMyReplyBtn.setBounds(957, 25, 110, 23);
		selectMyReplyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectMyReplyBtn.setVisible(false); // 내댓글 보기 버튼이 비활성화
				allReplyBtn.setVisible(true);// 전체 댓글보기 버튼이 활성화
				insertBtn.setVisible(false);// 전체 댓글보기 등록 버튼이 비활성화
				myinsertBtn.setVisible(true);// 내 댓글보기 등록 버튼이 활성화

				replyListPanel.removeAll(); // 화면 초기화
				replyListPanel.repaint(); // 화면 불러오기
				y = 0; // y = 0으로 초기화
				MyRelyList(); // 내댓글 보기 불러오기

			}
		});

		replyinsert.add(allReplyBtn);
		allReplyBtn.setVisible(false); // 하나를 안보여주는 기능
		replyinsert.add(selectMyReplyBtn);

		insertReplyText = new JTextArea();
		insertReplyText.setLineWrap(true);// 자동 택스트 넘김
		insertReplyText.setFont(new Font("굴림", Font.PLAIN, 16));
		insertReplyText.setBounds(204, 59, 791, 110);
		replyinsert.add(insertReplyText);
		insertReplyText.setDocument(limit); // 텍스트 상자에 리미트를 건다.

		myinsertBtn = new JButton("댓글등록"); // 내 댓글 보기 일때 보인다.
		myinsertBtn.setBounds(1000, 83, 115, 23);

		myinsertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String reContent = insertReplyText.getText(); // 화면에 쓰는 내용

					if (reContent.length() == 0) { // 댓글에 내용을 안쓰면 출력
						JOptionPane.showMessageDialog(ReplyPanel.this, " 댓글내용을 입력하세요");
						return;
					}

					ReplyDAO dao = new ReplyDAO();
					dao.insertReply(new ReplyVO(reNo, artNo, member.getMemId(), reContent)); // 4를
																					// 가지고
					insertReplyText.setText("");//빈공간 처리																	// 등록한다.

					replyListPanel.removeAll(); // 화면 초기화
					replyListPanel.repaint(); // 화면 호출
					y = 0; // y초기화
					MyRelyList(); // 내댓글 보기 조회

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		insertBtn = new JButton("댓글등록"); // 전체 댓글 보기일때만 보인다.
		insertBtn.setBounds(1000, 83, 115, 23);

		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String reContent = insertReplyText.getText(); // 화면에 쓰는 내용

					if (reContent.length() == 0) {// 댓글에 내용을 안쓰면 출력
						JOptionPane.showMessageDialog(ReplyPanel.this, "댓글내용을 입력하세요");
						return;
					}

					ReplyDAO dao = new ReplyDAO();
					dao.insertReply(new ReplyVO(reNo, artNo, member.getMemId(), reContent));// 4를
																				// 가지고// 등록한다.
					insertReplyText.setText("");	//빈공간 처리										

					replyListPanel.removeAll(); // 화면 초기화
					replyListPanel.repaint(); // 화면 호출
					y = 0; // y초기화
					ReplyList(); // 전체 댓글보기 호출

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});

		replyinsert.add(insertBtn);
		myinsertBtn.setVisible(false);// 하나를 안보여주는 기능
		replyinsert.add(myinsertBtn);

	}
}