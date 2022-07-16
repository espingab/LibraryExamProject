package ca.sheridancollege.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.LibraryCard;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long>{

	public LibraryCard findByCardNumber(Integer cardNumber);
	
}
