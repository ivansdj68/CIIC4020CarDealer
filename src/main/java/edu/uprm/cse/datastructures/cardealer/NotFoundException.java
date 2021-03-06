package edu.uprm.cse.datastructures.cardealer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super(Response.status(Response.Status.NOT_FOUND).build());
	}

	public NotFoundException(String message) {
		super(Response.status(Response.Status.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
	}

	public NotFoundException(JsonError jse) {
		super(Response.status(Response.Status.NOT_FOUND).entity(jse).type(MediaType.APPLICATION_JSON).build());
	}
}