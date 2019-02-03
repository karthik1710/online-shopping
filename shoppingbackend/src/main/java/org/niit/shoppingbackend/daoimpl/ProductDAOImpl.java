package org.niit.shoppingbackend.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.niit.shoppingbackend.dao.ProductDAO;
import org.niit.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Getting single product based on id
	 */
	@Override
	public Product get(int id) {

		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
	}

	/*
	 * adding a product
	 */
	@Override
	public boolean add(Product product) {
		try {

			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
	}

	/*
	 * updates a particular product
	 */
	@Override
	public boolean update(Product product) {

		try {

			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * method to delete a product
	 */
	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * to retrieve all products from the table
	 */
	@Override
	public List<Product> list() {
		String selectAllProduct = "from Product";
		Query query = sessionFactory.getCurrentSession().createQuery(selectAllProduct);
		return query.getResultList();
	}

	/*
	 * to retrieve all active products from the table
	 */
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProduct = "from Product where active=:active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProduct);
		query.setParameter("active", true);
		return query.getResultList();
	}

	/*
	 * to retrieve all active products from the table by category
	 */
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductByCategory = "from Product where active=:active and categoryId = :categoryId";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveProductByCategory);
		query.setParameter("active", true);
		query.setParameter("categoryId", categoryId);
		return query.getResultList();
	}

	/*
	 * to retrieve all latest 'n' active products from the table
	 */
	@Override
	public List<Product> latestActiveProducts(int count) {
		String selectLatestActiveProducts = "from Product where active=:active order by id";
		Query query = sessionFactory.getCurrentSession().createQuery(selectLatestActiveProducts);
		query.setParameter("active", true);
		query.setFirstResult(0);
		query.setMaxResults(count);
		return query.getResultList();
	}

}