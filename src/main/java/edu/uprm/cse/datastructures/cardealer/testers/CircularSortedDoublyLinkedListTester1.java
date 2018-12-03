package edu.uprm.cse.datastructures.cardealer.testers;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

public class CircularSortedDoublyLinkedListTester1 {

	public static void main(String[] args) {

		CircularSortedDoublyLinkedList<Car> list = new CircularSortedDoublyLinkedList<Car>();

		System.out.println("Adding cars... \n");
		
		list.add(new Car(1,"Toyota","Highlander","Limited", 0000, 52000));
		list.add(new Car(3,"Toyota","Highlander","XLE", 0000, 25000));
		list.add(new Car(2,"Hyundai","Tuscon","SE", 0000, 25000));
		list.add(new Car(4,"Honda","Fit","LE",0000, 15000));
		list.add(new Car(5,"Ford","F-150","Raptor",0000, 95000));
		list.add(new Car(6,"Ford","F-150","Limited",0000, 55000));
		list.add(new Car(5,"RAM","1500","BigHorn", 0000, 65000));

		System.out.println("Full list: \n");

		for(Car p: list) {
			System.out.println(p);
		}
		
		
		System.out.println("Get id 1, 4 and 20: \n");

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getCarId() == 1 || list.get(i).getCarId() == 4 || list.get(i).getCarId() == 20) {
				System.out.println(list.get(i) + "\n");
			}
		}


	}

}
