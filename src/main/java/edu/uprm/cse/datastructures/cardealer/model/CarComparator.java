package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarComparator implements Comparator<Car>{

	public CarComparator() {
		super();
	}

	@Override
	public int compare(Car c1, Car c2) {
		
		String car1String = c1.getCarBrand() + c1.getCarModel()
				+ c1.getCarModelOption() + c1.getYear();
		String car2String = c2.getCarBrand() + c2.getCarModel()
		+ c2.getCarModelOption() + c2.getYear();
		
		return car1String.compareTo(car2String);
	}
	

}
