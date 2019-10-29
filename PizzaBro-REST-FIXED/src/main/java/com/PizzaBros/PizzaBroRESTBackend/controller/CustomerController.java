package com.PizzaBros.PizzaBroRESTBackend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.PizzaBros.PizzaBroRESTBackend.DTO.CustomerDTO;
import com.PizzaBros.PizzaBroRESTBackend.model.Customer;
import com.PizzaBros.PizzaBroRESTBackend.model.CustomerLogin;
//import com.PizzaBros.PizzaBroRESTBackend.model.User;
import com.PizzaBros.PizzaBroRESTBackend.services.CustomerService;
import com.PizzaBros.PizzaBroRESTBackend.services.SecurityService;

@RestController
public class CustomerController {
   private final CustomerService customerService;
   
   @Autowired
	private SecurityService securityService;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private UserDetailsService userDetailsService;
   
   @Autowired
   public CustomerController(CustomerService customerService) {
       this.customerService = customerService;
   }
   
   
   @GetMapping("/api/customer")
   public List<CustomerDTO> getAll(@RequestParam(name = "customerid", required = false) Long customerid,
   @RequestParam(name = "related", required = false) boolean related,
   @RequestParam(name = "metadata", required = false) boolean metadata) {
       return customerService.findAll(customerid);
   }
   
   @GetMapping("/api/customer/{customerId}")
   public CustomerDTO getAll(@PathVariable Long customerId) {
       return customerService.findOne(customerId);
   }
   
   @PostMapping("/api/customer")
   public ResponseEntity<CustomerDTO> create(@RequestBody @Valid CustomerDTO customer) throws URISyntaxException {
       CustomerDTO result = customerService.save(customer);
       return ResponseEntity.created(new URI("/api/customer/" + result.getCustomerId())).body(result);
   }
   
   @PutMapping("/api/customer/{customerid}")
   public ResponseEntity<CustomerDTO> updateJob(@PathVariable Long customerid, @RequestBody @Valid CustomerDTO customer) {
       CustomerDTO result = customerService.update(customer, customerid);
       return ResponseEntity.ok().body(result);
   }
   
   @DeleteMapping("api/customer/{customerId}")
   public ResponseEntity<Void> delete(@PathVariable Long customerId) {
       customerService.delete(customerId);
       return ResponseEntity.ok().build();
   }
   
   //----------------------------------------------//
   @GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new Customer());
		return "registration";
	}

	@PostMapping("/registration")
	public ResponseEntity<Customer> registration(@RequestBody Customer customer) {
		customerService.save(customer);
		return ResponseEntity.ok(customer);
	}

	@PostMapping("/login")
	public ResponseEntity<UserDetails> login(@RequestBody CustomerLogin cust) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(cust.getUsername());
		//System.out.println(userDetails);
		//System.out.println("hello");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, cust.getPassword());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		return ResponseEntity.ok(userDetails);
	}
	
//	@GetMapping("/login12")
//	public String login12(Model model, String error, String logout) {
//		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
//		if (logout != null)
//			model.addAttribute("message", "You have been logged out successfully.");
//		//return customerService.findByUsername(username);
//	//	return customerRepository.findByUsername(username);
//		return "login";
//	}

//	@GetMapping({ "/logout" })
//	public String logout(HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//		if (session != null) {
//			session.invalidate();
//		}
//		return "login";
//	}

   
}
