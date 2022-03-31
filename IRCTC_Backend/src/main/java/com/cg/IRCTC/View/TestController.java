package com.cg.IRCTC.View;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;

import com.cg.IRCTC.entity.UsersDetails;
import com.cg.IRCTC.services.AuthonticateService;


@Controller
//@SpringBootApplication
//@RequestMapping("/")
public class TestController {

	@Autowired
	AuthonticateService services;
	
	
	
	@RequestMapping(value={"/", "loginPage"}, method = RequestMethod.GET)
	public String firstPage() {
		return "login";
	}
	
	@GetMapping(value="logout")
	public String logOut() {
		return "login";
	}
	
	@GetMapping(value="test")
	public ResponseEntity<Object> testFunction() {
		return new ResponseEntity<Object>("Request is handling properly", HttpStatus.OK);
	}
	@PostMapping("setpassword")
	public ResponseEntity<Object> addUser(@RequestBody UsersDetails ud){
		if(ud.getRole().equals("admin")) {
			services.setPasswordforAdmin(ud.getUserName(), ud.getPassword());
			return new ResponseEntity<Object>("Password for the Admin has been Setted!", HttpStatus.OK);
		}else {
			services.setPasswordforUser(ud.getUserName(), ud.getPassword());
			return new ResponseEntity<Object>("Password for the User has been Setted!", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginUser(Details detail, ModelMap model){
		System.out.println(detail.toString());
		if(detail.getRole().equals("admin")) {
			if(services.login(detail.getRole(), detail.getUsername(), detail.getPassword())) {
				return "AdminView";
			}else {
				model.put("ErrorMessage","Please provide correct admin Credentials!");
				return "login";
			}
		}else {
			if(services.login(detail.getRole(), detail.getUsername(), detail.getPassword())) {
				return "UserView";
			}else {
				model.put("ErrorMessage","Please provide correct user Credentials!");
				return "login";
			}
		}
		
	}
	
//	@RequestMapping(value = "logincheck", method = RequestMethod.POST)
//	public ResponseEntity<Object> login(Credentials credentials){
//		System.out.println(credentials.getUsername() + " " + credentials.getPassword());
//		return new ResponseEntity<Object>("value printed", HttpStatus.OK);
//	}
}

//class Credentials{
//	private String username;
//    private String password;
//    
//}

class Details{
	private String role;
	private String username;
	private String password;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Details [role=" + role + ", username=" + username + ", password=" + password + "]";
	}
	
}
