package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ServerCloseThread implements Runnable {
	Map<String, Socket> clients = null;
	ServerSocket server = null;

	public ServerCloseThread(ServerSocket server, Map<String, Socket> clients) {
		this.server = server;
		this.clients = clients;
	}

	@Override
	public void run() {
		while (true) {
			if (clients.isEmpty()) {
				try {
					System.out.println("�����ڰ� �����ϴ�. �����ڰ� ������ �ʴ´ٸ� 5�еڿ� ������ ����˴ϴ�.");
					for (int i = 0; i < 300; i++) {
						if (!clients.isEmpty()) break;
						Thread.sleep(1000);
					}

					if (clients.isEmpty()) {
						System.out.println("������ ����˴ϴ�.");
						server.close();
						System.exit(0);
						break;
					} else {
						System.out.println("������ Ȯ��");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("�ֱ������� ������ Ȯ����...");
				try {
					System.out.println("������ ����");
					Thread.sleep(5000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
