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

import edu.uprm.cse.datastructures.cardealer.model.CarUnit;
import edu.uprm.cse.datastructures.cardealer.model.CarUnitComparator;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

@Path("/carunit")
public class CarUnitManager {

	private static CircularSortedDoublyLinkedList<CarUnit> carUnitList 
		= new CircularSortedDoublyLinkedList<CarUnit>(new CarUnitComparator());

	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public CarUnit[] getAllCarUnits() {
		return carUnitList.toArray();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CarUnit getCarUnit(@PathParam("id") long id) {
		Optional<CarUnit> match = Optional.empty();
		for(int i=0; i<carUnitList.size(); i++) {
			if(carUnitList.get(i).getCarUnitId()==id) {
				match = Optional.of(carUnitList.get(i));
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
	public Response addCarUnit(CarUnit carUnit) {
		carUnitList.add(carUnit);
		return Response.status(201).build();
	}

	@PUT
	@Path("/{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCarUnit(CarUnit carUnit) {

		Optional<CarUnit> match = Optional.empty();
		for (int i = 0; i < carUnitList.size(); i++) {
			if (carUnitList.get(i).getCarUnitId() == carUnit.getCarUnitId()) {
				match = Optional.of(carUnitList.get(i));
			}
		}
		if (match.isPresent()) {
			carUnitList.remove(match.get());
			carUnitList.add(carUnit);
			return Response.status(Response.Status.OK).build();
		} 
		else return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}/delete")
	public Response deleteCarUnit(@PathParam("id") long id)
	{
		for(int i=0;i<carUnitList.size();i++){
			if(carUnitList.get(i).getCarUnitId()==id){
				carUnitList.remove(i);
				return Response.status(200).build();}	    	
		}
		return Response.status(Response.Status.NOT_FOUND).build(); 
	}

}
