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
	private JFrame initFrame = new JFrame("Messanger Ver 1.0"); // ����� �̸� �Է� ȭ��
	public JFrame messanger = new JFrame("Messanger Ver 1.0"); // �޽��� ȭ��

	private JPanel mainPanel = new JPanel(); // ContentPanel
	public JTextArea userArea = new JTextArea(); // show current connecting users
	private JTextArea messageArea = new JTextArea(); // show message
	private JPanel inputPanel = new JPanel(); // input message

	private GridLayout initLayout = new GridLayout(3, 1); // init display layout

	private JLabel initLabel = new JLabel(); // init label

	public JTextField nameSpace = new JTextField(); // ���� �̸� �Է� â
	public JTextField input = new JTextField(); // input TextField

	public JButton setNameBtn = new JButton(); // �Է� ��ư
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
		initLabel.setText("������� �̸��� �Է����ּ���.");
		initLabel.setHorizontalAlignment(JLabel.CENTER);
		setNameBtn.setText("�Է�");
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
		// �⺻ �гο� ä��â�� ����â�� �Է�â ��ġ
		messanger.setContentPane(mainPanel);
		mainPanel.add(scrollPanel);
		mainPanel.add(scrollUserPanel);
		mainPanel.add(inputPanel);
		// �⺻ �г� ���̾ƿ� ����
		mainPanel.setLayout(null);
		scrollPanel.setBounds(0, 0, 600, 600);
		messageArea.setBounds(0, 0, 600, 600);
		scrollUserPanel.setBounds(600, 0, 200, 600);
		userArea.setBounds(600, 0, 200, 600);
		inputPanel.setBounds(0, 600, 800, 100);
		// �Է�â ����
		inputPanel.add(input);
		inputPanel.add(inputBtn);
		inputPanel.setLayout(null);
		input.setBounds(0, 0, 700, 100);
		inputBtn.setBounds(700, 0, 100, 100);
		inputBtn.setText("������");
		// �� ����
		scrollPanel.setBackground(new Color(50, 50, 50));
		// show display
		messanger.pack();
		messanger.setVisible(true);
	}

	public void setMessage(String msg) {
		messageArea.append(msg + "\n");
	}
	
	public void setWhisper(boolean who, String sendUser, String sendMsg) {
		if(who) messageArea.append("[�ӼӸ�/���� " + sendUser + "���� : " + sendMsg + "]\n");
		else messageArea.append("[�ӼӸ�/" + sendUser + "�� ������ : " + sendMsg + "]\n");
	}
	
	public void setUser(String name) {
		String[] nameSplit = name.split("/");
		if(!userArea.getText().contains(nameSplit[0])) {
			if(nameSplit[1].equals("me")) userArea.append("[��]" + nameSplit[0] + "\n");
			else userArea.append(nameSplit[0] + "\n");
		}
	}

}