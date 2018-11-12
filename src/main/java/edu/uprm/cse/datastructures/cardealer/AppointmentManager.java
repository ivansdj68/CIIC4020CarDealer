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
import edu.uprm.cse.datastructures.cardealer.util.LinkedPositionalList;
import edu.uprm.cse.datastructures.cardealer.util.Position;

@Path("/appointment")
public class AppointmentManager {


	@SuppressWarnings("unchecked")
	private static LinkedPositionalList<Appointment>[] appointmentList 
	= new LinkedPositionalList[5];

	public AppointmentManager() {
		for(int i=0; i<appointmentList.length; i++) {
			appointmentList[i] = new LinkedPositionalList<Appointment>();
		}
	}

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public LinkedPositionalList<Appointment>[] getAllAppointments() {
		return appointmentList;
	}

	@GET
	@Path("/day/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public LinkedPositionalList<Appointment> getAllAppointmentsForDay(@PathParam("d") String d) {

		for(int i=0; i<appointmentList.length; i++) {

			switch(i){
			case 0:
				if(d=="Monday") {
					return appointmentList[i];
				}
			case 1:
				if(d=="Tuesday") {
					return appointmentList[i];
				}
			case 2:
				if(d=="Wednesday") {
					return appointmentList[i];
				}
			case 3:
				if(d=="Thursday") {
					return appointmentList[i];
				}
			case 4:
				if(d=="Friday") {
					return appointmentList[i];
				}
			}


		}

		return null;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment getAppointment(@PathParam("id") long id) {
		Optional<Appointment> match = Optional.empty();
		for(LinkedPositionalList<Appointment> day: appointmentList) {
			Appointment ap = day.iterator().next();

			while(ap.getAppointmentId()!=id) {
				ap = day.iterator().next();
			}
			
			match = Optional.of(ap);
			
		}

		if(match.isPresent()) {
			return match.get();
		}
		else {
			throw new NotFoundException();
		}
	}

/*		@GET
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
		}*/

/*	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCarUnit(Appointment appointment) {
		appointmentList.add(appointment);
		return Response.status(201).build();
	}*/
	
		@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointmentOnDay(Appointment appointment, @PathParam("day") String day) {
			for(int i=0; i<appointmentList.length; i++) {

				switch(i){
				case 0:
					if(day=="Monday") {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 1:
					if(day=="Tuesday") {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 2:
					if(day=="Wednesday") {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 3:
					if(day=="Thursday") {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 4:
					if(day=="Friday") {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				}


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
