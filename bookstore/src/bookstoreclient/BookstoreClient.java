package bookstoreclient;

import java.util.ArrayList;
import java.util.List;

import bookstoredomain.Book;
import bookstoredomain.Chapter;
import bookstoredomain.Publisher;
import bookstoreservices.BookStoreService;

public class BookstoreClient {
	public static void main(String[] args) {
BookStoreService bookStoreService = new BookStoreService();
		
		Publisher publisher = new Publisher("JKRO", "JK Rowling Pvt. Ltd.");
		
		Book book = new Book("1244341JHFG", "Harry Potter", publisher);
		
		Chapter chapter1 = new Chapter("Chamber of Secrets", 1);
		Chapter chapter2 = new Chapter("Prisoner of Azkaban", 2);
		
		List<Chapter> chapters = new ArrayList<Chapter>();
		chapters.add(chapter1);
		chapters.add(chapter2);
		
		book.setChapters(chapters);
		
//		Persist Object Graph
//		bookStoreService.persistObjectGraph(book);
//		System.out.println(book);

//		Retrieve Object Graph
		System.out.println(bookStoreService.retrieveObjectGraph("34522093093IHFDJ"));
//		System.out.println(bookStoreService.retrieveObjectGraph("34522093093IHFDJ"));
		
	}
}
