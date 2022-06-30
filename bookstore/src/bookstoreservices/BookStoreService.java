package bookstoreservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookstoredomain.Book;
import bookstoredomain.Chapter;
import bookstoredomain.Publisher;

public class BookStoreService {
	private Connection connection;

	public void persistObjectGraph(Book book) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "root"); 
		
			PreparedStatement insertPublisherStmt = connection.prepareStatement("insert into publisher values(?,?)");
			PreparedStatement insertBookStmt = connection.prepareStatement("insert into book values(?,?,?)");
			PreparedStatement insertChapterStmt = connection.prepareStatement("insert into chapter values(?,?,?)");
			
			insertPublisherStmt.setString(1, book.getPublisher().getCode());
			insertPublisherStmt.setString(2, book.getPublisher().getName());
			insertPublisherStmt.executeUpdate();
			insertPublisherStmt.close();
			
			insertBookStmt.setString(1, book.getIsbn());
			insertBookStmt.setString(2, book.getName());
			insertBookStmt.setString(3, book.getPublisher().getCode());
			insertBookStmt.executeUpdate();
			insertBookStmt.close();
			
			for(Chapter chapter : book.getChapters()) {
				insertChapterStmt.setString(1, book.getIsbn());
				insertChapterStmt.setInt(2, chapter.getChapterNumber());
				insertChapterStmt.setString(3, chapter.getTitle());
				insertChapterStmt.executeUpdate();
			}
			insertChapterStmt.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Book retrieveObjectGraph(String isbn) {
		Book book = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/bookstore", "root", "root");
			PreparedStatement stmt = connection.prepareStatement("select * from book,publisher where book.publisher_code=publisher.code and book.isbn=?");
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				book = new Book(rs.getString("ISBN"), rs.getString("BOOK_NAME"), null);
				Publisher publisher = new Publisher(rs.getString("CODE"), rs.getString("PUBLISHER_NAME"));
				book.setPublisher(publisher);
			}
			rs.close();
			stmt.close();
			
			stmt = connection.prepareStatement("select * from chapter where book_isbn = ?");
			stmt.setString(1, isbn);
			rs = stmt.executeQuery();
			List<Chapter> chapters = new ArrayList<Chapter>();
			while (rs.next()) {
				Chapter chapter = new Chapter(rs.getString("TITLE"), rs.getInt("CHAPTER_NUM"));
				chapters.add(chapter);
			}
			book.setChapters(chapters);
			rs.close();
			stmt.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book;
	}
}
