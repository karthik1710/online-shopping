package org.niit.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.niit.shoppingbackend.dao.CartLineDAO;
import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.CartLine;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CartLine get(int id) {

		return sessionFactory.getCurrentSession().get(CartLine.class, id);

	}

	@Override
	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;

		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;

		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;

		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		String selectQuery = "FROM CartLine where cartId=:cartId";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, CartLine.class)
					.setParameter("cartId", cartId).getResultList();

		}

		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String selectQuery = "FROM CartLine where cartId=:cartId AND available= :available";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, CartLine.class)
					.setParameter("cartId", cartId).setParameter("available", true).getResultList();

		}

		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String selectQuery = "FROM CartLine where cartId=:cartId AND product.id = :productId";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, CartLine.class)
					.setParameter("cartId", cartId).setParameter("productId", productId).getSingleResult();

		}

		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {

		try {

			sessionFactory.getCurrentSession().update(cart);
			return true;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}