package org.niit.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.niit.shoppingbackend.dao.CartLineDAO;
import org.niit.shoppingbackend.dao.ProductDAO;
import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.CartLine;
import org.niit.shoppingbackend.dto.Product;
import org.niit.onlineshopping.model.UserModel;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private HttpSession session;

	// returns the cart of user who has logged in
	public Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	// returns the entire cart lines
	public List<CartLine> getCartLines() {
		return cartLineDAO.list(this.getCart().getId());
	}

	public String manageCartLine(int cartLineId, int count) {

		// fetch the cartLine

		CartLine cartLine = cartLineDAO.get(cartLineId);

		if (cartLine == null) {
			return "result=error";
		}

		else {

			// update the cartline
			Product product = cartLine.getProduct();

			double oldTotal = cartLine.getTotal();

			if (product.getQuantity() < count) {

				return "result=unavailable";
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			cartLineDAO.update(cartLine);

			// update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDAO.updateCart(cart);

			return "result=updated";
		}

	}

	public String deleteCartLine(int cartLineId) {

		// fetch the cartLine
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if (cartLine == null) {

			return "result=error";

		} else {

			Cart cart = this.getCart();
			cart.setCartLines(cart.getCartLines() - 1);
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			//delete the cartLine
			cartLineDAO.delete(cartLine);
			
			return "result=deleted";

		}
	}

	public String addCartLine(int productId) {

		String response = null;
		
		Cart cart = this.getCart();
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null)
		{
			//add a new CartLine
			cartLine = new CartLine();
			
			//fetch the product
			
			Product product = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			//persist the cartline
			cartLineDAO.add(cartLine);
			
			//update the cart
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			response = "result=added";
		}
		
		else {
			
			//check if the cartLine has reached its maximum limit
			
			if(cartLine.getProductCount() < 3)
			{
				//update the product count for that cartLine
				
				response = this.manageCartLine(cartLine.getId(),cartLine.getProductCount()+ 1);				
				
			}
			
			else {
				
				response ="result=maximum";
			}
		}
		
		
		return response;
	}

}
