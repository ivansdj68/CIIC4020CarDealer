package edu.uprm.cse.datastructures.cardealer.model;

public class CarUnit {

	private long carUnitId; // internal id of the unit
	private long carId; // id of the car object that represents the general for the car. 
	// This Car from project 1.
	private String VIN; // vehicle identification number
	private String color; // car color
	private String carPlate; // car plate (null until sold)
	private long personId; // id of the person who purchased the car. (null until purchased)
	
	public CarUnit() { }

	public CarUnit(long carUnitId, long carId, String VIN, String color, String carPlate, long personId) {
		super();
		this.carUnitId = carUnitId;
		this.carId = carId;
		this.VIN = VIN;
		this.color = color;
		this.carPlate = carPlate;
		this.personId = personId;
	}

	public long getCarUnitId() {
		return carUnitId;
	}
	public long getCarId() {
		return carId;
	}
	public String getVIN() {
		return VIN;
	}
	public String getColor() {
		return color;
	}
	public String getCarPlate() {
		return carPlate;
	}
	public long getPersonId() {
		return personId;
	}
	
	
/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (personId ^ (personId >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + (int) (age ^ (age >>> 32));
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result;
		return result;
	}*/
	
/*	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (personId != other.personId)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (age != other.age)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}*/
	
/*	@Override
	public String toString() {
		return "Person [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", age=" + age + ", phone=" + phone + "]";
	}
	*/
	
	
	
	
	
	
}
