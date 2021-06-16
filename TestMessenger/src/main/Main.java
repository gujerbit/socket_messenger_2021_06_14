package main;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import server.Server;
import server.ServerCloseThread;

public class Main {

	public static void main(String[] args) {
		try {
			System.out.println("���� ������...");
			ServerSocket server = new ServerSocket(9000);
			System.out.println("���� ���� �Ϸ�");
			Map<String, Socket> clients = new HashMap<String, Socket>();
			
			Thread serverCloseThread = new Thread(new ServerCloseThread(server, clients));
			serverCloseThread.start();

			while (true) {
				Socket client = server.accept();
				Thread serverThread = new Thread(new Server(client, clients, server));
				serverThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
