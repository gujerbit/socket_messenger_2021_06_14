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
					System.out.println("접속자가 없습니다. 접속자가 생기지 않는다면 5분뒤에 서버가 종료됩니다.");
					for (int i = 0; i < 300; i++) {
						if (!clients.isEmpty()) break;
						Thread.sleep(1000);
					}

					if (clients.isEmpty()) {
						System.out.println("서버가 종료됩니다.");
						server.close();
						System.exit(0);
						break;
					} else {
						System.out.println("접속자 확인");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("주기적으로 접속자 확인중...");
				try {
					System.out.println("접속자 존재");
					Thread.sleep(5000);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
