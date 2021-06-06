package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ec.ftt.model.Book;
import ec.ftt.util.DBUtil;
import java.util.HashMap;
import java.util.Map;

public class BookDao {

    private Connection connection;

    public BookDao() {
        connection = DBUtil.getConnection();
    } 

    public void addBook(Book book) {
        
    	try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ftt.BOOK (TITLE,AUTHOR,STATUS) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getStatus());
        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
    public void deleteBook(Long id) {
    	
    	Book book = new Book();
    	book.setId(id);
    	
    	deleteBook(book);
    	
    }

    public void deleteBook(Book book) {
        try {
            
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM ftt.BOOK WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setLong(1, book.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    public void updateBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ftt.BOOK SET TITLE=?, AUTHOR=?, STATUS=? WHERE ID=?");
            
            // Parameters start with 1
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getStatus());
            
            preparedStatement.setLong(4, book.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    public List<Book> getAllBook() throws SQLException {
        
    	List<Book> bookList = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM ftt.BOOK";
            PreparedStatement add = connection.prepareStatement(query);

            ResultSet rs = add.executeQuery();     
            while (rs.next()) {
                
            	Book book = new Book();
                
            	book.setId(rs.getLong("ID"));
                book.setTitle(rs.getString("TITLE"));
                book.setAuthor(rs.getString("AUTHOR"));
                book.setStatus(rs.getString("STATUS"));

                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    } 

    public Book getBookById(Long id) {
    	
    	Book book = new Book();
    	book.setId(id);
    	
    	return getBookById(id);
    	
    } 
    
    
    	
    public Book getBookById(Book book) {

    	Book bookOutput = null;
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from ftt.BOOK WHERE ID=?");
            
            preparedStatement.setLong(1, book.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                bookOutput = new Book();
            	bookOutput.setId(rs.getLong("ID"));
            	bookOutput.setTitle(rs.getString("TITLE"));
            	bookOutput.setAuthor(rs.getString("AUTHOR"));
            	bookOutput.setStatus(rs.getString("STATUS"));
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookOutput;
    } 
    
    public String getDbDate() {
    	String output="";
    	
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT now() NOW");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	output = rs.getString("NOW");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    }
} 