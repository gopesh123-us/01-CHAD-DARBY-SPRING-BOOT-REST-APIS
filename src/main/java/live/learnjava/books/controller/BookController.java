package live.learnjava.books.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import live.learnjava.books.entity.Book;

@RestController
public class BookController {
	
	public BookController() {
		initializeBooks();
	}
	
	private final List<Book> books = new ArrayList<Book>();
	
	private void initializeBooks() {
		books.addAll(List.of(
				new Book("Title One", "Author One", "Science"),
				new Book("Title Two", "Author Two", "Science"),
				new Book("Title Three", "Author Three", "History"),
				new Book("Title Four", "Author Four", "Math"),
				new Book("Title Five", "Author Five", "Civics"),
				new Book("Title Six", "Author Six", "Geography")));
	}
	
	@GetMapping("/api-endpoint")
	public String firstApi() {
		return "Hello Gopi Bhai - from BookController";
	}
	
	@GetMapping("/books")
	public List<Book> getBooks(){
		return books;
	}
	
	@GetMapping("/book/{title}/findbook")
	public Book getBookByTitle(@PathVariable String title) {
		return books.stream().filter(book -> {return book.getTitle().equalsIgnoreCase(title);})
				.findFirst().orElse(null);			
	}
	
	@GetMapping("/booksByCategory")
	public List<Book> getBookByCategory(@RequestParam (required = false) String category) {
		if(category == null) {
			return books;
		}
		return books.stream().filter(book -> book.getCategory().equalsIgnoreCase(category)).toList();
	}
}
