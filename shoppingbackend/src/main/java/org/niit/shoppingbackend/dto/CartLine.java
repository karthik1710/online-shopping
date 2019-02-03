package org.niit.shoppingbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_line")
public class CartLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * private fields of cartline
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "cart_id")
	private int cartId;
	
	@Column(name = "product_count")
	private int productCount;
	
	private double total;
	
	@Column(name = "buying_price" )
	private double buyingPrice;
	
	@Column(name = "is_available")
	private boolean available;
	
	@OneToOne
	private Product product;

	
	/*
	 * getters and setters
	 */
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	/*
	 * toString method
	 */
	
	@Override
	public String toString() {
		return "CartLine [id=" + id + ", cartId=" + cartId + ", productCount=" + productCount + ", total=" + total
				+ ", buyingPrice=" + buyingPrice + ", available=" + available + ", product=" + product + "]";
	}

	
	
	
}