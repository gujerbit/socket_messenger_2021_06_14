package client;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import view.Display;

public class ClientSend implements Runnable {
	private Socket client = null;
	private Display display = null;

	public ClientSend(Socket client, Display display) {
		this.client = client;
		this.display = display;
	}

	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			DataOutputStream outputData = new DataOutputStream(output);
			if(!display.input.getText().equals("")) {
				outputData.writeUTF(display.input.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
