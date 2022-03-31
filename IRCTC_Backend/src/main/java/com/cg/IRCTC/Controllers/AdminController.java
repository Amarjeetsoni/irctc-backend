package com.cg.IRCTC.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;

import com.cg.IRCTC.entity.Coaches;
import com.cg.IRCTC.entity.Train;
import com.cg.IRCTC.services.AdminServices;

@SpringBootApplication
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminServices admin;
	
	@RequestMapping(value = "addTrain", method = RequestMethod.POST)
	public String AddTrain(TrainDetail td,ModelMap model){
		boolean temp = admin.addTrain(td.getTrainName(), td.getTrainNumber());
		if(temp) {
			model.put("SuccessMessage","Train Detail Added Successfully");
			return "AdminView";
		}
		else {
			model.put("ErrorMessage","Train Number Already Exist, Please provide some Other Train Number");
			return "AdminView";
		}
	}
	
//	@GetMapping("getTrainDetail")
	@RequestMapping(value = "getTrainDetail", method = RequestMethod.GET)
	public String getTrainDetails(ModelMap model){
		List<Train> allTrain = admin.getAllTrain();
		if(allTrain != null && allTrain.size() != 0) {
	    	model.put("allTrain", allTrain);
		}else {
			model.put("ErrorMessage","No Train Details Is added Till Now");
		}
		return "AdminView";
	}
	
//	@PostMapping("addCoach")
	@RequestMapping(value = "addCoach", method = RequestMethod.POST)
	public String addCoach(NewCoach coach,ModelMap model){
		if(admin.addCoaches(coach.getTrainNumber(), coach.getCoachName())) {
			model.put("SuccessMessage", "Coach Detail Added Successfully");
			return "AdminView";
		}
		model.put("ErrorMessage","Train Number does Not exist/coach Name must be in {A/C Sleeper, Non A/C Sleeper, Seater} ");
		return "AdminView";	
	}

//	@GetMapping("trainDetail")
	@RequestMapping(value = "trainDetail", method = RequestMethod.GET)
	public String getTrainDetail(String name,ModelMap model){
		List<Coaches> train = admin.getTrainDetail(name);
		if(train == null) {
			model.put("ErrorMessage","Train Number does Not exist/No Coaches are added yet!");
			return "AdminView";
		}
		model.put("AllCoach", train);
		return "AdminView";
	}
	
//	@PostMapping("TrainCoach")
	@RequestMapping(value = "TrainCoach", method = RequestMethod.POST)
	public String getTrainCoachDetail(CoachDetails name, ModelMap model){
		Coaches co = admin.getCoach(name.getTrainNumber(), name.getNumber());
		if(co == null) {
			model.put("ErrorMessage", "Train Number is invalid/coach does not exist!");
			return "AdminView";
		}
		model.put("CoachDetails", co);
		return "AdminView"; 
	}
	
//	@DeleteMapping("deleteCoach")
	@RequestMapping(value = "deleteCoach", method = RequestMethod.GET)
	public String DeleteTrainCoach(CoachDetails name, ModelMap model){
		if(admin.removeCoaches(name.getTrainNumber(), name.getNumber())) {
			model.put("SuccessMessage", "Mentioned Train Coach Was removed from the Train!");
			return "AdminView";
		}else {
			model.put("ErrorMessage", "Train Number is invalid/coach does not exist!");
			return "AdminView";
		}
	}
	
//	@DeleteMapping("deleteTrain")
	@RequestMapping(value = "deleteTrain", method = RequestMethod.GET)
	public String DeleteTrain(String trainNumber, ModelMap model){
			if(admin.removeTrain(trainNumber)) {
				model.put("SuccessMessage", "Train Number "+trainNumber +"Was removed!");
				return "AdminView";
			}else {
				model.put("ErrorMessage", "No Train Exist with the trainNumber "+trainNumber);
				return "AdminView";
			}
	}
}
class TrainDetail{
	private String trainName;
	private String trainNumber;
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	
}
class NewCoach{
	private String trainNumber;
	private String coachName;
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	
}
class CoachDetails{
	private String trainNumber;
	private int number;
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
