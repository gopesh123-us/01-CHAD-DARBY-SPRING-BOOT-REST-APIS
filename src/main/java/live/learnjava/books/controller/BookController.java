package live.learnjava.books.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import live.learnjava.books.entity.Book;

@RestController
@RequestMapping("/books")
public class BookController {

	public BookController() {
		initializeBooks();
	}

	private final List<Book> books = new ArrayList<Book>();

	private void initializeBooks() {
		books.addAll(List.of(new Book("Title One", "Author One", "Science"),
				new Book("Title Two", "Author Two", "Science"), new Book("Title Three", "Author Three", "History"),
				new Book("Title Four", "Author Four", "Math"), new Book("Title Five", "Author Five", "Civics"),
				new Book("Title Six", "Author Six", "Geography")));
	}

	@GetMapping("/api-endpoint")
	public String firstApi() {
		return "Hello Gopi Bhai - from BookController";
	}

	@GetMapping
	public List<Book> getBooks() {
		return books;
	}

	@GetMapping("/book/{title}/findbook")
	public Book getBookByTitle(@PathVariable String title) {
		return books.stream().filter(book -> {
			return book.getTitle().equalsIgnoreCase(title);
		}).findFirst().orElse(null);
	}

	@GetMapping("/booksByCategory")
	public List<Book> getBookByCategory(@RequestParam(required = false) String category) {
		if (category == null) {
			return books;
		}
		return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
	}

	@PostMapping("book/create")
	public String addBook(@RequestBody Book myBook) {
		boolean isNewBook = books.stream().noneMatch(book -> book.getTitle().equalsIgnoreCase(myBook.getTitle()));
		if (isNewBook) {
			books.add(myBook);
			return "Book added success";
		} else
			return "Book not added";
	}

	@PutMapping("book/{title}")
	public void updateBookAuthor(@PathVariable String title, @RequestBody Book newBook) {
		books.stream()
		.filter(book -> book.getTitle().equalsIgnoreCase(title))
		.findFirst().ifPresent(book -> {
					book.setAuthor(newBook.getAuthor());
					book.setCategory(newBook.getCategory());
				});
	}
	
	@DeleteMapping("book/{title}")
	public List<Book> deleteBook(@PathVariable String title) {
		Book foundBook = books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).findFirst().get();
		this.books.remove(foundBook);
		return this.books;
	}
	
}
