package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class AppointmentComparator implements Comparator<Appointment> {

	public AppointmentComparator() {
		super();
	}

	@Override
	public int compare(Appointment ap1, Appointment ap2) {

		String appointmentString1 = ap1.getJob();
		String appointmentString2 = ap2.getJob();

		return appointmentString1.compareTo(appointmentString2);
	}

}
