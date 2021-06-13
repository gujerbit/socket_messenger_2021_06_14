package client;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSendInit implements Runnable {
	private Socket client = null;
	private String name = "";
	
	public ClientSendInit(Socket client, String name) {
		this.client = client;
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			DataOutputStream outputData = new DataOutputStream(output);
			outputData.writeUTF(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
