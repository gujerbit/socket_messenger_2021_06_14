package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.Socket;
import java.util.ArrayList;

import view.Display;

public class Client {
	private static String name = "";

	public static void main(String[] args) {
		Display display = new Display();
		display.createInitDisplay();

		display.setNameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name = display.nameSpace.getText();
				display.messangerStart();
				display.input.requestFocus(true);

				try {
					System.out.println("서버 연결중...");
					Socket client = new Socket("127.0.0.1", 9000);

					Thread receive = new Thread(new ClientReceive(client, display));
					Thread init = new Thread(new ClientSendInit(client, name));
					
					init.start();
					receive.start();
					
					display.inputBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Thread send = new Thread(new ClientSend(client, display));
							send.start();
						}
					});
					
					display.messanger.addWindowListener(new WindowListener() {
						@Override
						public void windowOpened(WindowEvent e) {}
						@Override
						public void windowIconified(WindowEvent e) {}
						@Override
						public void windowDeiconified(WindowEvent e) {}
						@Override
						public void windowDeactivated(WindowEvent e) {}
						@Override
						public void windowClosing(WindowEvent e) {
							try {
								Thread clientSendThread = new Thread(new ClientSendExit(client, name));
								clientSendThread.start();
								Thread.sleep(1000);
								client.close();
								System.out.println(name + " client is close");
								System.exit(0);
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}			
						@Override
						public void windowClosed(WindowEvent e) {}
						@Override
						public void windowActivated(WindowEvent e) {}
					});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

}
