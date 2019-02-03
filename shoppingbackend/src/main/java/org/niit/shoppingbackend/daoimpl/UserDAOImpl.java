package org.niit.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.niit.shoppingbackend.dao.UserDAO;
import org.niit.shoppingbackend.dto.Address;
import org.niit.shoppingbackend.dto.Cart;
import org.niit.shoppingbackend.dto.User;


@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override  
	public boolean addUser(User user) {
		
		try{
			
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User where email = :email";
		
		try {
			
			return sessionFactory.getCurrentSession()
							.createQuery(selectQuery, User.class)
								.setParameter("email", email)
									.getSingleResult(); //getSingleResult throws an exception if there are multiple rows returned
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}

	

	@Override
	public boolean addAddress(Address address) {
		
		try {
			
			sessionFactory.getCurrentSession().persist(address);
			return true;
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address where uid=:uid and billing=:billing";
		try {
			
			return sessionFactory.getCurrentSession()
				.createQuery(selectQuery, Address.class)
					.setParameter("uid", userId)
					.setParameter("billing",true)
						.getSingleResult();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Address> listShippingAddress(int userId) {
		
		String selectQuery = "FROM Address where uid=:uid and shipping=:shipping";
		try {
			
			return sessionFactory.getCurrentSession()
					.createQuery(selectQuery, Address.class)
						.setParameter("uid", userId)
						.setParameter("shipping", true)
							.getResultList();
				
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
		
	}
}