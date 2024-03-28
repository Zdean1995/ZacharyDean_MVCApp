/*
 * Name:Zachary Dean
 * Student Number: 991353674
 */
package mvcapp;

import java.io.Serializable;

public class ErrorMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

    public ErrorMessage() {
        super();                 
        message = new String("noerror");     
        }        

    public void setMessage(String error) {
        this.message = error;        
        }       

    public String getMessage() {                  
        return message;    
      }

}
