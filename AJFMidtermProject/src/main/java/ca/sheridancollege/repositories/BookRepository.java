package ca.sheridancollege.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.beans.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	public Book findByBookBarcode(String barcode);
	
}
