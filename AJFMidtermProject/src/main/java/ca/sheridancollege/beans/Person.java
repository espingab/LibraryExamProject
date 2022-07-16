package ca.sheridancollege.beans;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String name;
	
	@NonNull
	private Integer age;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name="PERSON_LIBRARYCARD", 
		joinColumns = @JoinColumn(name="PERSON_ID"), 
		inverseJoinColumns=@JoinColumn(name="LIBRARYCARD_ID"))
	private LibraryCard libraryCard;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Book> bookList;
	
}
