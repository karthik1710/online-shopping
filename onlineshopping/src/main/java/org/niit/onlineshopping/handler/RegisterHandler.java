package org.niit.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import org.niit.shoppingbackend.dao.UserDAO;
import org.niit.shoppingbackend.dto.Address;
import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.User;
import org.niit.onlineshopping.model.RegisterModel;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init()
	{
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user)
	{
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing)
	{
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model)
	{
		String transitionValue = "success";
		
		/*
		 * fetch and save user
		 */
		//fetch the user
		User user = model.getUser();
		
		if(user.getRole().equals("USER")) // to create a cart for the role : user
		{
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			
		}
		
		//encode the password before saving the user
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//save the user
		userDAO.addUser(user);
		
		/*
		 * fetch and save address
		 */
		
		//fetch the address
		Address billing = model.getBilling();
		
		billing.setUser(user);
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
					
		
		
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext error)
	{
		String transitionValue = "success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword())))
				{
					error.addMessage(new MessageBuilder()
							.error()
							.source("confirmPassword")
							.defaultText("Passowrd does not match with confirm-passowrd!")
							.build()
							);
					transitionValue = "failure";
				}
		
		if(userDAO.getByEmail(user.getEmail()) != null)
		{
			{
				error.addMessage(new MessageBuilder()
						.error()
						.source("email")
						.defaultText("Email id already exists!")
						.build()
						);
				transitionValue = "failure";
			}
		}
		
		return transitionValue;
	}
	
}


