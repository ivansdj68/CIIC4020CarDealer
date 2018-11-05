package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarUnitComparator implements Comparator<CarUnit> {

	public CarUnitComparator() {
		super();
	}

	@Override
	public int compare(CarUnit cU1, CarUnit cU2) {

		String carUnitString1 = cU1.getVIN() + cU1.getColor()
			+ cU1.getCarPlate();
		String carUnitString2 = cU2.getVIN() + cU2.getColor()
			+ cU2.getCarPlate();

		return carUnitString1.compareTo(carUnitString2);
	}
	
}
