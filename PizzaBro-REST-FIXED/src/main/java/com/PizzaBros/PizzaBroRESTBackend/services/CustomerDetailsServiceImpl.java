package com.PizzaBros.PizzaBroRESTBackend.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PizzaBros.PizzaBroRESTBackend.repository.CustomerRepository;
import com.PizzaBros.PizzaBroRESTBackend.model.Customer;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Customer customer = customerRepository.findByUsername(username);
//        if (customer == null) throw new UsernameNotFoundException(userName);
////
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (User user : user.findByUsername()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(user.getName()));
//        }
    	
    	Customer cust= customerRepository.findByUsername(username);
    	if(cust == null)
    		throw new UsernameNotFoundException("Customer not found with username "+ username);

      return new org.springframework.security.core.userdetails.User( customer.getUsername(), customer.getPassword(), new ArrayList<>());
    }
}