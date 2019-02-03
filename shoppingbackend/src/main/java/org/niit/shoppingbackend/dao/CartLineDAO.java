package org.niit.shoppingbackend.dao;

import java.util.List;

import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.CartLine;

public interface CartLineDAO {

	// common CRUD operations

	public CartLine get(int id);

	public boolean add(CartLine cartLine);

	public boolean delete(CartLine cartLine);

	public boolean update(CartLine cartLine);

	public List<CartLine> list(int cartId);

	// business methods

	public List<CartLine> listAvailable(int cartId);

	public CartLine getByCartAndProduct(int cartId, int productId);

	// update a user Cart
	boolean updateCart(Cart cart);

}