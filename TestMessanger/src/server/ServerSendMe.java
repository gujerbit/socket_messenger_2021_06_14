package server;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSendMe implements Runnable {
	private Socket client = null;
	private String msg = "";
	
	public ServerSendMe(Socket client, String msg) {
		this.client = client;
		this.msg = msg;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			DataOutputStream outputData = new DataOutputStream(output);
			outputData.writeUTF("[³ª : " + msg + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
