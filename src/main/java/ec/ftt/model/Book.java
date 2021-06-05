package ec.ftt.model;

import java.util.Objects;

public final class Book {

	private long id;
	private String title,
	               author,
	               status;
	
	public Book() {
		
	}
	public Book(String id, String title, String author, String status) {
		super();
		setId(id);
		setTitle(title);
		setAuthor(author);
		setStatus(status);
	}
	
	public Book(long id, String title, String author, String status) {
		super();
		setId(id);
		setTitle(title);
		setAuthor(author);
		setStatus(status);
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		
		if (id.length()==0)
			setId(0);
		else
			setId(Long.valueOf(id));
	}
	/**
	 * @return the name
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param name the name to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the color
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param color the color to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getStatus() {
		return status;
	}
	/**
	 * @param color the color to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", status =" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, id, author, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		return Objects.equals(title, other.title) && 
                       Objects.equals(author, other.author);
	}
	
	
	
	
}
