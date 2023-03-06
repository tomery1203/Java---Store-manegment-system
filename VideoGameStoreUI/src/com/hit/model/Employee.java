package com.hit.model;

import java.io.IOException;

import com.hit.dm.VideoGame;
import com.hit.server.Request;
import com.hit.server.Response;
import java.net.Socket;

public class Employee {

	private   String m_host = "localhost";
    private int m_port = 12121;
    private Socket m_socket =null;
    private Request m_request =null;
    
	public Employee() {
		super();
		    try {
				this.setM_socket(new Socket(this.m_host, this.m_port));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public Socket getM_socket() {
		return m_socket;
	}

	public void setM_socket(Socket m_socket) {
		this.m_socket = m_socket;
	}

	public Request getM_request() {
		return m_request;
	}

	public void setM_request(Request m_request) {
		this.m_request = m_request;
	}
}
