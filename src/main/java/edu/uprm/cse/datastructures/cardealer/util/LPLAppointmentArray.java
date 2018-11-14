package edu.uprm.cse.datastructures.cardealer.util;


import edu.uprm.cse.datastructures.cardealer.model.Appointment;

public class LPLAppointmentArray {
	
	private static LinkedPositionalList<Appointment>[] apList;
	private static LPLAppointmentArray singleton = new LPLAppointmentArray();

	@SuppressWarnings("unchecked")
	private LPLAppointmentArray() {
		apList = new LinkedPositionalList[5];  
		for(int i=0; i<5;i++) {
			apList[i] = new LinkedPositionalList<>();
		}
	}

	public static LPLAppointmentArray getInstance() {
		return singleton;
	}
	public LinkedPositionalList<Appointment>[] getList(){
		return apList;
	}
	
}
