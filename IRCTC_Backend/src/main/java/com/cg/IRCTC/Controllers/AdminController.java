package com.cg.IRCTC.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.IRCTC.entity.Coaches;
import com.cg.IRCTC.services.AdminServices;

@SpringBootApplication
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminServices admin;
	
	@PostMapping("addTrain")
	public ResponseEntity<Object> AddTrain(@RequestBody TrainDetail td){
		boolean temp = admin.addTrain(td.trainName, td.trainNumber);
		if(temp) {
			return new ResponseEntity<Object>("Train Detail Added Successfully", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>("Train Number Already Exist, Please provide some Other Train Number", HttpStatus.CONFLICT);
		}
		
		
	}
	
	@GetMapping("getTrainDetail")
	public ResponseEntity<Object> getTrainDetails(){
		return new ResponseEntity<Object>(admin.getAllTrain(), HttpStatus.OK);
	}
	
	@PostMapping("addCoach")
	public ResponseEntity<Object> addCoach(@RequestBody NewCoach coach){
		if(admin.addCoaches(coach.trainNumber, coach.coachName)) {
			return new ResponseEntity<Object>("Coach Detail Added Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Train Number does Not exist/coach Name must be in {A/C Sleeper, Non A/C Sleeper, Seater} ", HttpStatus.BAD_REQUEST) ;
	}

	@GetMapping("trainDetail")
	public ResponseEntity<Object> getTrainDetail(@RequestParam String name){
		return new ResponseEntity<Object>(admin.getTrainDetail(name), HttpStatus.OK);
	}
	
	@PostMapping("TrainCoach")
	public ResponseEntity<Object> getTrainCoachDetail(@RequestBody CoachDetails name){
		Coaches co = admin.getCoach(name.trainNumber, name.number);
		if(co == null) {
			return new ResponseEntity<Object>("Train Number is invalid/coach does not exist!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(co, HttpStatus.OK); 
	}
	
	@DeleteMapping("deleteCoach")
	public ResponseEntity<Object> DeleteTrainCoach(@RequestBody CoachDetails name){
		if(admin.removeCoaches(name.trainNumber, name.number)) {
			return new ResponseEntity<Object>("Mentioned Train Coach Was removed from the Train!", HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>("Train Number is invalid/coach does not exist!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("deleteTrain")
	public ResponseEntity<Object> DeleteTrain(@RequestParam String trainNumber){
			if(admin.removeTrain(trainNumber)) {
				return new ResponseEntity<Object>("Mentioned Train Number Was removed!", HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>("No Train Exist with the trainNumber "+trainNumber, HttpStatus.BAD_REQUEST);
			}
	}
}
class TrainDetail{
	public String trainName;
	public String trainNumber;
}
class NewCoach{
	public String trainNumber;
	public String coachName;
}
class CoachDetails{
	public String trainNumber;
	public int number;
}
