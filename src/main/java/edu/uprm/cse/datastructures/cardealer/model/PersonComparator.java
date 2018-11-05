package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{

	public PersonComparator() {
		super();
	}

	@Override
	public int compare(Person p1, Person p2) {

		String person1String = p1.getFirstName() + p1.getLastName()
			+ p1.getPhone();
		String person2String = p2.getFirstName() + p2.getLastName()
			+ p2.getPhone();

		return person1String.compareTo(person2String);
	}
	
}
