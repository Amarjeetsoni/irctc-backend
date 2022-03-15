package com.cg.IRCTC.services;

import java.util.List;

public interface GeneralInfoService {

	public List<List<Integer>> getAllAvailbeSeat(String trainNumber);
	public List<List<Integer>> getAllBookedSeat(String trainNumber);
}
