# Library-Exam-Project

## Person Bean
I created the Person bean to act as the user of my database. There are 5 fields in the Person bean which are, id, name, age, libraryCard, and bookList. Id is used as the primary key and is also used to increment the id whenever another one of the same objects is created. Name and age are self explanatory.
libraryCard is a foreign key of the LibraryCard entity. This is demonstrating a OneToOne relationship with the Person entity, CascadeType.ALL is used because this system functions as a Library database in a way where a Person can’t exist without a Library Card and vice versa. The library database functions like this because I don’t want it holding information on a person after they have deleted their Library Card, it doesn’t make sense holding the information because it will deem it useless.
bookList is a foreign key of the Book entity. This field is demonstrating a OneToMany relationship with the Book entity, FetchType.LAZY is used here because I want the bookList information present when it is needed, I don’t want it present when there is no purpose for it. Since this is a library database, I want the books existing without having a dependent relationship with an entity. So, if a person was deleted from the database, then the books they had would still exist within the database as long as they “return” them.

## LibraryCard Bean
The LibraryCard bean was created as a OneToOne relationship with the Person entity, One Person can only have one Library Card. A Person cannot exist without a Library Card, and vice versa (this was designed for the database). The fields this bean contains are id and cardNumber.

## Book Bean
The Book bean was created as a OneToMany relationship with the Person Entity, One Person can have multiple Books, vice versa here is not possible. The Books can exist without a Person (because it exists in the library database). The fields this bean contains are id, borrowDateTime (uses the DateTimeFormat to capture the Date and Time of when the book was borrowed), bookName, bookBarcode, status (uses EnumType.STRING since it is a Enum object from the BookStatus bean), and description (using the Lob annotation for the purpose to hold more than 255 characters in the String).

## BookStatus bean
The BookStatus bean is an Enum to identify the status of the book, the predefined constants are AVAILABLE and UNAVAILABLE.

## Here is the CRUD this system performs
C – When you sign up for a library card, the system creates a person along with a library card linked to that person

R – Once the person has created a library card, there is an option to borrow books, and on the borrowBookPage, it displays/reads all the books that are in the library, whether they’re available or unavailable

U – There is another page that allows the person to return a book using their library card, entering the card number displays the books the person has in their possession. Returning the book is an option where it will clear the borrowDateTime, and update the status of the book making it available when it is returned

D – There is a delete page that allows the person to delete their library card as long as there are no books that the person still has “borrowed” out
