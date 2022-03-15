package com.cg.IRCTC.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cg.IRCTC.entity.Coaches;
import com.cg.IRCTC.entity.Train;

@Repository
public class ControllerDao {
	
	List<Train> train = new ArrayList<>();
	
	public boolean addTrain(String name, String number) {
		for(int i = 0; i < train.size(); i++) {
			train.get(i).toString();
			if(train.get(i).getTrainNumber().equals(number)) {
				return false;
			}
		}
		Train tr = new Train(name, number);
		train.add(tr);
		return true;
	}
	
	public List<Train> getAllTrain() {
		if(train.size() == 0) {
			return null;
		}
		return train;
	}
	
	public boolean addCoaches(String TrainNumber, String coatchName) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(TrainNumber)) {
				Train tr = train.get(i);
				return tr.addCoaches(coatchName);
			}
		}
		return false;
	}
	
	public List<Coaches> getTrainDetail(String number) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(number)) {
				Train tr = train.get(i);
				
				return tr.getCoatchs();
			}
		}
		return null;
	}
	public Coaches getCoach(String TrainNumber, int num) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(TrainNumber)) {
				Train tr = train.get(i);
				
				return tr.getCoachDetails(num);
			}
		}
		return null;
	}
	public boolean removeCoaches(String trainNumber, int num) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(trainNumber)) {
				Train tr = train.get(i);
				return tr.deleteCoaches(num);
			}
		}
		return false;
	}
	public boolean removeTrain(String trainNumber) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(trainNumber)) {
				train.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean bookTickets(String TainNumber, int coachNumber, List<Integer> book) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(TainNumber)) {
				Train tr = train.get(i);
				if(tr.getNumofCoatches() < coachNumber) {
					return false;
				}
				Coaches coa = tr.getCoatchs().get(coachNumber-1);
				return coa.bookSeat(book);
			}
		}
		return false;
	}
	public boolean bookTicket(String TainNumber, int coachNumber, int book) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(TainNumber)) {
				Train tr = train.get(i);
				if(tr.getNumofCoatches() < coachNumber) {
					return false;
				}
				Coaches coa = tr.getCoatchs().get(coachNumber-1);
				return coa.bookSeatOne(book);
			}
		}
		return false;
	}
	
	public List<Integer> getSeat(String TainNumber, int coachNumber) {
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(TainNumber)) {
				Train tr = train.get(i);
				if(tr.getNumofCoatches() < coachNumber) {
					return null;
				}
				Coaches coa = tr.getCoatchs().get(coachNumber-1);
				List<Integer> avail = new ArrayList<>();
				for(int j = 0; j < coa.getSeatStructure().size(); j++) {
					if(coa.getSeatStructure().get(j)) {
					avail.add(j+1);
					}
				}
				return avail;
			}
		}
		return null;
	}
	public List<List<Integer>> getAllAvailbeSeat(String trainNumber) {
		List<List<Integer>> val = new ArrayList<>();
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(trainNumber)) {
				Train tr = train.get(i);
//				System.out.println(tr.toString());
				List<Coaches> coa = tr.getCoatchs();
				for(int j = 0; j < coa.size(); j++) {
					List<Integer> temp = new ArrayList<>();
					for(int k = 0; k < coa.get(j).getSeatStructure().size(); k++) {
						if(coa.get(j).getSeatStructure().get(k)) {
							temp.add(k+1);
						}
					}
					val.add(temp);
				}
				return val;
			}
		}
		return val;
	}
	public List<List<Integer>> getAllBookedSeat(String trainNumber) {
		List<List<Integer>> val = new ArrayList<>();
		for(int i = 0; i < train.size(); i++) {
			if(train.get(i).getTrainNumber().equals(trainNumber)) {
				
				Train tr = train.get(i);
//				System.out.println(tr.toString());
				List<Coaches> coa = tr.getCoatchs();
				for(int j = 0; j < coa.size(); j++) {
					List<Integer> temp = new ArrayList<>();
					for(int k = 0; k < coa.get(j).getSeatStructure().size(); k++) {
						if(!coa.get(j).getSeatStructure().get(k)) {
							temp.add(k+1);
						}
					}
					val.add(temp);
				}
				return val;
			}
		}
		return val;
	}
	
}
