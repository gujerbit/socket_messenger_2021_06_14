package server;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class ServerSendUser implements Runnable {
	private Socket client = null;
	private Map<String, Socket> clients = null;
	
	public ServerSendUser(Socket client, Map<String, Socket> clients) {
		this.client = client;
		this.clients = clients;
	}
	
	@Override
	public void run() {
		try {
			for(String name : clients.keySet()) {
				Thread serverSendThread = null;
				
				if(client == clients.get(name)) serverSendThread = new Thread(new ServerSendUserName(client, name + "/me"));
				else serverSendThread = new Thread(new ServerSendUserName(client, name));
				
				serverSendThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
