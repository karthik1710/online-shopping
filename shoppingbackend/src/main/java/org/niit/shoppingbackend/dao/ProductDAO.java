package org.niit.shoppingbackend.dao;

import java.util.List;

import org.niit.shoppingbackend.dto.Product;

public interface ProductDAO {

	Product get(int id);

	boolean add(Product product);

	boolean update(Product product);

	boolean delete(Product product);

	List<Product> list();
	
	/*
	 *  business methods
	 */

	List<Product> listActiveProducts();

	List<Product> listActiveProductsByCategory(int categoryId);

	List<Product> latestActiveProducts(int count);

}