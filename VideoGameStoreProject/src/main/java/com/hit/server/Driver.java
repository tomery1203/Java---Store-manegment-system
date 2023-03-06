package com.hit.server;

public class Driver 
{
	
	public static void main(String[] args) 
	{
			Server server = new Server(12121);
			new Thread(server).start();
	}
}
