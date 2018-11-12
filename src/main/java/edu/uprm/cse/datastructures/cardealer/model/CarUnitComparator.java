package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarUnitComparator implements Comparator<CarUnit> {

	public CarUnitComparator() {
		super();
	}

	@Override
	public int compare(CarUnit cU1, CarUnit cU2) {

		String carUnitString1 = cU1.getVin();
		
		String carUnitString2 = cU2.getVin();

		return carUnitString1.compareTo(carUnitString2);
	}
	
}
