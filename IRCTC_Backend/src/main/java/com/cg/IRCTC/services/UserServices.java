package com.cg.IRCTC.services;

import java.util.List;

public interface UserServices {
	public boolean bookTickets(String TainNumber, int coachNumber, List<Integer> book);
	public boolean bookTicket(String TainNumber, int coachNumber, int book);
	public List<Integer> getSeat(String TraiNumber, int coachNumber);
}
