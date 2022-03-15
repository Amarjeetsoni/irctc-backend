package com.cg.IRCTC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.IRCTC.dao.ControllerDao;

@Service
public class UserServicesImplementation implements UserServices {

	@Autowired
	ControllerDao dao;
	
	@Override
	public boolean bookTickets(String TainNumber, int coachNumber, List<Integer> book) {
		return dao.bookTickets(TainNumber, coachNumber, book);
	}

	@Override
	public List<Integer> getSeat(String TraiNumber, int coachNumber) {
		return dao.getSeat(TraiNumber, coachNumber);
	}

	@Override
	public boolean bookTicket(String TainNumber, int coachNumber, int book) {
		return dao.bookTicket(TainNumber, coachNumber, book);
	}

}
