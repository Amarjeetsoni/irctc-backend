package com.cg.IRCTC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.IRCTC.dao.ControllerDao;

@Service
public class GeneralInfoImplementation implements GeneralInfoService{

	@Autowired
	ControllerDao dao;
	@Override
	public List<List<Integer>> getAllAvailbeSeat(String trainNumber) {
		return dao.getAllAvailbeSeat(trainNumber);
	}

	@Override
	public List<List<Integer>> getAllBookedSeat(String trainNumber) {
		return dao.getAllBookedSeat(trainNumber);
	}

}
