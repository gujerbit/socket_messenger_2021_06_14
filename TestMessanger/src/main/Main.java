package main;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import server.Server;

public class Main {
	private static String name = "";
	
	public static void main(String[] args) {
		try {
			System.out.println("���� ������...");
			ServerSocket server = new ServerSocket(9000);
			System.out.println("���� ���� �Ϸ�");
			Map<String, Socket> clients = new HashMap<String, Socket>();
			
			while(true) {
				Socket client = server.accept();
				Thread serverThread = new Thread(new Server(client, clients, server));
				serverThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
