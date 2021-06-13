package server;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerSendUserClear implements Runnable {
	private Socket client = null;
	
	public ServerSendUserClear(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			OutputStream output = client.getOutputStream();
			DataOutputStream outputData = new DataOutputStream(output);
			outputData.writeUTF("/clear");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
