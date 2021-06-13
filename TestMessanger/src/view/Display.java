package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Display {
	private JFrame initFrame = new JFrame("Messanger Ver 1.0"); // 사용자 이름 입력 화면
	public JFrame messanger = new JFrame("Messanger Ver 1.0"); // 메신저 화면

	private JPanel mainPanel = new JPanel(); // ContentPanel
	public JTextArea userArea = new JTextArea(); // show current connecting users
	private JTextArea messageArea = new JTextArea(); // show message
	private JPanel inputPanel = new JPanel(); // input message

	private GridLayout initLayout = new GridLayout(3, 1); // init display layout

	private JLabel initLabel = new JLabel(); // init label

	public JTextField nameSpace = new JTextField(); // 유저 이름 입력 창
	public JTextField input = new JTextField(); // input TextField

	public JButton setNameBtn = new JButton(); // 입력 버튼
	public JButton inputBtn = new JButton(); // click to send message

	private JScrollPane scrollPanel = new JScrollPane();
	private JScrollPane scrollUserPanel = new JScrollPane();

	public void messangerStart() {
		initFrame.setVisible(false);
		createMessangerDisplay();
	}

	// create init display
	public void createInitDisplay() {
		// init display setting
		initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initFrame.setPreferredSize(new Dimension(300, 120));
		initLabel.setText("사용자의 이름을 입력해주세요.");
		initLabel.setHorizontalAlignment(JLabel.CENTER);
		setNameBtn.setText("입력");
		initFrame.getContentPane().add(initLabel);
		initFrame.getContentPane().add(nameSpace);
		initFrame.getContentPane().add(setNameBtn);
		initFrame.getContentPane().setLayout(initLayout);
		// show init display
		initFrame.pack();
		initFrame.setVisible(true);
	}

	// create main display
	private void createMessangerDisplay() {
		mainPanel.setPreferredSize(new Dimension(800, 700));
		scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanel.setViewportView(messageArea);
		scrollUserPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollUserPanel.setViewportView(userArea);
		messageArea.setEditable(false);
		userArea.setEditable(false);
		// 기본 패널에 채팅창과 유저창과 입력창 배치
		messanger.setContentPane(mainPanel);
		mainPanel.add(scrollPanel);
		mainPanel.add(scrollUserPanel);
		mainPanel.add(inputPanel);
		// 기본 패널 레이아웃 설정
		mainPanel.setLayout(null);
		scrollPanel.setBounds(0, 0, 600, 600);
		messageArea.setBounds(0, 0, 600, 600);
		scrollUserPanel.setBounds(600, 0, 200, 600);
		userArea.setBounds(600, 0, 200, 600);
		inputPanel.setBounds(0, 600, 800, 100);
		// 입력창 설정
		inputPanel.add(input);
		inputPanel.add(inputBtn);
		inputPanel.setLayout(null);
		input.setBounds(0, 0, 700, 100);
		inputBtn.setBounds(700, 0, 100, 100);
		inputBtn.setText("보내기");
		// 색 설정
		scrollPanel.setBackground(new Color(50, 50, 50));
		// show display
		messanger.pack();
		messanger.setVisible(true);
	}

	public void setMessage(String msg) {
		messageArea.append(msg + "\n");
	}
	
	public void setWhisper(boolean who, String sendUser, String sendMsg) {
		if(who) messageArea.append("[귓속말/내가 " + sendUser + "에게 : " + sendMsg + "]\n");
		else messageArea.append("[귓속말/" + sendUser + "가 나에게 : " + sendMsg + "]\n");
	}
	
	public void setUser(String name) {
		String[] nameSplit = name.split("/");
		if(!userArea.getText().contains(nameSplit[0])) {
			if(nameSplit[1].equals("me")) userArea.append("[나]" + nameSplit[0] + "\n");
			else userArea.append(nameSplit[0] + "\n");
		}
	}

}