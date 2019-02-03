package org.niit.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.niit.shoppingbackend.dao.CategoryDAO;
import org.niit.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	@Autowired
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*
	 * @Test public void testAddCategory() { category = new Category();
	 * category.setName("Mobile");
	 * category.setDescription("Some description for mobile");
	 * category.setImageURL("CAT_2.png");
	 * assertEquals("successfully added a new category",true,categoryDAO.add(
	 * category));
	 * 
	 * }
	 */

	/*
	 * @Test public void testGetCategory() {
	 * assertEquals("successfully retrieved a single category from db","Mobile",
	 * categoryDAO.get(2).getName());
	 * 
	 * }
	 */

	/*
	 * @Test public void testUpdateCategory() { category= categoryDAO.get(2);
	 * category.setName("TV");
	 * 
	 * assertEquals("successfully updated a category",true,categoryDAO.update(
	 * category));
	 * 
	 * }
	 */

	/*
	 * @Test public void testDeleteCategory() { category= categoryDAO.get(2);
	 * assertEquals("successfully deleted a category",true,categoryDAO.delete(
	 * category));
	 * 
	 * 
	 * }
	 */
	/*
	 * @Test public void testListActiveCategories() {
	 * assertEquals("successfully retrieved the active categories",1,categoryDAO.
	 * list().size()); }
	 */

	/*
	 * Testing all the CRUD operations on Category
	 */
	@Test
	public void testCRUDCategory() {

		// adding 3 Categories to Category table

		category = new Category();
		category.setName("Televison");
		category.setDescription("Some description for television");
		category.setImageURL("CAT_1.png");

		assertEquals("something went wrong when adding a new category", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Mobile");
		category.setDescription("Some description for mobile");
		category.setImageURL("CAT_2.png");

		assertEquals("something went wrong when adding a new category", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Laptop");
		category.setDescription("Some description for laptop");
		category.setImageURL("CAT_3.png");

		assertEquals("something went wrong when adding a new category", true, categoryDAO.add(category));

		// Updating a Category

		category = categoryDAO.get(1);
		category.setName("TV");

		assertEquals("something went wrong when updating a category", true, categoryDAO.update(category));

		// Deleting a Category

		category = categoryDAO.get(3);
		assertEquals("something went wrong when deleting a category", true, categoryDAO.delete(category));

		// Retrieving all active categories

		assertEquals("something went wrong when retrieving the active categories", 2, categoryDAO.list().size());

	}
}