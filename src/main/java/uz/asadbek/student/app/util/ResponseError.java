/******************************************************************************
 * Copyright (c)  2/14/24, 10:46 PM                                           *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/

package uz.asadbek.student.app.util;

public class ResponseError {
	private String message;
	private long timestamp;


	public ResponseError(String message, long timestamp) {
		this.message = message;
		this.timestamp = timestamp;
	}

	public ResponseError() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
