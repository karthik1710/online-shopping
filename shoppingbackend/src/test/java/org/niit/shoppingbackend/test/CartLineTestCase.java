package org.niit.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.niit.shoppingbackend.dao.CartLineDAO;
import org.niit.shoppingbackend.dao.ProductDAO;
import org.niit.shoppingbackend.dao.UserDAO;
import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.CartLine;
import org.niit.shoppingbackend.dto.Product;
import org.niit.shoppingbackend.dto.User;

public class CartLineTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	private static CartLineDAO cartLineDAO;
	
	private User user = null;
	private Cart cart = null;
	private Product product = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
		
	}
	
	@Test
	public void testAddNewCartLine()
	{
		//1. get the user by email id
		
		user = userDAO.getByEmail("abc@gmail.com");
		
		//2. get the cart of the user
		
		cart = user.getCart();
		
		//3. get the product to be added
		
		product = productDAO.get(1);
		
		//4. create the new cartline
		
		cartLine = new CartLine();
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		cartLine.setBuyingPrice(product.getUnitPrice());
		
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		
		cartLine.setAvailable(true);
		
		assertEquals("failed to add the cartline", true, cartLineDAO.add(cartLine));
		
		
		//update the cart
		
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		assertEquals("failed to update the cartline", true, cartLineDAO.updateCart(cart));
	}
	

}