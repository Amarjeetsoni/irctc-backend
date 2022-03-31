package com.cg.IRCTC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.IRCTC.dao.ControllerDao;

@Service
public class AuthonticateService {

	@Autowired
	ControllerDao dao;
	
	
	public boolean setPasswordforAdmin(String username, String password) {
		return dao.setPasswordForAdmin(username, password);
	}
	
	public boolean setPasswordforUser(String username, String password) {
		return dao.setPasswordForUser(username, password);
	}
	
	public boolean login(String role, String user, String password) {
		return dao.login(role, user, password);
	}
	
}
