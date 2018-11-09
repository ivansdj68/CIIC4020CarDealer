package edu.uprm.cse.datastructures.cardealer;

import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.model.AppointmentComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/appointment")
public class AppointmentManager {

	private static CircularSortedDoublyLinkedList<Appointment> appointmentList 
		= new CircularSortedDoublyLinkedList<Appointment>(new AppointmentComparator());

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment[] getAllAppointments() {
		return appointmentList.toArray();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment getAppointment(@PathParam("id") long id) {
		Optional<Appointment> match = Optional.empty();
		for(int i=0; i<appointmentList.size(); i++) {
			if(appointmentList.get(i).getCarUnitId()==id) {
				match = Optional.of(appointmentList.get(i));
			}
		}
		if(match.isPresent()) {
			return match.get();
		}
		else {
			throw new NotFoundException();
		}
	}

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCarUnit(Appointment appointment) {
		appointmentList.add(appointment);
		return Response.status(201).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAppointment(Appointment appointment) {

		Optional<Appointment> match = Optional.empty();
		for (int i = 0; i < appointmentList.size(); i++) {
			if (appointmentList.get(i).getAppointmentId() == appointment.getAppointmentId()) {
				match = Optional.of(appointmentList.get(i));
			}
		}
		if (match.isPresent()) {
			appointmentList.remove(match.get());
			appointmentList.add(appointment);
			return Response.status(Response.Status.OK).build();
		} 
		else return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deleteAppointment(@PathParam("id") long id)
	{
		for(int i=0;i<appointmentList.size();i++){
			if(appointmentList.get(i).getAppointmentId()==id){
				appointmentList.remove(i);
				return Response.status(200).build();}	    	
		}
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}

}
