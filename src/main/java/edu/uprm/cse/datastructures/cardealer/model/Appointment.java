package edu.uprm.cse.datastructures.cardealer.model;

public class Appointment {

	private long appointmentId; // internal id of the appointment
	private long carUnitId; // id of the car to be serviced
	private String job; // description of the job to be done (i.e.: “oil change”)
	private double bill; // cost of the service (initially 0).

	public Appointment(long appointmentId, long carUnitId, String job, double bill) {
		super();
		this.appointmentId = appointmentId;
		this.carUnitId = carUnitId;
		this.job = job;
		this.bill = bill;
	}

	public long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public long getCarUnitId() {
		return carUnitId;
	}
	public void setCarUnitId(long carUnitId) {
		this.carUnitId = carUnitId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}

}
