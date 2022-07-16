package ca.sheridancollege.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.LibraryCard;
import ca.sheridancollege.beans.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByName(String name);
	
	public Person findByLibraryCard(LibraryCard card);
	
}
