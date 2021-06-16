package client;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import view.Display;

public class ClientSendExit implements Runnable {
	private Socket client = null;
	private String name = "";
	
	public ClientSendExit(Socket client, String name) {
		this.client = client;
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			DataOutputStream outputData = new DataOutputStream(output);
			outputData.writeUTF(name + "/exit");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
