package com.cg.IRCTC.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.IRCTC.services.UserServices;

@SpringBootApplication
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServices user;
	
	@PostMapping("bookTickets")
	public ResponseEntity<Object> bookTickets(@RequestBody BookTickets td){
		if(user.bookTickets(td.TainNumber, td.coachNumber, td.book)) {
			return new ResponseEntity<Object>("Train Ticket Booked sucessfully!", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>("Train Number is invalid/CoatchNumber Does Not exist/List of Seat is unavailable!", HttpStatus.OK);
		}
	}
	@PostMapping("bookTicket")
	public ResponseEntity<Object> bookTicket(@RequestBody BookTicket td){
		if(user.bookTicket(td.TainNumber, td.coachNumber, td.book)) {
			return new ResponseEntity<Object>("Train Ticket Booked sucessfully!", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>("Train Number is invalid/CoatchNumber Does Not exist/Seat is unavailable!", HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("getavailSeat")
	public ResponseEntity<Object> getSeats(@RequestBody CoachDetails cd){
		List<Integer> li = user.getSeat(cd.trainNumber, cd.number);
		if(li == null) {
			return new ResponseEntity<Object>("Train Number is invalid/CoatchNumber Does Not exist", HttpStatus.BAD_GATEWAY);
		}
		else {
			return new ResponseEntity<Object>(li, HttpStatus.OK);
		}
	}
	
	
}
class BookTickets{
	public String TainNumber;
	public int coachNumber;
	public List<Integer> book;
}
class BookTicket{
	public String TainNumber;
	public int coachNumber;
	public int book;
}
