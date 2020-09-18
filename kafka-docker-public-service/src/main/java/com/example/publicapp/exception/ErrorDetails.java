package com.example.publicapp.exception;

import lombok.*;

import java.util.Date;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	private int status;
	private String error;
	private String path;

	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}


}
