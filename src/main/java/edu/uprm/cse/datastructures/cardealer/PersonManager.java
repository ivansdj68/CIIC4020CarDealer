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

import edu.uprm.cse.datastructures.cardealer.model.Person;
import edu.uprm.cse.datastructures.cardealer.model.PersonComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/person")
public class PersonManager {

	private static CircularSortedDoublyLinkedList<Person> personList 
		= new CircularSortedDoublyLinkedList<Person>(new PersonComparator());

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Person[] getAllPersons() {
		return personList.toArray();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getPerson(@PathParam("id") long id) {
		Optional<Person> match = Optional.empty();
		for(int i=0; i<personList.size(); i++) {
			if(personList.get(i).getPersonId()==id) {
				match = Optional.of(personList.get(i));
			}
		}
		if(match.isPresent()) {
			return match.get();
		}
		else {
			throw new NotFoundException();
		}
	}
	
	@GET
	@Path("/lastname/{lastName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getLastName(@PathParam("lastName") String lastName) {
		Optional<Person> match = Optional.empty();
		for(int i=0; i<personList.size(); i++) {
			if(personList.get(i).getLastName()==lastName) {
				match = Optional.of(personList.get(i));
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
	public Response addPerson(Person person) {
		personList.add(person);
		return Response.status(201).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePerson(Person person) {

		Optional<Person> match = Optional.empty();
		for (int i = 0; i < personList.size(); i++) {
			if (personList.get(i).getPersonId() == person.getPersonId() ) {
				match = Optional.of(personList.get(i));
			}
		}
		if (match.isPresent()) {
			personList.remove(match.get());
			personList.add(person);
			return Response.status(Response.Status.OK).build();
		} 
		else return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deletePerson(@PathParam("id") long id)
	{
		for(int i=0;i<personList.size();i++){
			if( personList.get(i).getPersonId()==id){
				personList.remove(i);
				return Response.status(200).build();}	    	
		}
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}   

}
