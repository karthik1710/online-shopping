package org.niit.shoppingbackend.dao;

import java.util.List;

import org.niit.shoppingbackend.dto.Address;
import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.User;


public interface UserDAO {
	
	//add a user
	public boolean addUser(User user);
	
	//get user by email
	User getByEmail(String email);
	
		
	//add an address
	boolean addAddress(Address address);
	
	//get the billing address by user
	Address getBillingAddress(int userId);
	
	//list of shipping addresses
	List<Address> listShippingAddress(int userId);
	
	
	
	
}