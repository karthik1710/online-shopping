package org.niit.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.niit.shoppingbackend.dao.ProductDAO;
import org.niit.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;

	/*
	 * for retrieving all active products
	 */
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllActiveProducts() {

		return productDAO.listActiveProducts();
	}

	/*
	 * for retrieving products for a specific category
	 */
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getActiveProductsByCategory(@PathVariable("id") int id) {
		return productDAO.listActiveProductsByCategory(id);
	}
	
	/*
	 * for retrieving all products
	 */
	@RequestMapping("admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin() {

		return productDAO.list();
	}

}
