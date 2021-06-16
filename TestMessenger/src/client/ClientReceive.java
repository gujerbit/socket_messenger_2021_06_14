package client;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

import view.Display;

public class ClientReceive implements Runnable {
	private Socket client = null;
	private Display display = null;

	public ClientReceive(Socket client, Display display) {
		this.client = client;
		this.display = display;
	}

	@Override
	public void run() {
		while (true) {
			try {
				InputStream input = client.getInputStream();
				DataInputStream inputData = new DataInputStream(input);
				String msg = inputData.readUTF();
				
				if(msg.contains("/exit")) {
					inputData.close();
					break;
				}
				
				if(msg.contains("/clear")) {
					display.userArea.setText("");
				} else if (!msg.contains("/user") && !msg.contains("/whisper")) {
					display.setMessage(msg);
					display.input.setText("");
					display.input.requestFocus(true);
				} else if(msg.contains("/user")) {
					display.setUser(msg);
				} else if(msg.contains("/whisper")) {
					if(msg.contains("/me")) {
						String[] msgSplit = msg.split("/");
						String sendUser = msgSplit[2];
						String sendMsg = msgSplit[0];
						
						display.setWhisper(true, sendUser, sendMsg);
						display.input.setText("");
						display.input.requestFocus(true);
					} else {
						String[] msgSplit = msg.split("/");
						String sendUser = msgSplit[1];
						String sendMsg = msgSplit[0];
						
						display.setWhisper(false, sendUser, sendMsg);
						display.input.setText("");
						display.input.requestFocus(true);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
