package com.cg.IRCTC.entity;

import java.util.ArrayList;
import java.util.List;

public class Train {
	private String trainName;
	private String trainNumber;
	private int numofCoatches;
	private List<Coaches> coatchs = new ArrayList<>();
	public Train(String trainName, String trainNumber) {
		super();
		this.trainName = trainName;
		this.trainNumber = trainNumber;
		this.numofCoatches = 0;
	}
	public String getTrainName() {
		return trainName;
	}
	public int getNumofCoatches() {
		return numofCoatches;
	}
	public List<Coaches> getCoatchs() {
		return coatchs;
	}
	
	public String getTrainNumber() {
		return trainNumber;
	}
	public boolean addCoaches(String coatchName) {
			if(coatchName.equals("A/C Sleeper") || coatchName.equals("Non A/C Sleeper") || coatchName.equals("Seater"))
			{
				Coaches coa = new Coaches(coatchName);
				this.coatchs.add(coa);
				this.numofCoatches++;
				return true;
			}
			return false;
	}
	public boolean deleteCoaches(int num) {
		if(num <= this.numofCoatches) {
			this.coatchs.remove(num-1);
			this.numofCoatches--;
			return true;
		}
		return false;
		
	}
	
	public List<Coaches> getTrainDetails(){
		if(this.numofCoatches <= 0) {
			return null;
		}
		return this.coatchs;
	}
	
	public Coaches getCoachDetails(int num) {
		if(num <= this.numofCoatches) {
			return this.coatchs.get(num-1);
		}
		return null;
	}
	@Override
	public String toString() {
		return "Train [trainName=" + trainName + ", trainNumber=" + trainNumber + ", numofCoatches=" + numofCoatches
				+ ", coatchs=" + coatchs + "]";
	}
	
	
	

}
