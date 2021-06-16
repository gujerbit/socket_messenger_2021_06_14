package server;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSendWhisper implements Runnable {
	private Socket client = null;
	private String currentUser = "";
	private String msg = "";
	
	public ServerSendWhisper(Socket client, String currentUser, String msg) {
		this.client = client;
		this.currentUser = currentUser;
		this.msg = msg;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			DataOutputStream outputData = new DataOutputStream(output);
			outputData.writeUTF(msg + "/" + currentUser + "/whisper");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
