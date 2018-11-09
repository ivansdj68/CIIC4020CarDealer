package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{

	public PersonComparator() {
		super();
	}

	@Override
	public int compare(Person p1, Person p2) {

		String person1String =  p1.getLastName() + p1.getFirstName();
		String person2String = p2.getLastName() + p2.getFirstName();

		return person1String.compareTo(person2String);
	}
	
}
