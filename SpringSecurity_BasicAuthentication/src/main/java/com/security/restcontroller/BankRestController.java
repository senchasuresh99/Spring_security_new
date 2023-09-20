package com.security.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankRestController {

	@GetMapping("/")
	public String welcomePage() {
		return "Welcome to ICIC Bank";
	}
	
	@GetMapping("/transfer")
	public String fundTransfer() {
		return "Fund Transfer Initated";
	}
	
	@GetMapping("/balence")
	public String checkBalance() {
		return "Balance Amount is :: 10000INR";
	}
	
	@GetMapping("/about")
	public String aboutUs() {
		return "ICIC bank is managed by Indian Centeal Govt";
	}
}
