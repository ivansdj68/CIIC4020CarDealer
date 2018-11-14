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


	/*@SuppressWarnings("unchecked")
	private static LinkedPositionalList<Appointment>[] appointmentList 
	= new LinkedPositionalList[5];*/

	LinkedPositionalList<Appointment>[] appointmentList= LPLAppointmentArray.getInstance().getList();
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Appointment[] getAllAppointments() {
		for(LinkedPositionalList<Appointment> a: appointmentList) {
			return (Appointment[]) a.toArray();
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

	/*	@GET
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
	}*/

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

	@POST
	@Path("add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointmentOnDay(Appointment appointment, @PathParam("day") int day){

		if(day<appointmentList.length){	
			appointmentList[day].addLast(appointment);			
			return Response.status(201).build();			
		}
		
		return Response.status(Response.Status.NOT_FOUND).build(); 
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


	/*				@POST
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
	}*/

	/*				@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointmentOnDay(Appointment appointment, @PathParam("day") int day) {
			for(int i=0; i<appointmentList.length; i++) {

				switch(i){
				case 0:
					if(day==0) {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 1:
					if(day==1) {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 2:
					if(day==2) {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 3:
					if(day==3) {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				case 4:
					if(day==4) {
						appointmentList[i].addLast(appointment);
						return Response.status(201).build();
					}
				}


			}
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}*/

	/*	@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointmentOnDay(Appointment appointment, @PathParam("day") int day) {
		for(int i=0; i<appointmentList.length; i++) {
			if(day==i) {
				appointmentList[i].addLast(appointment);
				return Response.status(201).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}*/

	/*	@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointment(Appointment a, @PathParam("day") String day){
		for(int i=0; i<appointmentList.length; i++){		
			if(week[i].equals(day)){
				appointmentList[i].addLast(a);
				return Response.status(201).build();
			}
		}		
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}*/


	/*	@POST
	@Path("/add/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAppointment(Appointment a, @PathParam("day") String day){
		boolean isIDValid=false;
		for(CarUnit cu: carUnitList){

			if((int)a.getAppointmentId()==(int)cu.getCarId()){
				isIDValid=true;
			}
		}

		for(int i=0;i<5;i++){		
			if(week[i].equals(day) && isIDValid){
				appointmentList[i].addLast(a);
				return Response.status(201).build();
			}
		}		
		throw new NotFoundException();
	}*/

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
