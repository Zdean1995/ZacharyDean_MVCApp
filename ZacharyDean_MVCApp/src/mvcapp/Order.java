/*
 * Name:Zachary Dean
 * Student Number: 991353674
 */ 
package mvcapp;

import java.io.Serializable;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderID;
	private String quantity;
	private String shoeSize;
	private String status;

    public Order() {
        super();                 
        orderID = new String("");
        quantity = new String("");
        shoeSize = new String("");
        status = new String("");
        
        }

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
}
