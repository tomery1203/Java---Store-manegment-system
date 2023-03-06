package com.hit.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@SuppressWarnings("unused")
public class Server implements Runnable {
	
	@SuppressWarnings("unused")
	private ServerSocket server  = null;
	private ExecutorService executorService;
	
	public Server(int port) {
		 try {
			this.server = new ServerSocket(port);
			this.executorService = Executors.newFixedThreadPool(1000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			while (true) {
			    Socket someClient = server.accept();
			    executorService.execute(new HandleRequest(someClient));
			}
		}
		catch (Exception e){}
	}
}

