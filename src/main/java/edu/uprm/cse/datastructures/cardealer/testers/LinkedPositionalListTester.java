package edu.uprm.cse.datastructures.cardealer.testers;

import edu.uprm.cse.datastructures.cardealer.model.Appointment;
import edu.uprm.cse.datastructures.cardealer.util.LinkedPositionalList;

public class LinkedPositionalListTester {

	public static void main(String[] args) {

		LinkedPositionalList<Appointment> list = new LinkedPositionalList<Appointment>();

		System.out.println("Adding appointment... \n");

		//'{"appointmentId" : 1, "carUnitId" : 1, "job" : "oil", "bill" : 39.99}'
		list.addLast(new Appointment(1,1,"oil",39.99));

		System.out.println("Full list: \n");

		for(Appointment p: list) {
			System.out.println(p);
		}

		System.out.println("Get id 1: \n");

		for(Appointment p: list) {
			if(p.getAppointmentId()==1) {
				System.out.println(p);
			}
		}

	}

}
