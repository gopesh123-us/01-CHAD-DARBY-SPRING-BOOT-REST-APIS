package live.learnjava.books.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private long id;
	private String title;
	private String author;
	private String category;
	private int ratings;
}
