package com.cg.IRCTC.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.IRCTC.services.GeneralInfoService;

@SpringBootApplication
@RestController
@RequestMapping("/general")
public class GeneralInfo {

	@Autowired
	GeneralInfoService service;
	
	@PostMapping("getAvailSeat")
	public ResponseEntity<Object> getAvailSeat(@RequestParam String trainNumber){
		List<List<Integer>> val = service.getAllAvailbeSeat(trainNumber);
		return new ResponseEntity<Object>(val,HttpStatus.OK);
		
	}
	@PostMapping("getBookedSeat")
	public ResponseEntity<Object> getBookedSeat(@RequestParam String trainNumber){
		List<List<Integer>> val = service.getAllBookedSeat(trainNumber);
//		System.out.println(val.size());
		return new ResponseEntity<Object>(val,HttpStatus.OK);
		
	}
}
