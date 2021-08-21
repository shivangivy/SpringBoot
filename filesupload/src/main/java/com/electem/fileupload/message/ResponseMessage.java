/**
 * 
 */
package com.electem.fileupload.message;

/**
 * The ResponseMessage is for message to client that we’re gonna use in 
 * Rest Controller and Exception Handler.
 *
 */
public class ResponseMessage {
	
	private String message;
	
	/**
	 * Constructor
	 */
	public ResponseMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter and Setter
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
