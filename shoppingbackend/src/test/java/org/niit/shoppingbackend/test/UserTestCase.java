package org.niit.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.niit.shoppingbackend.dao.UserDAO;
import org.niit.shoppingbackend.dto.Address;
import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	
	
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	/*
	@Test
	public void testAddUser()
	{
		user = new User();
		user.setFirstName("Sanjana");
		user.setLastName("Sreekumar");
		user.setEmail("a.s.sanjana28@gmail.com");
		user.setContactNumber("9790802750");
		user.setRole("USER");
		user.setPassword("sanju");
		user.setEnabled(true);
		
		
		
		if(user.getRole().equals("USER"))
		{
			// create a cart for this user
			
			cart = new Cart();
			cart.setUser(user);
		}
		
			// attach this cart with the user
		
			user.setCart(cart);
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
	}
	
	@Test
	public void testUpdateCart()
	{
		// fetch the user by its email
		user = userDAO.getByEmail("a.s.sanjana28@gmail.com");
		
		//get the cart of the user
		cart = user.getCart();
		
		cart.setCartLines(2);
		cart.setGrandTotal(5555);
		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));
		
	}*/
	
	@Test
	public void testAddAddress()
	{
		/*
		//add a user
		user = new User();
		user.setFirstName("Sanjana");
		user.setLastName("Sreekumar");
		user.setEmail("a.s.sanjana28@gmail.com");
		user.setContactNumber("9790802750");
		user.setRole("USER");
		user.setPassword("sanju");
		user.setEnabled(true);
		
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		//add a billing address
		address = new Address();
		address.setAddressLineOne("abc def");
		address.setAddressLineTwo("efg hij");
		address.setCity("bangalore");
		address.setState("karnataka");
		address.setCountry("India");
		address.setPostalCode("55555555");
		address.setBilling(true);
		
		//attach user to address
		address.setUser(user);
		
		assertEquals("Failed to add billing address",true,userDAO.addAddress(address));
		
		//add  shipping address 1
		address = new Address();
		address.setAddressLineOne("eee def");
		address.setAddressLineTwo("ehhh hij");
		address.setCity("chennai");
		address.setState("tamil nadu");
		address.setCountry("India");
		address.setPostalCode("557855555");
		address.setShipping(true);
		
		//attach user to address
		address.setUser(user);
		
		assertEquals("Failed to add shipping address 1",true,userDAO.addAddress(address));
		
		address = new Address();
		address.setAddressLineOne("sss def");
		address.setAddressLineTwo("120hhh hij");
		address.setCity("thrissur");
		address.setState("kerala");
		address.setCountry("India");
		address.setPostalCode("557855555");
		address.setShipping(true);
		
		//attach user to address
		address.setUser(user);
		
		assertEquals("Failed to add shipping address 2",true,userDAO.addAddress(address));
		*/
		
		
		assertEquals("Failed to add shipping address 2","bangalore",userDAO.getBillingAddress(1).getCity());
		
		assertEquals("Failed to add shipping address 2",2,userDAO.listShippingAddress(1).size());
				
		
	}
	
}
