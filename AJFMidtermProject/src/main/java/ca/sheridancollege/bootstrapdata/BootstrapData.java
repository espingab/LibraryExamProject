package ca.sheridancollege.bootstrapdata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.beans.Book;
import ca.sheridancollege.beans.BookStatus;
import ca.sheridancollege.beans.LibraryCard;
import ca.sheridancollege.beans.Person;
import ca.sheridancollege.repositories.BookRepository;
import ca.sheridancollege.repositories.LibraryCardRepository;
import ca.sheridancollege.repositories.PersonRepository;

@Component
public class BootstrapData implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private LibraryCardRepository libraryCardRepo;
	@Autowired
	private PersonRepository personRepo;
	
	@Override
	public void run(String[] args) throws Exception {
		
		Person person1 = new Person("Jimmy", 21);
	
		Book book1 = new Book("In Search of Lost Time", "1604", BookStatus.AVAILABLE, "Swann's Way, the first part of A la recherche de temps perdu, Marcel Proust's seven-part cycle, was published in 1913. In it, "
				+ "Proust introduces the themes that run through the entire work. The narrator recalls his childhood, aided by the famous madeleine; and describes M. Swann's passion for Odette. The work is incomparable. "
				+ "Edmund Wilson said \"[Proust] has supplied for the first time in literature an equivalent in the full scale for the new theory of modern physics.\"");
		Book book2 = new Book("Ulysses", "0193", BookStatus.AVAILABLE, "Ulysses chronicles the passage of Leopold Bloom through Dublin during an ordinary day, June 16, 1904. The title parallels and alludes to Odysseus "
				+ "(Latinised into Ulysses), the hero of Homer's Odyssey (e.g., the correspondences between Leopold Bloom and Odysseus, Molly Bloom and Penelope, and Stephen Dedalus and Telemachus). Joyce fans worldwide "
				+ "now celebrate June 16 as Bloomsday.");
		Book book3 = new Book("Don Quixote", "7045", BookStatus.AVAILABLE, "Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha with his niece and a housekeeper. He has "
				+ "become obsessed with books of chivalry, and believes their every word to be true, despite the fact that many of the events in them are clearly impossible. Quixano eventually appears to other people to "
				+ "have lost his mind from little sleep and food and because of so much reading.");
		Book book4 = new Book("The Great Gatsby", "4052", BookStatus.AVAILABLE, "The novel chronicles an era that Fitzgerald himself dubbed the \"Jazz Age\". Following the shock and chaos of World War I, American society "
				+ "enjoyed unprecedented levels of prosperity during the \"roaring\" 1920s as the economy soared. At the same time, Prohibition, the ban on the sale and manufacture of alcohol as mandated by the Eighteenth "
				+ "Amendment, made millionaires out of bootleggers and led to an increase in organized crime, for example the Jewish mafia. Although Fitzgerald, like Nick Carraway in his novel, idolized the riches and "
				+ "glamor of the age, he was uncomfortable with the unrestrained materialism and the lack of morality that went with it, a kind of decadence.");
		Book book5 = new Book("The Odyssey", "0062", BookStatus.AVAILABLE, "The Odyssey is one of two major ancient Greek epic poems attributed to Homer. It is, in part, a sequel to the Iliad, the other work traditionally "
				+ "ascribed to Homer. The poem is fundamental to the modern Western canon. Indeed it is the second—the Iliad being the first—extant work of Western literature. It was probably composed near the end of the "
				+ "eighth century BC, somewhere in Ionia, the Greek-speaking coastal region of what is now Turkey. The poem mainly centers on the Greek hero Odysseus (or Ulysses, as he was known in Roman myths) and his "
				+ "long journey home following the fall of Troy. It takes Odysseus ten years to reach Ithaca after the ten-year Trojan War. In his absence, it is assumed he has died, and his wife Penelope and son Telemachus "
				+ "must deal with a group of unruly suitors, the Mnesteres or Proci, competing for Penelope's hand in marriage.");
		Book book6 = new Book("The Divine Comedy", "8052", BookStatus.AVAILABLE, "Belonging in the immortal company of the great works of literature, Dante Alighieri's poetic masterpiece, The Divine Comedy, is a moving human "
				+ "drama, an unforgettable visionary journey through the infinite torment of Hell, up the arduous slopes of Purgatory, and on to the glorious realm of Paradise — the sphere of universal harmony and eternal "
				+ "salvation.");
		Book book7 = new Book("Crime and Punishment", "0962", BookStatus.AVAILABLE, "It is a murder story, told from a murder;s point of view, that implicates even the most innocent reader in its enormities. It is a "
				+ "cat-and-mouse game between a tormented young killer and a cheerfully implacable detective. It is a preternaturally acute investigation of the forces that impel a man toward sin, suffering, and grace. "
				+ "Ever since its publication in 1866 Crime and Punishment has intrigued readers and sorely tested translators, the best of whom seemed to capture one facet of Dostoevsky's masterpiece while missing the rest.");
		
		LibraryCard card1 = new LibraryCard(1000);
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book4);
		book1.setBorrowDateTime(java.time.LocalDateTime.now());
		book4.setBorrowDateTime(java.time.LocalDateTime.now());
		book1.setStatus(BookStatus.UNAVAILABLE);
		book4.setStatus(BookStatus.UNAVAILABLE);
		
		person1.setLibraryCard(card1);
		person1.setBookList(bookList);
		
		bookRepo.save(book1);
		bookRepo.save(book2);
		bookRepo.save(book3);
		bookRepo.save(book4);
		bookRepo.save(book5);
		bookRepo.save(book6);
		bookRepo.save(book7);
		
		personRepo.save(person1);
		
		libraryCardRepo.save(card1);
		
	}
	
}
