package server;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server implements Runnable {
	private Socket client = null;
	private Map<String, Socket> clients = null;
	private ServerSocket server = null;

	public Server(Socket client, Map<String, Socket> clients, ServerSocket server) {
		this.client = client;
		this.clients = clients;
		this.server = server;
	}

	@Override
	public void run() {
		while (true) {
			try {
				InputStream input = client.getInputStream();
				DataInputStream inputData = new DataInputStream(input);
				String msg = inputData.readUTF();
				
				String currentUser = "";
				
				for (String name : clients.keySet()) {
					if (client == clients.get(name)) {
						currentUser = name;
					}
				}
				
				if(msg.contains("/exit")) {
					String exitUserName = msg.substring(0, msg.length()-5);
					clients.remove(exitUserName);
					
					for(String name : clients.keySet()) {
						Thread serverSendThread = new Thread(new ServerSendUserClear(clients.get(name)));
						serverSendThread.start();
					}
					
					for(String name : clients.keySet()) {
						Thread serverSendNotice = new Thread(new ServerSendNotice(clients.get(name), "[Server Message : " + exitUserName + "님이 퇴장하셨습니다.]"));
						Thread serverUserThread = new Thread(new ServerSendUser(clients.get(name), clients));
						serverSendNotice.start();
						serverUserThread.start();
					}
					
					Thread serverSendThread = new Thread(new ServerSendExit(client, msg));
					serverSendThread.start();
					System.out.println(exitUserName + " is close");
					System.out.println("현재 접속 중인 유저 : " + clients.size() + "명");
					
					break;
				} else if(msg.contains("/w")) {
					String[] msgSplit = msg.split(" ");
					String sendName = msgSplit[1];
					String sendMsg = "";
					
					for(int i = 2; i < msgSplit.length; i++) {
						sendMsg += msgSplit[i];
					}
					
					Thread serverSendThread = new Thread(new ServerSendWhisper(clients.get(sendName), currentUser, sendMsg));
					Thread serverSendThreadMe = new Thread(new ServerSendWhisper(clients.get(currentUser), sendName, sendMsg + "/me"));
					serverSendThread.start();
					serverSendThreadMe.start();
				} else if (!clients.containsValue(client)) {
					clients.put(msg, client);
					System.out.println("현재 접속 중인 유저 : " + clients.size() + "명");
					
					for(String name : clients.keySet()) {
						Thread serverSendThread = new Thread(new ServerSendNotice(clients.get(name), "[Server Message : " + msg + "님이 입장하셨습니다.]"));
						Thread serverUserThread = new Thread(new ServerSendUser(clients.get(name), clients));
						serverSendThread.start();
						serverUserThread.start();
					}
				} else {
					for (String name : clients.keySet()) {
						if (client == clients.get(name)) {
							Thread serverSendThread = new Thread(new ServerSendMe(clients.get(name), msg));
							serverSendThread.start();
						} else {
							Thread serverSendThread = new Thread(new ServerSendOther(clients.get(name), msg, currentUser));
							serverSendThread.start();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}