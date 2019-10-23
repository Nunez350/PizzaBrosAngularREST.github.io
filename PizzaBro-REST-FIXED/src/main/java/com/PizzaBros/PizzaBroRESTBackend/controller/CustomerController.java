package com.PizzaBros.PizzaBroRESTBackend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.PizzaBros.PizzaBroRESTBackend.DTO.CustomerDTO;
import com.PizzaBros.PizzaBroRESTBackend.services.CustomerService;

@RestController
public class CustomerController {
   private final CustomerService customerService;
   
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
}
