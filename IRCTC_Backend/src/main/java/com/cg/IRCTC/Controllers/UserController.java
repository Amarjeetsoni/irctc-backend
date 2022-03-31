package com.cg.IRCTC.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;

import com.cg.IRCTC.entity.Coaches;
import com.cg.IRCTC.entity.Train;
import com.cg.IRCTC.services.AdminServices;
import com.cg.IRCTC.services.UserServices;

@SpringBootApplication
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserServices user;
	@Autowired
	AdminServices admin;
	
	@RequestMapping(value = "bookTickets", method = RequestMethod.POST)
	public String bookTickets(BookTickets td, ModelMap model){
		List<Integer> allTicket = new ArrayList<>();
		String[] seat = td.getBook().split(" ");
		for(int i = 0; i < seat.length; i++) {
			Integer seat1 = Integer.parseInt(seat[i]);
			allTicket.add(seat1);
		}
		if(user.bookTickets(td.getTainNumber(), td.getCoachNumber(), allTicket)) {
			model.put("SuccessMessage","Train Ticket Booked sucessfully!");
		}
		else {
			model.put("ErrorMessage","Train Number is invalid/CoatchNumber Does Not exist/List of Seat is unavailable!");
		}
		return "UserView";
	}
//	@PostMapping("bookTicket")
	@RequestMapping(value = "bookTicket", method = RequestMethod.POST)
	public String bookTicket(BookTicket td, ModelMap model){
		if(user.bookTicket(td.getTainNumber(), td.getCoachNumber(), td.getBook())) {
			model.put("SuccessMessage", "Train Ticket Booked sucessfully!");
		}
		else {
			model.put("ErrorMessage", "Train Number is invalid/CoatchNumber Does Not exist/Seat is unavailable!");
		}
		return "UserView";
	}
//	@PostMapping("getavailSeat")
//	public ResponseEntity<Object> getSeats(@RequestBody CoachDetails cd){
//		List<Integer> li = user.getSeat(cd.getTrainNumber(), cd.getNumber());
//		if(li == null) {
//			return new ResponseEntity<Object>("Train Number is invalid/CoatchNumber Does Not exist", HttpStatus.BAD_GATEWAY);
//		}
//		else {
//			return new ResponseEntity<Object>(li, HttpStatus.OK);
//		}
//	}
	@RequestMapping(value = "TrainCoach", method = RequestMethod.POST)
	public String getTrainCoachDetail(CoachDetails name, ModelMap model){
		Coaches co = admin.getCoach(name.getTrainNumber(), name.getNumber());
		if(co == null) {
			model.put("ErrorMessage", "Train Number is invalid/coach does not exist!");
			return "UserView";
		}
		model.put("CoachDetails", co);
		return "UserView"; 
	}
	@RequestMapping(value = "trainDetail", method = RequestMethod.GET)
	public String getTrainDetail(String name,ModelMap model){
		List<Coaches> train = admin.getTrainDetail(name);
		if(train == null) {
			model.put("ErrorMessage","Train Number does Not exist/No Coaches are added yet!");
			return "UserView";
		}
		model.put("AllCoach", train);
		return "UserView";
	}
	@RequestMapping(value = "getTrainDetail", method = RequestMethod.GET)
	public String getTrainDetails(ModelMap model){
		List<Train> allTrain = admin.getAllTrain();
		if(allTrain != null && allTrain.size() != 0) {
	    	model.put("allTrain", allTrain);
		}else {
			model.put("ErrorMessage","No Train Details Is added Till Now");
		}
		return "UserView";
	}
	
	
}
class BookTickets{
	private String TainNumber;
	private int coachNumber;
	private String book;
	public String getTainNumber() {
		return TainNumber;
	}
	public void setTainNumber(String tainNumber) {
		TainNumber = tainNumber;
	}
	public int getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(int coachNumber) {
		this.coachNumber = coachNumber;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	
}
class BookTicket{
	private String TainNumber;
	private int coachNumber;
	private int book;
	public String getTainNumber() {
		return TainNumber;
	}
	public void setTainNumber(String tainNumber) {
		TainNumber = tainNumber;
	}
	public int getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(int coachNumber) {
		this.coachNumber = coachNumber;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	
}
