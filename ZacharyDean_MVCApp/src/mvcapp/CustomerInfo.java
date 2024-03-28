/*
 * Name:Zachary Dean
 * Student Number: 991353674
 */ 

package mvcapp;

import java.io.Serializable;

public class CustomerInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orders;
	private String address;
	private String city;
	private String postalCode;

    public CustomerInfo() {
        super();                 
        orders = new String(""); 
        address = new String("");
        city = new String(""); 
        postalCode = new String(""); 
        }        

    public void setOrders(String error) {
        this.orders = error;        
        }       

    public String getOrders() {                  
        return orders;    
      }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
