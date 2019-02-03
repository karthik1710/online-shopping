package org.niit.shoppingbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address implements Serializable{

	
	private static final long serialVersionUID = 1L;

	/*
	 * private fields of address
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message="Please enter address line one!")
	@Column(name="address_line_one")
	private String addressLineOne;
	
	@NotEmpty(message="Please enter address line two!")
	@Column(name="address_line_two")
	private String addressLineTwo;
	
	@NotEmpty(message="Please enter the city name!")
	private String city;
	
	@NotEmpty(message="Please enter the state name!")
	private String state;
	
	@NotEmpty(message="Please enter the country name!")
	private String country;
	
	@NotEmpty(message="Please enter the postal code!")
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="is_billing")
	private boolean billing;
	
	@Column(name="is_shipping")
	private boolean shipping;
	
	@ManyToOne
	@JoinColumn(name="uid")
	private User user;
	
	

	/*
	 * getters and setters
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public boolean isBilling() {
		return billing;
	}

	public void setBilling(boolean billing) {
		this.billing = billing;
	}

	public boolean isShipping() {
		return shipping;
	}

	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	/*
	 * toString method
	 */
	
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLineOne=" + addressLineOne + ", addressLineTwo=" + addressLineTwo
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode
				+ ", billing=" + billing + ", shipping=" + shipping + ", user=" + user + "]";
	}
	
	
}