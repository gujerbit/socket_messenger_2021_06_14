package server;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSendOther implements Runnable {
	private Socket client = null;
	private String msg = "";
	private String name = "";
	
	public ServerSendOther(Socket client, String msg, String name) {
		this.client = client;
		this.msg = msg;
		this.name = name;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			DataOutputStream outputData = new DataOutputStream(output);
			outputData.writeUTF("[" + name + " : " + msg + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
