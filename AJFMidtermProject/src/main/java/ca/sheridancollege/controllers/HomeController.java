package ca.sheridancollege.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ca.sheridancollege.beans.Book;
import ca.sheridancollege.beans.BookStatus;
import ca.sheridancollege.beans.LibraryCard;
import ca.sheridancollege.beans.Person;
import ca.sheridancollege.repositories.BookRepository;
import ca.sheridancollege.repositories.LibraryCardRepository;
import ca.sheridancollege.repositories.PersonRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private BookRepository bookRepo;
	private LibraryCardRepository libraryCardRepo;
	private PersonRepository personRepo;

	@GetMapping("/stonehengePage")
	public String stonehengePage() {
		return "stonehengePage.html";
	}
	
	@GetMapping("/smallStatuePage")
	public String smallStatuePage() {
		return "smallestStatuePage.html";
	}
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/signUpPage")
	public String signUpForm(Model model) {
		model.addAttribute("person", new Person());
		return "signUpPage.html";
	}
	
	@PostMapping("/createPersonAndCard")
	public String createPersonAndCard(Model model, @ModelAttribute Person person) {
		
		try {
			boolean duplicate = false;
			for(int i = 1; i <= personRepo.count(); i++) {
				Person test = personRepo.getById(Long.valueOf(i));
				if(test.getName().equals(person.getName())) {
					duplicate = true;
				}
			}
			
			if(!duplicate && person.getAge() != 0 && !person.getName().equals(null) && !person.getName().equals("")) {
				LibraryCard libraryCard = new LibraryCard((int)(Math.random() * 2000) + 1000);
				libraryCardRepo.save(libraryCard);
				person.setLibraryCard(libraryCard);
				personRepo.save(person);
				model.addAttribute("person", personRepo.findByName(person.getName()));
			} else {
				return "failSignUpPage.html";
			}
			
			return "successSignUpPage.html";
			
		} catch(Exception e) {
			return "failSignUpPage.html";
		}
	}
	
	@GetMapping("/borrowBookPage")
	public String borrowBookForm(Model model) {
		model.addAttribute("bookList", bookRepo.findAll());
		return "borrowBookPage.html";
	}
	
	@GetMapping("/borrowBook")
	public String borrowBookWithCard(Model model, @RequestParam Integer cardNumber, @RequestParam String barcode) {
		
		try {
			Person person = personRepo.findByLibraryCard(libraryCardRepo.findByCardNumber(cardNumber));
			Book book = bookRepo.findByBookBarcode(barcode);
			book.setStatus(BookStatus.UNAVAILABLE);
			book.setBorrowDateTime(java.time.LocalDateTime.now());
			person.getBookList().add(book);
			bookRepo.save(book);
			personRepo.save(person);
			model.addAttribute("book", bookRepo.findByBookBarcode(barcode));
			return "successPage.html";
		} catch(Exception e) {
			return "failedPage.html";
		}
		
	}
	
	@GetMapping("/deletePage")
	public String deletePage() {
		return "deletePage.html";
	}
	
	@GetMapping("/deleteCard")
	public String deleteCard(@RequestParam String name, @RequestParam Integer age, @RequestParam Integer cardNumber) {
		
		try {
			LibraryCard card = libraryCardRepo.findByCardNumber(cardNumber);
			Person person = personRepo.findByLibraryCard(card);
			if(!person.getName().equals(name) || !person.getAge().equals(age)) {
				return "failedDeletePage.html";
			}
			if(!person.getBookList().isEmpty()) {
				return "failedDeletePage.html";
			}
			for(int i = 0; i < person.getBookList().size(); i++) {
				bookRepo.getById(Long.valueOf(person.getBookList().get(i).getId())).setBorrowDateTime(null);
				bookRepo.getById(Long.valueOf(person.getBookList().get(i).getId())).setStatus(BookStatus.AVAILABLE);
			}
			personRepo.delete(person);
			return "successDeletePage.html";
		} catch(Exception e) {
			return "failedDeletePage.html";
		}
		
	}
	
	@GetMapping("/returnPage")
	public String returnPage() {
		return "returnPage.html";
	}
	
	@GetMapping("/getBooks")
	public String getBooks(Model model, @RequestParam Integer cardNumber) {
		
		try {
			Person person = personRepo.findByLibraryCard(libraryCardRepo.findByCardNumber(cardNumber));
			model.addAttribute("bookList", person.getBookList());
			return "returnPage.html";
		} catch(Exception e) {
			return "failedReturn.html";
		}
		
	}
	
	@GetMapping("/returnBook/{barcode}")
	public String returnBook(Model model, @PathVariable String barcode) {

		Book book = bookRepo.findByBookBarcode(barcode);
		
		List<Person> personList = personRepo.findAll();
		Person booksPerson = new Person();
		for(int i = 0; i < personList.size(); i++) {
			for(int j = 0; j < personList.get(i).getBookList().size(); j++) {
				if(personList.get(i).getBookList().get(j) == book) {
					booksPerson = personList.get(i);
				}
			}
		}
		booksPerson.getBookList().remove(book);
		book.setBorrowDateTime(null);
		book.setStatus(BookStatus.AVAILABLE);
		bookRepo.save(book);
		model.addAttribute("book", bookRepo.findByBookBarcode(barcode));
		return "successReturn.html";
		
	}
	
}
