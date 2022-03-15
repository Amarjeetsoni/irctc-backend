package com.cg.IRCTC.services;

import java.util.List;

import com.cg.IRCTC.entity.Coaches;
import com.cg.IRCTC.entity.Train;

public interface AdminServices {
	public boolean addTrain(String name, String number);
	public List<Train> getAllTrain();
	public boolean addCoaches(String TrainNumber, String CoatchName);
	public List<Coaches> getTrainDetail(String number);
	public Coaches getCoach(String TrainNumber, int num);
	public boolean removeCoaches(String trainNumber, int num);
	public boolean removeTrain(String trainNumber);
}
