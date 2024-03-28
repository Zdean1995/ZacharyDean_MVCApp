/**
 * 
 */
package mvcapp;

import java.io.Serializable;

public class CustomerManage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String firstName;
	private String lastName;
	private String userID;

    public CustomerManage() {
        super();                 
        userName = new String("");
        firstName = new String("");
        lastName = new String("");
        userID = new String("");
        }        

    public void setUserName(String error) {
        this.userName = error;        
        }       

    public String getUserName() {                  
        return userName;    
      }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
