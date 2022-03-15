package com.cg.IRCTC.entity;

import java.util.ArrayList;
import java.util.List;

public class Coaches {
	private String coaches_name;
	private int number_seats;
	private int number_available_seat;
	private List<Boolean> seatStructure = new ArrayList<Boolean>();
	
	public Coaches(String coaches_name) {
		super();
		this.coaches_name = coaches_name;
		if(coaches_name.equals("A/C Sleeper")) {
			this.number_seats = 60;
			this.number_available_seat = 60;
			for(int i = 0; i < 60; i++) {
				seatStructure.add(true);
			}
		}else if(coaches_name.equals("Non A/C Sleeper")) {
			this.number_seats = 60;
			this.number_available_seat = 60;
			for(int i = 0; i < 60; i++) {
				seatStructure.add(true);
			}
		}else if(coaches_name.equals("Seater")) {
			this.number_seats = 120;
			this.number_available_seat = 120;
			for(int i = 0; i < 120; i++) {
				seatStructure.add(true);
			}
		}
	}
	
	public String getCoaches_name() {
		return coaches_name;
	}
	public int getNumber_seats() {
		return number_seats;
	}
	public int getNumber_available_seat() {
		return number_available_seat;
	}
	public List<Boolean> getSeatStructure() {
		return seatStructure;
	}
	public boolean bookSeat(List<Integer> book) {
		boolean check = false;
		for(int i = 0; i < book.size(); i++) {
			if(book.get(i) > this.number_seats) {
				return false;
			}
			if(seatStructure.get(book.get(i)-1)) {
				continue;
			}
			check = true;
			break;
		}
		if(check) {
			return false;
		}
		for(int j = 0; j < book.size(); j++) {
		   seatStructure.set(book.get(j)-1, false);
		}
		number_available_seat -= book.size();
		return true;
	}
	public boolean bookSeatOne(int book) {
		if(book > this.number_seats) {
			return false;
		}
		if(this.seatStructure.get(book-1)) {
			this.seatStructure.set(book-1, false);
			this.number_available_seat--;
			return true;
		}else {
			return false;
		}
	}
	
}
