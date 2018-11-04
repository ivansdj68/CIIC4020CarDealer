package edu.uprm.cse.datastructures.cardealer.testers;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

public class CircularSortedDoublyLinkedListTester2 {

	public static void main(String[] args) {

		CircularSortedDoublyLinkedList<Car> list = new CircularSortedDoublyLinkedList<Car>();

		System.out.println("Adding cars... \n");
		
		list.add(new Car(1,"Toyota","Highlander","Limited",52000));
		list.add(new Car(3,"Toyota","Highlander","XLE",25000));
		list.add(new Car(2,"Hyundai","Tuscon","SE",25000));
		list.add(new Car(4,"Honda","Fit","LE",15000));
		list.add(new Car(5,"Ford","F-150","Raptor",95000));
		list.add(new Car(6,"Ford","F-150","Limited",55000));
		list.add(new Car(5,"RAM","1500","BigHorn",65000));

		System.out.println("Get all cars (full list): \n");

		for(int i=0; i<list.size(); i++) {
			System.out.println(list.toArray()[i] + "\n");
		}


		System.out.println("Delete car with id 4 : ");

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getCarId() == 4) {
				System.out.print(list.remove(i));
			}
		}

		System.out.println("Get car with id 4 \n");

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getCarId() == 4) {
				System.out.println(list.get(i) + "\n");
			}
		}

		System.out.println("Get all cars (full list): \n");

		for(int i=0; i<list.size(); i++) {
			System.out.println(list.toArray()[i] + "\n");
		}

		System.out.println("Add car with id 4 again \n");

		list.add(new Car(4,"Honda","Fit","LE",15000));

		System.out.println("Get all cars (full list): \n");

		for(int i=0; i<list.size(); i++) {
			System.out.println(list.toArray()[i] + "\n");
		}

		System.out.println("\nNow the same but with id 2 \n \n");

		System.out.println("Delete car with id 2 \n");

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getCarId() == 2) {
				System.out.print(list.remove(i));
			}
		}

		System.out.println("Get car with id 2 \n");

		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getCarId() == 2) {
				System.out.println(list.get(i) + "\n");
			}
		}

		System.out.println("Get all cars (full list): \n");

		for(int i=0; i<list.size(); i++) {
			System.out.println(list.toArray()[i] + "\n");
		}

		System.out.println("Add car with id 2 again \n");

		list.add(new Car(2,"Hyundai","Tuscon","SE",25000));

		System.out.println("Get all cars (full list): \n");

		for(int i=0; i<list.size(); i++) {
			System.out.println(list.toArray()[i] + "\n");
		}


	}

}
