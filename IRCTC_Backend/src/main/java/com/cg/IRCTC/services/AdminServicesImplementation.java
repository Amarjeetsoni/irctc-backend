package com.cg.IRCTC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.IRCTC.dao.ControllerDao;
import com.cg.IRCTC.entity.Coaches;
import com.cg.IRCTC.entity.Train;

@Service
public class AdminServicesImplementation implements AdminServices {

	@Autowired
	ControllerDao dao;
	
	@Override
	public boolean addCoaches(String TrainNumber, String coatchName) {
		return dao.addCoaches(TrainNumber, coatchName);
	}

	@Override
	public List<Coaches> getTrainDetail(String number) {
		return dao.getTrainDetail(number);
	}

	@Override
	public Coaches getCoach(String TrainNumber, int num) {
		return dao.getCoach(TrainNumber, num);
	}

	@Override
	public boolean removeCoaches(String trainNumber, int num) {
		return dao.removeCoaches(trainNumber, num);
	}

	@Override
	public boolean addTrain(String name, String number) {
		return dao.addTrain(name, number);
	}

	@Override
	public List<Train> getAllTrain() {
		return dao.getAllTrain();
	}

	@Override
	public boolean removeTrain(String trainNumber) {
		return dao.removeTrain(trainNumber);
	}

}
