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

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;       

@Path("/cars")
public class CarManager {

	private static CircularSortedDoublyLinkedList<Car> carList = new CircularSortedDoublyLinkedList<Car>();

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] getAllCars() {
		return carList.toArray();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("id") long id) {
		Optional<Car> match = Optional.empty();
		for(int i=0; i<carList.size(); i++) {
			if(carList.get(i).getCarId()==id) {
				match = Optional.of(carList.get(i));
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
	public Response addCar(Car car) {
		carList.add(car);
		return Response.status(201).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCar(Car car) {

		Optional<Car> match = Optional.empty();
		for (int i = 0; i < carList.size(); i++) {
			if (carList.get(i).getCarId() == car.getCarId() ) {
				match = Optional.of(carList.get(i));
			}
		}
		if (match.isPresent()) {
			carList.remove(match.get());
			carList.add(car);
			return Response.status(Response.Status.OK).build();
		} 
		else return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deleteCar(@PathParam("id") long id)
	{

		for(int i=0;i<carList.size();i++){
			if( carList.get(i).getCarId()==id){
				carList.remove(i);
				return Response.status(200).build();}	    	
		}
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}   


}