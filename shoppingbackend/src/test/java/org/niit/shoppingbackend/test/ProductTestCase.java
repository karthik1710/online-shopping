package org.niit.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.niit.shoppingbackend.dao.ProductDAO;
import org.niit.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	@Autowired
	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	@Test
	public void testCRUDProduct() {
	/*	product = new Product();
		product.setName("Moto g3");
		product.setBrand("Motorola");
		product.setDescription("A smart phone by Motorola");
		product.setQuantity(3);
		product.setUnitPrice(10000.00);
		product.setSupplierId(3);
		product.setCategoryId(3);
		product.setActive(false);
		product.setViews(0);
		product.setPurchases(0);
		// adding a product
		assertEquals("something went wrong when adding a new product", true, productDAO.add(product));
*/
		// Updating a Product

		product = productDAO.get(34);
		product.setActive(true);

		assertEquals("something went wrong when updating a product", true, productDAO.update(product));

/*		// Deleting a Category
		product = productDAO.get(4);
		assertEquals("something went wrong when deleting a product", true, productDAO.delete(product));
		// Retrieving all products
		assertEquals("something went wrong when rerieving all products", 6, productDAO.list().size());
		// Retrieving all active products
		assertEquals("something went wrong when rerieving all active products", 4,
				productDAO.listActiveProducts().size());
		// Retrieving all active products by category
		assertEquals("something went wrong when rerieving all active products", 3,
				productDAO.listActiveProductsByCategory(3).size());
		// Retrieving 3 latest active products by category
		assertEquals("something went wrong when rerieving latest 3 active products", 3,
				productDAO.latestActiveProducts(3).size());
*/
	}

}