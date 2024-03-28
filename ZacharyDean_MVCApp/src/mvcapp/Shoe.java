/*
 * Name:Zachary Dean
 * Student Number: 991353674
 */
package mvcapp;

import java.io.Serializable;

public class Shoe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shoeID;
	private String shoeName;
	private String category;
	private String shoeSize;
	private String price;
	private String shoeInfo;

    public Shoe() {
        super();
        shoeID = new String("");
        shoeName = new String("");
        category = new String("");
        shoeSize = new String("");
        price = new String("");                 
        shoeInfo = new String("");
        }        

    public void setShoeInfo(String error) {
        this.shoeInfo = error;        
        }       

    public String getShoeInfo() {                  
        return shoeInfo;    
      }

	public String getShoeID() {
		return shoeID;
	}

	public void setShoeID(String shoeID) {
		this.shoeID = shoeID;
	}

	public String getShoeName() {
		return shoeName;
	}

	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
