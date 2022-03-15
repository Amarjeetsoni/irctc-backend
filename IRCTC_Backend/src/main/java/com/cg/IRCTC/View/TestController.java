package com.cg.IRCTC.View;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("/Test")
public class TestController {

	@GetMapping(value="test")
	public String testFunction() {
		return "Request is handling properly";
	}
}
