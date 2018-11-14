package edu.uprm.cse.datastructures.cardealer;

import java.util.Iterator;
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
import edu.uprm.cse.datastructures.cardealer.util.LPLAppointmentArray;
import edu.uprm.cse.datastructures.cardealer.util.LinkedPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.Position;


@Path("/appointment")
public class AppointmentManager {

	LinkedPositionalList<Appointment>[] appointmentList= LPLAppointmentArray.getInstance().getList();

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment[] getAllAppointments() {
		for(int j=0; j<appointmentList.length; j++) {
			Iterator<Appointment> iter= appointmentList[j].iterator();
			Appointment[] c=new Appointment[appointmentList[j].size()];
			int i=0;
			while(iter.hasNext()) {
				c[i++]=(Appointment)iter.next();
				return c;
			}
		}
		return null;
	}

	@GET
	@Path("/day/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public LinkedPositionalList<Appointment> getAllAppointmentsForDay(@PathParam("d") int d) {

		for(int i=0; i<appointmentList.length; i++){		
			if(i==d){
				return appointmentList[i];
			}
		}

		return null;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment getAppointment(@PathParam("id") long id) {

		Optional<Appointment> match = Optional.empty();

		for(int j=0; j<appointmentList.length; j++) {
			for(int i=0; i<appointmentList[i].size(); i++) {
				Appointment ap = appointmentList[i].positions().iterator().next().getElement();
				if(ap.getAppointmentId()==id) {
					match = Optional.of(ap);
				}
			}
		}

		if(match.isPresent()) {
			return match.get();
		}
		else {
			throw new NotFoundException();
		}
	}
	
	//Both add methods work when adding the appointment a second time in the terminal
	@POST
	@Path("add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointmentOnDay(Appointment appointment, @PathParam("day") String day){
		String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		for(int i=0; i<week.length; i++) {
			if(day.equals(week[i])){	
				appointmentList[i].addLast(appointment);			
				return Response.status(201).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

		@POST
	@Path("add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointmentOnDay(Appointment appointment, @PathParam("day") int day){

		if(day>=0&& day<5){	
			appointmentList[day].addLast(appointment);			
			return Response.status(201).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@SuppressWarnings("unchecked")
	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAppointment(Appointment appointment) {

		Optional<Appointment> match = Optional.empty();
		int i = 0;
		for(LinkedPositionalList<Appointment> day: appointmentList) {
			Appointment ap = day.iterator().next();
			if(day!=appointmentList[0]) {
				i++;
			}
			while(ap.getAppointmentId()!=appointment.getAppointmentId()) {
				ap = day.iterator().next();
			}

			match = Optional.of(ap);

		}
		if (match.isPresent()) {
			appointmentList[i].remove((Position<Appointment>)match.get());
			appointmentList[i].addLast(appointment);
			return Response.status(Response.Status.OK).build();
		} 
		else return Response.status(Response.Status.NOT_FOUND).build();
	}

	@SuppressWarnings("unchecked")
	@DELETE
	@Path("/{id}/delete")
	public Response deleteAppointment(@PathParam("id") long id){
		int i=0;
		for(LinkedPositionalList<Appointment> day: appointmentList) {
			Appointment ap = day.iterator().next();
			if(day!=appointmentList[0]) {
				i++;
			}

			while(ap.getAppointmentId()!=id) {
				ap = day.iterator().next();
			}

			appointmentList[i].remove((Position<Appointment>)ap);
			return Response.status(200).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}

}
